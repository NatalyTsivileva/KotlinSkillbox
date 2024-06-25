package practice9

class RwandaConverter : CurrencyConverter {

    override val currencyCode: String = "RWF"

    override fun convertRub(amount: Float) = amount * 14.5704
}