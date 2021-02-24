package channels


import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow

fun main() = runBlocking {
    val basket = Channel<String>(0)

    launch { // coroutine1
        val fruits = listOf("Apple", "Orange", "Banana")
        for (fruit in fruits) {
            println("coroutine1: Sending $fruit")
            basket.send(fruit)
        }
    }

    launch { // coroutine2
        repeat(3) {
            delay(100)
            println("coroutine2: Received ${basket.receive()}")
        }
    }

    delay(2000)
    coroutineContext.cancelChildren()
}

private fun getFlows(channel: Channel<String>): Flow<String> {
    return channel.consumeAsFlow()
}