package issues

import java.io.BufferedWriter
import java.io.FileWriter
import java.io.IOException

fun main() {
    val worker = Worker()
    repeat(10) {
        Thread(Runnable { worker.work() }).start()
    }
}

class Worker {
    @Synchronized
    fun work() {
        val name = Thread.currentThread().name
        val fileName = "$name.txt"
        try {
            BufferedWriter(FileWriter(fileName))
                .use { writer -> writer.write("Thread $name wrote this message") }
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        while (true) {
            println("$name is working")
        }
    }
}