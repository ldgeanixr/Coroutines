package coroutines.exceptions

import kotlinx.coroutines.*

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    val job = GlobalScope.launch(handler) { // root coroutine, running in GlobalScope
        throw IllegalStateException()
    }
    val deferred = GlobalScope.async(handler) { // also root, but async instead of launch
        throw AssertionError() // Nothing will be printed, relying on user to call deferred.await()
    }
    joinAll(job, deferred)
}