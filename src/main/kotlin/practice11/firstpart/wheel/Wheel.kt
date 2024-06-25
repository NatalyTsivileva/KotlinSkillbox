package practice11.firstpart.exceptions.wheel

import practice11.firstpart.exceptions.IncorrectPressure
import practice11.firstpart.exceptions.TooHighPressure
import practice11.firstpart.exceptions.TooLowPressure

class Wheel {
    private var currentPressure = 0f

    fun pumpWheel(amount: Float) {
        amount.makeSureCorrectPressure()
        currentPressure = amount
    }

    fun checkPressure() {
        currentPressure.makeSureCorrectPressure()
    }

    private fun Float.makeSureCorrectPressure() {
        when {
            this >= 10 || this <= 0 -> throw IncorrectPressure()
            this < 1.6 -> throw TooLowPressure()
            this > 2.5 -> throw TooHighPressure()
        }
    }

}