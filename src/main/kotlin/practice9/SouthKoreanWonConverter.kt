package practice9

class SouthKoreanWonConverter : CurrencyConverter {

    override val currencyCode: String = "KRW"

    override fun convertRub(amount: Float): Double = amount * 15.5
}