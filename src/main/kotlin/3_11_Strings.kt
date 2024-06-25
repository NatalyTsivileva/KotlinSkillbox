fun main(args: Array<String>) {
    val bloggerName = "Оксана"

    val dateVideoShown = "13.08.2023"

    val productName = "Фитнес-браслет"
    val productBuyPrice = 700
    val productSellPrice = 2400

    val videoViews = 370000
    val linkClicked = 3250
    val salesAmount = 153


    val totalIncome = salesAmount * productSellPrice
    val totalBuyPrice = salesAmount * productBuyPrice
    val salesProfit = totalIncome - totalBuyPrice

    val payment = 35000 + (totalIncome.toFloat() * 0.1f)
    val pureProfit = salesProfit - payment

    val reportText = """
        Отчёт о рекламной кампании
        Блогер: $bloggerName
        Ролик вышел $dateVideoShown
        Платеж за ролик: $payment рублей
        Рекламируемый товар: $productName
        Закупочная цена: $productBuyPrice рублей
        Цена продажи: $productSellPrice рублей
        Количество просмотров рекламного ролика: $videoViews
        Переходов по рекламной ссылке: $linkClicked
        Покупок товара: $salesAmount
        Общая стоимость проданного товара: $totalIncome рублей
        Прибыль от продаж: $salesProfit рублей
        Чистая прибыль: $pureProfit рублей
    """.trimIndent()
    System.out.println(reportText)

}