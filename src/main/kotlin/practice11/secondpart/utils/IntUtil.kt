package practice11.secondpart.utils

import kotlin.random.Random

fun Int.isSuccessProbability(): Boolean = if (this != 0) {
    val step = 100 / this
    val randomValue = Random.nextInt(0, step)
    val isSuccess = randomValue == 0
    isSuccess
} else {
    false
}
