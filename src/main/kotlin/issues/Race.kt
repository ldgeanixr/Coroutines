package issues

import java.util.concurrent.atomic.AtomicInteger

data class Status(
    var sheepCounts: AtomicInteger = AtomicInteger(0)
)

fun main() {
    val status = Status()
    val threads = arrayListOf<Thread>()

    repeat(10) {
        val thread = Thread {
            repeat(1000) {
                status.sheepCounts.incrementAndGet()
            }
        }
        thread.start()
        threads.add(thread)
    }

    threads.forEach { it.join() }

    print(status.sheepCounts)
}