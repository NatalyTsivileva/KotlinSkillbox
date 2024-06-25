package practice11.firstpart

import practice11.firstpart.exceptions.IncorrectPressure
import practice11.firstpart.exceptions.TooHighPressure
import practice11.firstpart.exceptions.TooLowPressure
import practice11.firstpart.exceptions.wheel.Wheel

fun main() {
    val wheel = Wheel()
    pumpWheel(wheel,0f)
    pumpWheel(wheel,-6f)
    pumpWheel(wheel,1f)
    pumpWheel(wheel,1.7f)
    pumpWheel(wheel,7f)
    pumpWheel(wheel,10f)
    pumpWheel(wheel,2f)
}

private fun pumpWheel(wheel: Wheel, amount: Float) {
    print("При накачке $amount атмосфер(ы) ")
    try {
        wheel.pumpWheel(amount)
        println("процедура удалась. Эксплуатация возможна.")
    } catch (e: TooLowPressure) {
        println("процедура удалась. Эксплуатация невозможна — давление ниже нормального.")
    } catch (e: TooHighPressure) {
        println("процедура удалась. Эксплуатация невозможна — давление превышает нормальное.")
    } catch (e: IncorrectPressure) {
        println("процедура не удалась.")
    }
}