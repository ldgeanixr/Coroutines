package flow

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun getFlows(): Flow<String> =
    flowOf(1, 2, 3)
        .onEach { println("Emitting $it") }
        .map { value ->
            check(value <= 1) { "Crashed on $value" }
            "string $value"
        }

fun main() = runBlocking {
    getFlows()
        .catch { e -> emit("Caught $e") } // emit on exception
        .collect { value -> println(value) }
}