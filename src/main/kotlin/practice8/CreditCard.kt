package practice8

import kotlin.math.abs

abstract class CreditCard(private var creditLimit: Float) : BankCard() {

    override fun putMoney(amount: Float) {
        if (amount > 0) {
            balance += amount
        }
    }

    override fun pay(amount: Float): Boolean {
        return if (balance + creditLimit >= amount) {
            balance -= amount
            true
        } else {
            false
        }
    }

    override fun getBalanceInfo(): String {
        val limit = if (balance < 0) {
            creditLimit - abs(balance)
        } else
            creditLimit

        val balance = if (this.balance < 0) 0 else balance
        return """
                Кредитные средства: $limit
                Собственные средства: $balance
               """.trimIndent()
    }
}
