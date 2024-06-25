package practice8

class AlfaCreditCard(
    creditLimit: Float,
    private val cashbackPercentage: Int = 5
) : CreditCard(creditLimit) {

    private var cashbackAmount: Float = 0f

    override fun pay(amount: Float): Boolean {
        val payed = super.pay(amount)
        if (payed && balance>0 && cashbackPercentage in (1..99)) {
            cashbackAmount += amount * cashbackPercentage * 0.01f
        }
        return payed
    }

    override fun getAvailableFundsInfo() = "${getBalanceInfo()}\nКэшбэк: ${cashbackAmount.roundTwoDigit()}"
}