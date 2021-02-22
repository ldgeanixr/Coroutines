package issues

import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors

fun main() {
    runExecutor()
}

const val IMAGE_URL = "https://static.wikia.nocookie.net/system-of-knowledge/images/7/71/Bogdanov.jpg"

private fun runExecutor() {
    val executor = Executors.newWorkStealingPool()
    val tasks = listOf<Callable<ByteArray>>(
        Callable { fetchImage(IMAGE_URL); },
        Callable { fetchImage(IMAGE_URL); },
        Callable { fetchImage(IMAGE_URL); },
        Callable { fetchImage(IMAGE_URL); }
    )
    try {
        val features = executor.invokeAll(tasks)
        features.forEach { println(Arrays.toString(it.get())) }
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        executor.shutdown()
    }
}

private fun fetchImage(url: String): ByteArray {
    val imageUrl = URL(url)
    val connection = imageUrl.openConnection() as HttpURLConnection
    connection.doInput = true
    connection.connect()

    val inputStream = connection.inputStream
    return inputStream.readAllBytes() // don't do this in prod, memory issues
}