package blocking

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue

fun main() {
    val queue: BlockingQueue<String> = ArrayBlockingQueue(1024)
    val producer = Producer(queue)
    val consumer = Consumer(queue)
    Thread(producer).apply { start();  }
    Thread(consumer).apply { start(); }
//    Thread.sleep(4000)
}

class Producer(private val queue: BlockingQueue<String>) : Runnable {
    override fun run() {
        try {
            queue.put("1")
            Thread.sleep(2000)
            queue.put("2")
            Thread.sleep(3000)
            queue.put("3")
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}

class Consumer(private val queue: BlockingQueue<String>) : Runnable {
    override fun run() {
        try {
            println(queue.take())
            println(queue.take())
            println(queue.take())
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}