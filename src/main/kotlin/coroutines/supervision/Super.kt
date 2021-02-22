package coroutines.supervision

import kotlinx.coroutines.*

fun main() = runBlocking<Unit> {
    val topLevelScope = CoroutineScope(Job())
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }
    val top = topLevelScope.launch(handler) {
        val job1 = launch {
            println("starting Coroutine 1")
        }

        supervisorScope {
            val job2 = launch {
                println("starting Coroutine 2")
            }

            val job3 = launch {
                println("starting Coroutine 3")
            }
        }
    }
    top.join()
}