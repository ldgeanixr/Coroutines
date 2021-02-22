package coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun main() {
    val deferred = (1..1_000_000).map {
        GlobalScope.async { it }
    }
//    runBlocking {
//        val sum = deferred.sumOf { it.await().toLong() }
//        println("Sum: $sum")
//    }
}