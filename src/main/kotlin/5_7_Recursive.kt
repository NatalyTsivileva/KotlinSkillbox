fun main(args: Array<String>) {
    val n = readln().toIntOrNull() ?: 0
    printNNumbers(n)
   println("Сумма чисел от 0 до $n=${calcSum(n)}")
}

fun printNNumbers(n: Int) {
    if (n <= 0) {
        println()
        return
    }
    print("${n} ")
    printNNumbers(n - 1)
}

fun calcSum(number: Int): Int {
    if (number == 0) return 0
    val next = number+calcSum(number-1)
    println("n=${number}, next value = $next")
    return next
}