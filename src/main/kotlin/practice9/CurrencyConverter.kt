package practice9

interface CurrencyConverter {

    val currencyCode: String

    fun convertRub(amount: Float): Double
}