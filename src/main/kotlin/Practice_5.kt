fun main(args: Array<String>) {

    val count = inputCount()

    val numbers = inputNumbers(count)

    println("\nКоличество уникальных элементов: ${numbers.toSet().size}")

    val uniqueValidNumbers = numbers
        .filter { it.lastIndex >= 1 && it[0] == '+' && it[1] == '7' }
        .toSet()

    val catalog = createCatalog(uniqueValidNumbers)

    printCatalogInfo(catalog)
}

fun inputCount(): Int {
    while (true) {
        print("Введите число телефонных номеров в справочнике: ")
        val count = readln().toIntOrNull() ?: 0

        if (count > 0) {
            return count
        } else {
            println("Введено некорректное значение! Должно быть число больше 0")
        }
    }
}

fun inputNumbers(count: Int): List<String> {
    val phoneNumbers = mutableListOf<String>()

    for (i in 0 until count) {
        print("Введите номер телефона:")
        val number = readln()
        phoneNumbers.add(number)
    }

    return phoneNumbers
}

fun createCatalog(numbers: Set<String>): Map<String, String> {
    val phoneCatalog = mutableMapOf<String, String>()

    numbers.forEach { number ->
        print("\nВведите имя человека с номером телефона ${number}:")
        val name = readln()
        phoneCatalog[number] = name
    }

    return phoneCatalog
}

fun printCatalogInfo(catalog: Map<String, String>) {
    if (catalog.isNotEmpty()) {
        println("\nНомера телефонов, начинающиеся с +7: ${catalog.keys.joinToString(",") { it }}")

        println("\nСумма длин всех номеров телефонов: ${catalog.keys.sumOf { it.length }}")

        println("\nСправочник:")
        catalog.forEach { entry -> println("Абонент: ${entry.value}. Номер телефона: ${entry.key}") }

        println("\nТелефонный справочник, отсортированный по номерам телефона:")
        catalog.entries
            .sortedBy { it.key }
            .forEach { entry ->
                println("Абонент: ${entry.value}. Номер телефона: ${entry.key}")
            }


        println("\nТелефонный справочник, отсортированный по имени абонента: ")
        catalog.entries
            .sortedBy { it.value }
            .forEach { entry -> println("Абонент: ${entry.value}. Номер телефона: ${entry.key}") }
    }
}