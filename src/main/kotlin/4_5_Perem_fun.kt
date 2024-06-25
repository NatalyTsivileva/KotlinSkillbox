/*
Нужно реализовать следующий алгоритм шифрования:

Разделить сообщение пополам.
Обработать первую половину сообщения следующим образом:
В исходном тексте заменить все символы «у» (русская буква У) на символ 1 (единица).
В получившемся тексте заменить каждый символ сообщения символом, стоящим на четыре символа правее от исходного (сдвиг каждого символа вправо на 4).
В получившемся тексте заменить все символы «я» на букву w.
В получившемся тексте заменить все символы «ч» на цифру 2.
В получившемся тексте заменить каждый символ сообщения символом, стоящим на два символа левее от исходного (сдвиг каждого символа влево на 2).
Вторую половину сообщения обработать так:
Заменить пробелы на символ «_».
В получившемся тексте заменить каждый символ сообщения символом, стоящим на один символ правее от исходного (сдвиг каждого символа вправо на 1).
Развернуть получившуюся строку.
Соединить обе зашифрованные половины.
Как же нужно декомпозировать условие этой задачи, чтобы справиться с ней?*/

fun main(args: Array<String>) {
    val text = "Я учу язык Котлин"
    val firstHalf = text.substring(0, text.length / 2)
    val secondHalf = text.substring(text.length / 2)
    println("first half is: $firstHalf")
    println("second half is : $secondHalf")
    val encodedFirstHalf = encodeFirstHalf(firstHalf)
    val encodedSecondHalf = encodeSecondHalf(secondHalf)
    val result = "$encodedFirstHalf$encodedSecondHalf"

    println(
        """
       encoded firstHalf: $encodedFirstHalf
       encoded secondHalf: $encodedSecondHalf
       result: $result
    """.trimIndent()
    )


}

fun encodeFirstHalf(text: String): String {
    var result: String = text
    result = result.replace('у', '1')
    result = moveRight(result, 4)
    result = result.replace('я', 'w')
    result = result.replace('ч', '2')
    result = moveLeft(result, 2)
    return result
}

fun encodeSecondHalf(text: String): String {
    var result = text
    result = result.replace(" ", "_")
    result = moveLeft(result, 1)
    result = result.reversed()
    return result
}

fun moveRight(text: String, amount: Int): String {
    return text.map { c -> c + amount }.joinToString("")
}

fun moveLeft(text: String, amount: Int): String {
    return text.map { c -> c - amount }.joinToString("")
}