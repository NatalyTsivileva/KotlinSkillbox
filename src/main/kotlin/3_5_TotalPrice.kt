/***
 *
 * https://go.skillbox.ru/profession/profession-android-developer-2021/kotlin/f0b0e0a6-4413-492d-b659-35225f592484/longread
 *
 *
 * Измените программу так, чтобы она рассчитывала отдельно стоимость каждого продукта и записывала эти значения в свою переменную
 * (например, milkCost/milkTotalPrice и так далее).
 * А также хранила сумму покупок в отдельной переменной с говорящим названием и выводила сумму всех покупок на экран.
 * Обратите внимание, что сумма должна остаться прежней.
 */

fun main(args: Array<String>) {
    val milkPrice = 52
    val milkAmount = 2
    val milkTotal = milkPrice * milkAmount

    val tomatoPrice = 220
    val tomatoAmount = 0.5
    val tomatoTotal = tomatoPrice * tomatoAmount

    val cucumberPrice = 114
    val cucumberAmount = 0.4
    val cucumberTotal = cucumberPrice * cucumberAmount

    val waterPrice = 40
    val waterAmount = 1
    val waterTotal = waterPrice * waterAmount

    val total = milkTotal + tomatoTotal + cucumberTotal + waterTotal
    println(total)
}