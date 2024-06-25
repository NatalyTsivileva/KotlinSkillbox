package practice12.main1

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Код нейронки
 */


suspend fun calculateFibonacci(number: Int): Int {
    delay(1000) // Имитация длительных вычислений
    return if (number <= 1) number else calculateFibonacci(number - 1) + calculateFibonacci(number - 2)
}

fun main() = runBlocking {
    val job1 = launch {
        val result1 = calculateFibonacci(10)
        println("Рассчитанное значение 1: $result1")
    }

    val job2 = launch {
        val result2 = calculateFibonacci(20)
        println("Рассчитанное значение 2: $result2")
    }

    val job3 = launch {
        val result3 = calculateFibonacci(30)
        println("Рассчитанное значение 3: $result3")
    }

    val job4 = launch {
        val result4 = calculateFibonacci(40)
        println("Рассчитанное значение 4: $result4")
    }

    val progressJob = launch {
        while (job1.isActive || job2.isActive || job3.isActive || job4.isActive) {
            print(".")
            delay(100)
        }
    }

    listOf(job1, job2, job3, job4).joinAll() // Ожидание завершения всех задач
    progressJob.cancel() // Остановка индикатора прогресса
    println("\nВсе значения рассчитаны")
}