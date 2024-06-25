fun main(args: Array<String>) {

    val firstName = "Наталья"
    val lastName = "Цивилева"
    var height = 1.6
    val weight = 68f
    var isChild = height < 1.5 || weight < 40
    var info = """
        ==================================================
        Анкета
        Имя:$firstName
        Фамилия:$lastName
        Рост:${height}м
        Вес:${weight}кг
        Ребенок:${if (isChild) "Да" else "Нет"}
        
    """.trimIndent()


    println(info)


    height = 1.48
    isChild = height < 1.5 || weight < 40
    info = """
        ==================================================
        Анкета
        Имя:$firstName
        Фамилия:$lastName
        Рост:${height}м
        Вес:${weight}кг
        Ребенок:${if (isChild) "Да" else "Нет"}
        
    """.trimIndent()

    println(info)
}