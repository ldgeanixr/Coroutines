package flow

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.delayEach
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

@InternalCoroutinesApi
fun main() = runBlocking<Unit> {
    val numbersFlow = flowOf(1, 2, 3).delayEach(300)
    val lettersFlow = flowOf("A", "B", "C").delayEach(600)

    numbersFlow
        .combine(lettersFlow) { number, letter -> "$number$letter" }
        .collect { println(it) }
}