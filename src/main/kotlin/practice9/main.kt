package practice9

import kotlin.random.Random

fun main(args: Array<String>) {
    convertRubles(randomValue(), "USD")
    convertRubles(randomValue(), "EUR")
    convertRubles(randomValue(), "GBP")
    convertRubles(randomValue(), "RWF")
    convertRubles(randomValue(), "KRW")

    convertRubles(randomValue(), "AUD")
    convertRubles(randomValue(), "AZN")
    convertRubles(randomValue(), "AMD")
    convertRubles(randomValue(), "BYN")
    convertRubles(randomValue(), "BGN")
    convertRubles(randomValue(), "BRL")
    convertRubles(randomValue(), "HUF")
    convertRubles(randomValue(), "VND")
    convertRubles(randomValue(), "GEL")
    convertRubles(randomValue(), "DKK")
    convertRubles(randomValue(), "AED")
    convertRubles(randomValue(), "EGP")
    convertRubles(randomValue(), "INR")
    convertRubles(randomValue(), "IDR")
    convertRubles(randomValue(), "KZT")
    convertRubles(randomValue(), "CAD")
    convertRubles(randomValue(), "QAR")
    convertRubles(randomValue(), "KGS")
    convertRubles(randomValue(), "CNY")
    convertRubles(randomValue(), "MDL")
    convertRubles(randomValue(), "NZD")
    convertRubles(randomValue(), "NOK")
    convertRubles(randomValue(), "PLN")
    convertRubles(randomValue(), "RON")
    convertRubles(randomValue(), "XDR")
    convertRubles(randomValue(), "SGD")
    convertRubles(randomValue(), "TJS")
    convertRubles(randomValue(), "THB")
    convertRubles(randomValue(), "TRY")
    convertRubles(randomValue(), "TMT")
    convertRubles(randomValue(), "UZS")
    convertRubles(randomValue(), "UAH")
    convertRubles(randomValue(), "CZK")
    convertRubles(randomValue(), "SEK")
    convertRubles(randomValue(), "CHF")
    convertRubles(randomValue(), "RSD")
    convertRubles(randomValue(), "ZAR")
    convertRubles(randomValue(), "JPY")
}

fun convertRubles(rubles: Float, currencyCode: String) {
    val converter = Converters.get(currencyCode)
    val result = converter.convertRub(rubles)
    println("$rubles рублей = ${"%.5f".format(result)} ${converter.currencyCode}")
}

fun randomValue() = Random.nextInt(10, 3000).toFloat()