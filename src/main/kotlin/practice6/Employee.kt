package practice6

import practice6.PositionUtils.createInternPosition
import practice6.PositionUtils.positionList
import java.util.*

/***
 * Класс, описывающий работника.
 * У каждого работника есть имя, пол, возраст, должность. Заработная плата установливается сдельная(ставка в час) и зависит от должности.
 * Каждому работнику так же устанавливается график работы - 2/2, 5/2, 3/1.
 * График 2/2 доступен только самым опытным сотрудникам, с графиком 5/2 работают стажёры, остальные сотрудники работают по графику 3/1.
 * Работника можно повысить в должности, ему можно посчитать зарплату за отработанное время и сделать запрос на её получение.
 * Зарплату можно получить в любое время. Отработанные часы обнуляются, когда сотрудник получает з/п.
 * По умолчанию все сотрудники принимаются на должность стажёра с графиком 2 на 2.
 * При повышении сотрудника бухгалтерия выдает з/п за отработанное время на предыдущей должности.
 */

class Employee(
    val name: String,
    val age: Int,
    val gender: Gender
) {

    var currentPosition: Position = createInternPosition()
        private set

    var schedule: Schedule = Schedule.TWO_BY_TWO
        private set

    var workedHours: Int = 0
        private set

    private val salary: Double
        get() = workedHours * currentPosition.salaryPerHour

    fun work(hours: Int) {
        workedHours += hours
        println("[work] Сотрудник ($name) отработал(а): ${workedHours} ч.")
    }

    fun obtainSalary(): Double {
        val currentSalary = salary
        println("[obtainSalary] Зарплата сотруднику ($name - \"${currentPosition.name}\") выдана в размере ${currentSalary} (ставка ${currentPosition.salaryPerHour}/час). Отработал(a) $workedHours часов")
        workedHours = 0
        return currentSalary
    }

    fun increasePosition() {
        val nextPosition = positionList.find { it.level == currentPosition.level + 1 }
        if (nextPosition != null) {
            println("[increasePosition] Сотрудник ($name) повышен в должности. C \"${currentPosition.name}\" на \"${nextPosition.name}\"")

            obtainSalary()

            currentPosition = nextPosition

            updateSchedule()
        }
    }


    private fun updateSchedule() {
        val minLevel = positionList.minBy { it.level }.level
        val maxLevel = positionList.maxBy { it.level }.level

        schedule = when (currentPosition.level) {
            maxLevel -> Schedule.TWO_BY_TWO
            minLevel -> Schedule.FIVE_BY_TWO
            else -> Schedule.THREE_BY_ONE
        }
        println("[updateSchedule] Расписание для сотрудника ($name) обновлено!: ${schedule.name}")
    }

    fun getEmployeeInfo() = """
         Имя: $name
         Возраст: $age
         Пол: ${gender.name.lowercase()}
         Позиция: ${currentPosition.name}
         Ставка в час: ${currentPosition.salaryPerHour}
         Отработанные часы: $workedHours
         Зарплата: $salary
        """.trimIndent()
}

enum class Gender { MALE, FEMALE }

enum class Schedule { TWO_BY_TWO, THREE_BY_ONE, FIVE_BY_TWO }

data class Position(
    val level: Int,
    val name: String,
    val salaryPerHour: Double
)

object PositionUtils {

    val positionList = listOf(
        createInternPosition(),
        Position(level = 1, name = "Специалист 1-й категории", salaryPerHour = 250.0),
        Position(level = 2, name = "Специалист 2-й категории", salaryPerHour = 350.0),
        Position(level = 3, name = "Специалист 3-й категории", salaryPerHour = 400.0)
    )

    fun createInternPosition() = Position(level = 0, name = "Стажёр", salaryPerHour = 180.0)

}