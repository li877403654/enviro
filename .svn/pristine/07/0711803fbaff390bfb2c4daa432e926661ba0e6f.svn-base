package ext

import java.io.PrintWriter
import java.io.StringWriter

/**
 * Created by zzl.
 * Date: 2018-03-30
 */
val Exception.stackTraceText: String
    get() {
        val sw = StringWriter()
        val pw = PrintWriter(sw)
        this.printStackTrace(pw)
        pw.close()
        return sw.toString()
    }