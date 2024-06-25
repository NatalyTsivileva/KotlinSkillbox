package practice9

class EuroConverter : CurrencyConverter {

    override val currencyCode: String = "EUR"

    override fun convertRub(amount: Float): Double = amount / 95.69
}