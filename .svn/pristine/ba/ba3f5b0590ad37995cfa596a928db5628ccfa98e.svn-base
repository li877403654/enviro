package filesys

import org.springframework.util.AntPathMatcher
import java.io.File
import java.nio.file.*
import java.util.*

/**
 * Created by zzl.
 * Date: 2018-03-07
 */
@Suppress("NAME_SHADOWING")
class FileSys {

    private var _homeDir: String = ""

    constructor() {
        //
    }

    constructor(homeDir: String) {
        this.home = homeDir
    }

    var home: String
    get() {
        if(_homeDir.isEmpty()) {
            _homeDir = System.getProperty("user.dir").replace('\\', '/')
        }
        return _homeDir
    }
    set(value) {
        if(value.length < 2 || value[1] != ':') {
            _homeDir = File(System.getProperty("user.dir"), _homeDir).absolutePath
        }
        _homeDir = value.replace('\\', '/').trimEnd('/')
    }

    fun cd(dir: String): FileSys {
        home = File(home, dir).absolutePath
        return this
    }

    fun list(recursive: Boolean = false, includeFolder: Boolean = false,
             returnRelPath: Boolean = false): List<String> {

        val files = mutableListOf<String>()
        val homePath = Paths.get(home)
        val homeLen = home.length
        val paths = if(recursive) Files.walk(homePath) else Files.list(homePath)
        for (path in paths) {
            var filePath = path.toString()
            if(path.toFile().isDirectory) {
                if(!includeFolder || filePath.length == homeLen) {
                    continue
                }
            }
            if(returnRelPath) {
               filePath = filePath.substring(homeLen + 1)
            }
            filePath = filePath.replace('\\', '/')
            files.add(filePath)
        }
        return files
    }

    fun listFolders(fullPath: Boolean = false): List<String> {
        val homePath = Paths.get(home)
        val result = mutableListOf<String>()
        val paths = Files.list(homePath)
        for (path in paths) {
            if (path.toFile().isDirectory) {
                if(fullPath) {
                    result.add(path.toString())
                }
                else {
                    result.add(path.fileName.toString())
                }
            }
        }
        return result
    }

    fun delete(path: String, force: Boolean = true) {
        val path = getFullPath(path)
        val file = File(path)
        if(force) {
            file.deleteRecursively()
        }
        else {
            file.delete()
        }
    }

    fun mkdir(path: String) {
        val path = getFullPath(path)
        val file = File(path)
        file.mkdirs()
    }

    fun getFullPath(path: String): String {
        val path = path.replace('\\', '/')
        if(path.contains(':') || path.startsWith('/')) {
            return path
        }
        else {
            return "$home/$path"
        }
    }

    fun exists(path: String): Boolean {
        if(path.contains('*')) {
            return find(path).isNotEmpty()
        }
        val path = getFullPath(path)
        val file = File(path)
        return file.exists()
    }

    fun find(pattern: String, returnRelPath: Boolean = false): List<String> {
        val allItems = list(pattern.contains('/'), true, true)
        val pathMatcher = AntPathMatcher()
        val items = allItems.filter {
            pathMatcher.match(pattern, it)
        };
        if(!returnRelPath) {
            var fullItems = mutableListOf<String>()
            for(item in items) {
                fullItems.add("$home/$item");
            }
            return fullItems
        }
        return items;
    }

    fun findFirst(pattern: String, returnRelPath: Boolean = false): String {
        return find(pattern, returnRelPath)[0]
    }

    fun get(pattern: String): String {
        return findFirst(pattern, false)
    }

    fun isFolder(filePath: String): Boolean {
        val filePath = getFullPath(filePath)
        return Files.isDirectory(Paths.get(filePath))
    }

    fun isFile(filePath: String): Boolean {
        val filePath = Paths.get(getFullPath(filePath))
        return Files.exists(filePath) && !Files.isDirectory(filePath)
    }

    private fun copyFile(filePath: String, toFilePath: String, force: Boolean): Boolean {
        val toFilePath = Paths.get(toFilePath)
        if(Files.exists(toFilePath)) {
            if(force) {
                try {
                    Files.delete(toFilePath)
                }
                catch(_: Exception) {
                    return false;
                }
            }
            else {
                return false
            }
        }
        try {
            Files.createDirectories(toFilePath.parent);
            Files.copy(File(filePath).toPath(), toFilePath)
            return true
        }
        catch (_: Exception) {
            return false
        }
    }

    fun copy(filePath: String, toFilePath: String, force: Boolean = true) {
        val filePath = getFullPath(filePath)
        val toFilePath = getFullPath(toFilePath)
        if(isFolder(filePath)) {
            throw NotImplementedError()
        }
        else {
            copyFile(filePath, toFilePath, force)
        }
    }

    fun getFileName(filePath: String): String {
        val filePath = filePath.replace('\\', '/')
        val pos = filePath.lastIndexOf('/');
        return filePath.substring(pos + 1)
    }

    fun copyTo(filePath: String, toFolderPath: String, force: Boolean = true): Boolean {
        val filePath = getFullPath(filePath)
        val fileName = getFileName(filePath)
        val toFolderPath = getFullPath(toFolderPath)
        if(isFile(toFolderPath)) {
            return false
        }
        Files.createDirectories(File(toFolderPath).toPath())
        val toFilePath = Paths.get(toFolderPath, fileName).toString()
        if(isFolder(filePath)) {
            throw NotImplementedError()
        }
        else {
            return copyFile(filePath, toFilePath, force)
        }
    }

    fun getChangeTime(filePath: String): Date {
        val filePath = getFullPath(filePath)
        return Date(File(filePath).lastModified())
    }

    fun move(fromFilePath: String, toFilePath: String, force: Boolean): Boolean {
        if(exists(toFilePath)) {
            if(force) {
                delete(toFilePath, true)
            }
            else {
                throw FileAlreadyExistsException(toFilePath)
            }
        }
        File(fromFilePath).renameTo(File(toFilePath))
        return true
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val fs = FileSys()
            //val files = fs.list(true, false, true)
            val files = fs.find("**/*.xml")
            files.forEach(::println)
            println("?")
        }
    }

}