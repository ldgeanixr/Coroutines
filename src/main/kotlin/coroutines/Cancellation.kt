package coroutines

import kotlinx.coroutines.*


fun main() = runBlocking {

}

private suspend fun runWithTimout() {
    val result = withTimeout(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
}

private suspend fun nonCancellable() = runBlocking{
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            withContext(NonCancellable) {
                println("job: I'm running finally")
                delay(1000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L) // delay a bit
    println("blocking.main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("blocking.main: Now I can quit.")
}

private suspend fun CoroutineScope.cancelWithTryFinally() {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("job: I'm running finally")
        }
    }
    delay(1300L) // delay a bit
    println("blocking.main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("blocking.main: Now I can quit.")
}

private suspend fun CoroutineScope.cancel() {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("blocking.main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("blocking.main: Now I can quit.")
}

private suspend fun CoroutineScope.cancelCoroutine() {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5 && isActive) { // cancellable computation loop
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("blocking.main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("blocking.main: Now I can quit.")
}