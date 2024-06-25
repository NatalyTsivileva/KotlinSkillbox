package practice8

abstract class BankCard() {
    protected var balance: Float = 0f

    abstract fun  putMoney(amount: Float)

    abstract fun pay(amount: Float): Boolean

    abstract fun getBalanceInfo() : String

    abstract fun getAvailableFundsInfo() : String
}