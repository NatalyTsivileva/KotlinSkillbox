package curswork
// This file was automatically generated from channels.md by Knit tool. Do not edit.

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() = runBlocking<Unit> {
    val jobs= mutableListOf<Job>()
    val producer = produceNumbers()
    repeat(5) { val job = launchProcessor(it, producer)
    jobs.add(job)}
        //delay(950)
    jobs.joinAll()
    producer.cancel() // cancel producer coroutine and thus kill them all
}

fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1 // start from 1
    while (true) {
        send(x++) // produce next
        delay(100) // wait 0.1s
    }
}

fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (msg in channel) {
        println("Processor #$id received $msg")
    }
}