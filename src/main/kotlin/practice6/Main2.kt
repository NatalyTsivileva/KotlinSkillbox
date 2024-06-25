package practice6

fun main(args: Array<String>) {

    val employees = listOf(
        Employee(name = "Савицкий Валерий Аркадьевич", age = 25, gender = Gender.MALE),
        Employee(name = "Фролова Оксана Юрьевна", age = 21, gender = Gender.FEMALE),
        Employee(name = "Васильев Геннадий Петрович", age = 40, gender = Gender.MALE),
        Employee(name = "Старовойтов Дмитрий Васильевич", age = 36, gender = Gender.MALE)
    )

    employees.forEach {
        println(it.getEmployeeInfo())
        println()
    }

    val randomEmployee = employees.random()
    randomEmployee.work(100)
    randomEmployee.increasePosition()
    randomEmployee.work(30)
    randomEmployee.increasePosition()
    randomEmployee.work(500)
    randomEmployee.increasePosition()
    randomEmployee.work(13)
    randomEmployee.work(190)
    randomEmployee.increasePosition()
    randomEmployee.work(30)
    randomEmployee.work(40)
    randomEmployee.obtainSalary()
    randomEmployee.work(50)

    println()
    println(randomEmployee.getEmployeeInfo())

}