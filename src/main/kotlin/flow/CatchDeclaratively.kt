package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

    fun main() = runBlocking<Unit> {
        flowOf(1, 2, 3)
            .onEach { value ->
                check(value <= 1) { "Collected $value" }
                println(value)
            }
            .catch { e -> println("Caught $e") }
            .onCompletion { println("done") }
            .collect { println("collect") }
    }