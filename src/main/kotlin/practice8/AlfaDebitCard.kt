package practice8

class AlfaDebitCard : DebitCard() {

    private var bonusBalls: Float = 0f


    override fun pay(amount: Float): Boolean {
        val isPayed = super.pay(amount)
        if (isPayed) bonusBalls += amount * 0.01f
        return isPayed
    }

    override fun getAvailableFundsInfo() = "${getBalanceInfo()}\nБонусные баллы: ${bonusBalls.roundTwoDigit()}"
}