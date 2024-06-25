package practice12

import kotlinx.coroutines.*
import practice12.Fibonacci.hundredStepInterruptFunction
import practice12.Fibonacci.immediateInterruptFunction
import practice12.Fibonacci.take


fun main(args: Array<String>) = runBlocking {
    testFibonacci(this)
    testTimeout(this)
    println("\n\n\n\nEND")
}


suspend fun testFibonacci(scope: CoroutineScope) {
    println("\t\t\t\t\t\tТест Фибоначчи")

    val result = mutableListOf<String>()

    val jobs = listOf(
        scope.fibonacciJob(7000, result::add),
        scope.fibonacciJob(100, result::add, hundredStepInterruptFunction),
        scope.fibonacciJob(1000, result::add),
        scope.fibonacciJob(400, result::add, immediateInterruptFunction),
        scope.fibonacciJob(5000, result::add)
    )

    val progressJob = scope.launch {
        while (jobs.any { it.isActive }) {
            delay(1000)
            print("▮")
        }
    }

    jobs.joinAll()

    println(result.joinToString(","))

    progressJob.cancel()
}


suspend fun testTimeout(scope: CoroutineScope) {
    println("\n\n\t\t\t\t\t\t Тест WITH TIMEOUT")

    val job = scope.launch {
        try {
            withTimeout(5_000L) {
                val result = take(1_500_000)
                println("\nКорутина с withTimeout: withTimeout $result")
            }
        } catch (e: TimeoutCancellationException) {
            println("\nВремя выполнения превышено! ${e.message}")
        }
    }
    job.join()
}
