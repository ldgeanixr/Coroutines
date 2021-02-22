package issues

data class Visibility(@Volatile var done: Boolean)

fun main() {
    val state = Visibility(false)

    val threads = mutableListOf<Thread>()

    val thread = Thread {
        repeat(100_000) { synchronized(Any()) {} }
        println("done")
        state.done = true
    }.apply { start(); }

    threads.add(thread)

    val thread2 = Thread {
        while (!state.done) {
        }
    }.apply { start() }
    threads.add(thread2)

    threads.forEach { it.join() }

    print(state.done)

}