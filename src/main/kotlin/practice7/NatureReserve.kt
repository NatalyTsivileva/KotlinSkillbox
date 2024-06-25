package practice7

import kotlin.random.Random

class NatureReserve() {

    private val stepCount = Random.nextInt(1, 10)

    private val animalList = mutableListOf(
        Bird(name = "Аист белый", weight = 100, energy = 50, maxAge = 2),
        Bird(name = "Домовой воробей", weight = 30, energy = 60, maxAge = 2),
        Bird(name = "Зяблик", weight = 20, energy = 40, maxAge = 3),
        Bird(name = "Белая куропатка", weight = 70, energy = 15, maxAge = 2),
        Bird(name = "Лебедь-кликун", weight = 41, energy = 19, maxAge = 3),
        Fish(name = "Гуппи", weight = 10, energy = 30, maxAge = 2),
        Fish(name = "Золотая рыбка", weight = 2, energy = 60, maxAge = 2),
        Fish(name = "Рыба Меч", weight = 5, energy = 70, maxAge = 3),
        Dog(name = "Шпиц рыжуля", weight = 17, energy = 100, maxAge = 3),
        Dog(name = "Болонка", weight = 29, energy = 20, maxAge = 2),
        Animal(name = "Змея Уж", weight = 10, energy = 30, maxAge = 2),
        Animal(name = "Кошка Сфинкс", weight = 7, energy = 50, maxAge = 3),
        Animal(name = "Конь Степной", weight = 36, energy = 90, maxAge = 2)
    )

    fun openReserve() {
        println("Заповедник открыт!\n")

        while (animalList.isNotEmpty()) {
            val newBorn = mutableSetOf<Animal>()
            val dying = mutableSetOf<Animal>()

            for (i in 0 until stepCount) {

                animalList.forEach { animal ->
                    if (!animal.isTooOld) {
                        when (Random.nextInt(0, 4)) {
                            0 -> animal.walk()
                            1 -> animal.eat()
                            2 -> animal.sleep()
                            3 -> newBorn.add(animal.bornChild())
                        }
                    } else {
                        dying.add(animal)
                    }
                    Thread.sleep(100)
                }

            }

            animalList.addAll(newBorn)
            animalList.removeAll(dying)
            println("======================================================================================\n")
            println("Живых животных:${animalList.count()}")
            println("Умерло:${dying.count()}")
            println("Родилось:${newBorn.count()}\n")
            println("======================================================================================\n")

        }
        println("Заповедник закрыт! Все животные умерли")

    }
}
