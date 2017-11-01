import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>){
    println("Start")

    val deferred = (1..1_000_000).map { n ->
        async {
            delay(1000)
            n
        }
    }

    runBlocking {
        val sum = deferred.sumBy {it.await()}
        println("Sun: $sum")
    }

    async {
        workload(10)
    }

    println("Stop")
}

suspend fun workload(n: Int): Int{
    delay(1000)
    return n
}