package practice8

abstract class DebitCard : BankCard() {

    override fun putMoney(amount: Float) {
        if (amount > 0) balance += amount
    }

    override fun pay(amount: Float): Boolean {
        val isValidOperation = balance >= amount
        if (isValidOperation) balance -= amount
        return isValidOperation
    }

    override fun getBalanceInfo(): String {
        return "Баланс: $balance"
    }
}