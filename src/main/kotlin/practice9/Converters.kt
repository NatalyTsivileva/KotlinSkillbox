package practice9

object Converters {

    val convertersList = listOf(
        UsdConverter(),
        EuroConverter(),
        SterlingConverter(),
        RwandaConverter(),
        SouthKoreanWonConverter()
    )


    fun get(currencyCode: String) = convertersList
        .find { it.currencyCode == currencyCode }
        ?: createCurrencyConverter(currencyCode)


    private fun createCurrencyConverter(currencyCode: String) = object : CurrencyConverter {

        override val currencyCode: String = currencyCode

        override fun convertRub(amount: Float) = when (this.currencyCode) {
            "AUD" -> amount / 58.5878
            "AZN" -> amount / 51.8871
            "AMD" -> amount / 0.227317
            "BYN" -> amount / 27.59
            "BGN" -> amount / 48.5502
            "BRL" -> amount / 16.3688
            "HUF" -> amount / 0.241045
            "VND" -> amount / 0.00363819
            "HKD" -> amount / 11.3145
            "GEL" -> amount / 30.7731
            "DKK" -> amount / 12.7306
            "AED" -> amount / 24.0185
            "EGP" -> amount / 1.84887
            "INR" -> amount / 1.05579
            "IDR" -> amount / 0.00541253
            "KZT" -> amount / 0.19525
            "CAD" -> amount / 64.3854
            "QAR" -> amount / 24.233
            "KGS" -> amount / 1.01251
            "CNY" -> amount / 12.0408
            "MDL" -> amount / 4.96566
            "NZD" -> amount / 54.4508
            "NOK" -> amount / 8.28042
            "PLN" -> amount / 21.9866
            "RON" -> amount / 19.1507
            "XDR" -> amount / 116.4513
            "SGD" -> amount / 65.3877
            "TJS" -> amount / 8.22169
            "THB" -> amount / 2.40821
            "TRY" -> amount / 2.72782
            "TMT" -> amount / 25.2023
            "UZS" -> amount / 0.00698898
            "UAH" -> amount / 2.18099
            "CZK" -> amount / 3.85154
            "SEK" -> amount / 8.45217
            "CHF" -> amount / 98.5014
            "RSD" -> amount / 0.814145
            "ZAR" -> amount / 4.7899
            "JPY" -> amount / 0.562551
            else -> 0.0
        }
    }
}