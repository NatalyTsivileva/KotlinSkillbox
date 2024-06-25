package practice9

class UsdConverter : CurrencyConverter {

    override val currencyCode: String = "USD"

    override fun convertRub(amount: Float): Double = amount / 89.38
}