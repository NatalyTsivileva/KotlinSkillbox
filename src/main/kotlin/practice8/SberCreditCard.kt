package practice8

class SberCreditCard(
    creditLimit: Float,
    private val putBonusPercentage: Int = 1
) : CreditCard(creditLimit) {

    private var putBonus: Float = 0f

    override fun putMoney(amount: Float) {
        super.putMoney(amount)
        if (balance >= amount) {
            putBonus += amount * putBonusPercentage * 0.01f
        }
    }

    override fun getAvailableFundsInfo() = "${getBalanceInfo()}\nБаллы за пополнение: ${putBonus.roundTwoDigit()}"
}