package practice9

class SterlingConverter:CurrencyConverter {
    override val currencyCode: String="GBP"

    override fun convertRub(amount: Float) = amount/113.58
}