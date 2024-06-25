package practice12

import kotlinx.coroutines.*
import java.math.BigInteger


typealias  InterruptFunction = (step: Int, fibonacci: BigInteger) -> Boolean


object Fibonacci {

    val nonInterruptFunction: InterruptFunction = { _, _ -> false }

    val immediateInterruptFunction: InterruptFunction = { step, _ -> step >= 0 }

    val hundredStepInterruptFunction: InterruptFunction = { step, _ -> step == 100 }

    suspend fun take(
        count: Int,
        needInterrupt: InterruptFunction = nonInterruptFunction
    ): BigInteger {
        var sum = (0L).toBigInteger()
        var n0 = (0L).toBigInteger()
        var n1 = (1L).toBigInteger()

        for (i in 2..count) {
            delay(1)

            if (needInterrupt(i, sum) || !currentCoroutineContext().isActive) {
                break
            }

            sum = n0 + n1
            n0 = n1
            n1 = sum
        }

        return sum
    }
}

fun CoroutineScope.fibonacciJob(
    n: Int,
    saveResult: (text: String) -> Boolean,
    interruption: InterruptFunction = Fibonacci.nonInterruptFunction
) = this.launch {
    val fibonacci = Fibonacci.take(n, interruption)
    val text = "\nfibonacci($n) = $fibonacci"
    saveResult(text)
}
