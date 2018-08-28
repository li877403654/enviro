package os

import java.io.*
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit
import java.util.*


/**
 * Created by zzl.
 * Date: 2018-03-16
 */
class SysCommand {

    companion object {
        fun inheritIO(src: InputStream, writer: Writer) {
            var bw: BufferedWriter
            if(writer is BufferedWriter) {
                bw = writer
            }
            else {
                bw = BufferedWriter(writer)
            }
            Thread(Runnable {
                val sc = Scanner(src, "cp936") //?
                while (sc.hasNextLine()) {
                    val line = sc.nextLine()
                    bw.write(line)
                    bw.newLine()
                    bw.flush()
                }
            }).start()
        }

        fun extractErrorMsg(stdout: String): String {
            var errMsg: String;
            val keyword = "___ERROR___:"
            var errPos = stdout.lastIndexOf(keyword);
            if (errPos != -1) {
                errMsg = stdout.substring(errPos + keyword.length).trim()
            }
            else {
                errMsg = stdout
            }
            return errMsg
        }
    }

    var raiseErrorOnNonZeroExitCode: Boolean = false
    var commandParts = listOf<String>()
    var dir: String = ""
    var timeoutSeconds = 60*20
    var exitCode = Int.MIN_VALUE

    constructor(commandParts: List<String>) {
        this.commandParts = commandParts
    }

    constructor(vararg commandParts: String) {
        this.commandParts = commandParts.toList()
    }

    fun setDir(dir: String): SysCommand {
        this.dir = dir
        return this
    }

    fun run(raiseErrorOnNonZeroExitCode: Boolean = this.raiseErrorOnNonZeroExitCode): String {
        val pc = ProcessBuilder(commandParts)
                //.redirectInput()
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
        if(dir != "") {
            pc.directory(File(dir))
        }
        val p = pc.start()

        val swStdout = StringWriter();
        inheritIO(p.inputStream, swStdout);
        inheritIO(p.errorStream, OutputStreamWriter(System.err));

        if(!p.waitFor(timeoutSeconds.toLong(), TimeUnit.SECONDS)) {
            //wtd?
        }
        exitCode = p.exitValue()
        val stdout = swStdout.buffer.toString()
        if(exitCode != 0 && raiseErrorOnNonZeroExitCode) {
            var errMsg = extractErrorMsg(stdout)
            throw RuntimeException("Command failed (" + exitCode + "): " + errMsg)
        }
        return stdout
    }

    fun run(callback: (ps: PrintStream, line: String?) -> Unit): Int {
        val pc = ProcessBuilder(commandParts)
                //.redirectInput()
                .redirectOutput(ProcessBuilder.Redirect.PIPE)
                .redirectError(ProcessBuilder.Redirect.PIPE)
        if(dir != "") {
            pc.directory(File(dir))
        }
        val p = pc.start()

//        val swStdout = StringWriter();
//        inheritIO(p.inputStream, swStdout);
        //inheritIO(p.inputStream, OutputStreamWriter(System.out));
        inheritIO(p.errorStream, OutputStreamWriter(System.err));

        val pStdin = p.outputStream
        val ps = PrintStream(pStdin, true, "cp936")
        callback(ps, null);
        Thread(Runnable {
            val sc = Scanner(p.inputStream, "cp936") //?
            while (sc.hasNextLine()) {
                val line = sc.nextLine()
                callback(ps, line)
            }
        }).start()


        if(!p.waitFor(timeoutSeconds.toLong(), TimeUnit.SECONDS)) {
            //wtd?
        }
        return p.exitValue()
//        val stdout = swStdout.buffer.toString()
//        if(exitCode != 0 && raiseErrorOnNonZeroExitCode) {
//            var errMsg = extractErrorMsg(stdout)
//            throw RuntimeException("Command failed (" + exitCode + "): " + errMsg)
//        }
//        return stdout
    }

}