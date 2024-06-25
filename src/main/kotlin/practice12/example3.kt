package practice12.nn

import kotlinx.coroutines.*
import practice12.Fibonacci


fun main(args: Array<String>) = runBlocking {
    testFibonacci(this)
    testTimeout(this)
    println("\n\n\n\nEND")
}


suspend fun testFibonacci(scope: CoroutineScope) {
    println("\t\t\t\t\t\tТест Фибоначчи")

    val jobs = listOf(
        scope.launch {
            val fibonacci = Fibonacci.take(700)
            val text = "\nJOB0: fibonacci=$fibonacci"
            println(text)
        },
        scope.launch {
            val fibonacci = Fibonacci.take(100, Fibonacci.hundredStepInterruptFunction)
            val text = "\nJOB1: fibonacci=$fibonacci"
            println(text)
        },
        scope.launch {
            val fibonacci = Fibonacci.take(1000)
            val text = "\nJOB2: fibonacci=$fibonacci"
            println(text)
        },
        scope.launch {
            val fibonacci = Fibonacci.take(100, Fibonacci.immediateInterruptFunction)
            val text = "\nJOB3: fibonacci=$fibonacci"
            println(text)
        }
    )

    val progressJob = scope.launch {
        while (jobs.any { it.isActive }) {
            delay(1000)
            print(".")
        }
    }

    jobs.joinAll()
    progressJob.cancel()
}


suspend fun testTimeout(scope: CoroutineScope) {
    println("\n\n\t\t\t\t\t\t Тест WITH TIMEOUT")

    val job = scope.launch {
        try {
            withTimeout(5_000L) {
                val result = Fibonacci.take(1_500_000)
                println("\nКорутина с withTimeout: withTimeout $result")
            }
        } catch (e: TimeoutCancellationException) {
            println("\nВремя выполнения превышено! ${e.message}")
        }
    }
    job.join()
}

