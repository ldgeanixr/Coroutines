package coroutines

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong
import kotlin.concurrent.thread

fun main() {
    startMlnCoroutines()
}

private fun startMlnCoroutines() {
    val c = AtomicLong()
    for (i in 1..1_000_000L)
        GlobalScope.launch {
            c.addAndGet(i)
        }
    println(c.get())
}

private fun startMlnThreads() {
    val c = AtomicLong()
    for (i in 1..1_000_000L)
        thread(start = true) {
            c.addAndGet(i)
        }
    println(c.get())
}