fun main(args: Array<String>) {
    printTotalProductPrice(
        productName = "картофель",
        productPrice = 45.75,
        productAmount = 1.2,
        discount = 30
    )
    val cucumberPrice = 45.0
    val cucumberAmount = 0.6
    val cucumberDiscount = 12
    printTotalProductPrice("огурец", cucumberPrice, cucumberAmount, cucumberDiscount)

    val mangoPrice = 150.50
    val mangoAmount = 0.9
    val mangoDiscount = 15
    printTotalProductPrice("манго", mangoPrice, mangoAmount, mangoDiscount)
}

fun printTotalProductPrice(
    productName: String,
    productPrice: Double,
    productAmount: Double,
    discount: Int
) {
    val totalPrice = productPrice * productAmount * ((100 - discount) / 100.0)
    println("Итого $productName стоит $totalPrice")

}