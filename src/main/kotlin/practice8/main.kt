package practice8
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

fun main(args: Array<String>) {
    val cards = listOf(
        AlfaCreditCard(200f),
        AlfaDebitCard(),
        SberCreditCard(1000f)
    )

    cards.forEach { card ->
       println("\t\t\t\t\t\t\t${card.javaClass.simpleName.uppercase()}\n")

        repeat(10) {
            val amount = Random.nextInt(10,100).toFloat()
            card.putMoney(amount)
            printCardInfo(card, amount, Operation.PUT)
        }

        repeat(20) {
            val amount = Random.nextInt(50, 200).toFloat()
            val payed = card.pay(amount)
            if(payed) printCardInfo(card, amount, Operation.PAY)
        }
    }
}

fun printCardInfo(card: BankCard, amount: Float, operation: Operation) {
    val formatter = SimpleDateFormat("HH:mm:ss")
    val time = formatter.format(Date())

    Thread.sleep(1_000)
    val info = if (operation == Operation.PAY) "ОПЛАТА" else "ПОПОЛНЕНИЕ СЧЕТА"

    println("======================================================================================")
    println("[$time] [${card.javaClass.simpleName}] $info, сумма=$amount:")
    println(card.getAvailableFundsInfo())
    println()
}

enum class Operation { PUT, PAY }