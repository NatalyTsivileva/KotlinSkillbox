fun main(args: Array<String>) {
    val expectedEarnings = 500

    val goodMetalPrice = 110
    val badMetalPrice = 55
    val fuelPrice = 350

    val diary1 = (6 * goodMetalPrice + 8 * badMetalPrice) - fuelPrice
    val diary2 = (3 * goodMetalPrice + 2 * badMetalPrice) - fuelPrice
    val diary3 = (4 * goodMetalPrice + 11 * badMetalPrice) - fuelPrice
    val diary4 = (7 * goodMetalPrice + 0 * badMetalPrice) - fuelPrice
    val diary5 = (3 * goodMetalPrice + 7 * badMetalPrice) - fuelPrice
    val diary6 = (0 * goodMetalPrice + 0 * badMetalPrice) - fuelPrice

    val actualEarnings = (diary1 + diary2 + diary3 + diary4 + diary5 +diary6) / 6f

    val isPromiseFulfilled = actualEarnings >= expectedEarnings;
    println(actualEarnings)
    println(isPromiseFulfilled)
}