import practice13.game.LottoGamer
import practice13.table.cell.impl.DrawableCell
import practice13.table.cell.impl.LottoCellsGenerator
import practice13.table.table.LottoTable
import practice13.table.table.LottoTableSettings

val defaultSettings = LottoTableSettings(
    rowsCount = 3,
    columnsCount = 9,
    maxValue = 90,
    winCountInRow = 5
)

fun generateGamers(
    settings: LottoTableSettings,
    tableCount: Int
): List<LottoGamer> {

    val cellsGenerator = LottoCellsGenerator(settings)

    val firstGamer = LottoGamer("Василий", cellsGenerator.generateTables(tableCount, settings))
    val secondGamer = LottoGamer("Инна", cellsGenerator.generateTables(tableCount, settings))
    val thirdGamer = LottoGamer("Семен", cellsGenerator.generateTables(tableCount, settings))

    return listOf(
        firstGamer,
        secondGamer,
        thirdGamer
    )
}


fun LottoCellsGenerator.generateTables(
    count: Int,
    settings: LottoTableSettings
) = (0 until count).map {
    LottoTable(
        settings = settings,
        generator = this
    )
}

fun List<DrawableCell?>.printInfo() {
    forEach {
        val text = it?.getValue() ?: "NULL"
        print(" $text")
    }
    println()
}