package coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}

fun main() {
    val time = measureTimeMillis {
        try {

            val one = somethingUsefulOneAsync()
            val two = somethingUsefulTwoAsync()

            runBlocking {
                println("The answer is ${one.await() + two.await()}")
            }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }

    }
    println("Completed in $time ms")
}