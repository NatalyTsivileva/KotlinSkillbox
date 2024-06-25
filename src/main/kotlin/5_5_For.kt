import kotlin.random.Random

fun main(args: Array<String>) {

    val hiddenNumber = Random.nextInt(20)

    println("Загадано число от 0 до 20. Попробуйте его угадать")

    var quantitiesNumber = 0

    do {
        //вводим данные
        val userNumber = getUserNumber()

        quantitiesNumber++;

        when {
            userNumber == hiddenNumber -> {
                println("Вы угадали, было загадано число: $hiddenNumber")
                println("число попыток=$quantitiesNumber")
                return
            }

            hiddenNumber > userNumber -> {
                println("Загаданное число больше")
            }

            else -> {
                println("Загаданное число меньше")
            }
        }


    } while (true)
}


fun getUserNumber(): Int {

    do {
        print("Введите число от 0 до 20:")
        val number = readln().toInt()

        if (number < 1 || number > 20) {
            println("Некорректный ввод!")
        } else {
            return number
        }
    } while (true)
}

