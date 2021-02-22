package issues

import kotlinx.coroutines.*

class Criminal(@Volatile var hostageReleased: Boolean = false) {
    fun releaseHostage(police: Police) {
        while (!police.moneySent) {
            println("Criminal: waiting")
            Thread.sleep(500)
        }
        println("Criminal: released hostage")
        hostageReleased = true
    }
}

class Police(@Volatile var moneySent: Boolean = false) {
    fun giveRansom(criminal: Criminal) {
        while (!criminal.hostageReleased) {
            println("Police: waiting")
            Thread.sleep(500)
        }
        println("Police: sent money")
        moneySent = true
    }
}

fun main() {
    val police = Police()
    val criminal = Criminal()

    Thread(Runnable { police.giveRansom(criminal) }).start()
    Thread(Runnable { criminal.releaseHostage(police) }).start()
}

