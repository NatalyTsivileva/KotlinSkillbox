package com


fun main(args: Array<String>) {
    val text = "F2p)v\"y233{0->c}ttelciFc"
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
    result = shift(text) { it + 1 }
    result = result.replace("5", "s")
    result = result.replace("4", "u")
    result = shift(result) { it - 3 }
    result = result.replace("0", "o")
    return result
}

fun encodeSecondHalf(text: String): String {
    var result = text
    result = result.reversed()
    result = shift(result) { it - 4 }
    result = result.replace("_", " ")
    return result
}

fun shift(text: String, move: (char: Char) -> Char): String {
    return text.map { c -> move(c) }.joinToString("")
}