package coroutines.exceptions

import kotlinx.coroutines.*
import java.io.IOException

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got " +
                "$exception with suppressed ${exception.suppressed!!.contentDeepToString()}")
    }

    val job = GlobalScope.launch(handler) {
        launch {
            try {
                delay(200) // it gets cancelled when another sibling fails with IOException
            } finally {
                throw ArithmeticException() // the second exception
            }
        }
        supervisorScope {
            launch {
//                delay(100)
                while (isActive) {
                    delay(1000)
                    println("supervisor job")
                }
//                throw IOException() // the first exception
            }
        }
        delay(Long.MAX_VALUE)
    }
    job.join()
}