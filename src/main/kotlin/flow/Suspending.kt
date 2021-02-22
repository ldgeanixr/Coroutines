package flow

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

suspend fun list(): List<Int> {
    delay(100) // pretend we are doing something asynchronous here
    return listOf(1, 2, 3)
}

fun main() = runBlocking<Unit> {
    list().forEach { value -> println(value) }
}