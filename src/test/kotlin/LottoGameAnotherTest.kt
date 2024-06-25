import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import practice13.table.table.LottoTableSettings

class LottoGameAnotherTest {

    @Test
    fun `when get rows - while valid table settings - should return list of values`() {
        val gamers = generateGamers(defaultSettings, 5)
        val firstGamer = gamers.first()
        println(firstGamer.cards)

        val lastCard = firstGamer.cards.last()

        for (rowIndex in 0 until 10) {
            val rowCells = lastCard.getRow(rowIndex)
            rowCells.printInfo()

            when (rowIndex) {
                in 1..defaultSettings.rowsCount -> {
                    Assertions.assertTrue(rowCells.count() == defaultSettings.columnsCount)
                }

                else -> {
                    Assertions.assertTrue(rowCells.isEmpty())
                }
            }
        }
    }

    @Test
    fun `when get rows - while invalid table settings - should return empty list of values`() {
        val invalidSettings = LottoTableSettings(
            rowsCount = 0,
            columnsCount = 0,
            maxValue = 40,
            winCountInRow = 190
        )
        val gamers = generateGamers(invalidSettings, 5)
        val firstGamer = gamers.first()
        Assertions.assertTrue(firstGamer.cards.all { it.isEmpty() })

        for (rowIndex in 0 until 10) {
            val rowCells = firstGamer.cards.last().getRow(rowIndex)
            Assertions.assertTrue(rowCells.isEmpty())
        }

    }
}