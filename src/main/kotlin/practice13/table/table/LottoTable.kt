package practice13.table.table

import practice13.table.cell.impl.DrawableCell
import practice13.table.cell.impl.LottoCellsGenerator
import practice13.utils.asCoordinate


class LottoTable(
    val settings: LottoTableSettings,
    generator: LottoCellsGenerator,
) : AbstractTable<Int, DrawableCell>(generator) {

    override val rows: Int = settings.rowsCount

    override val columns: Int = settings.columnsCount

    override fun toString(): String {
        var text = ""
        var row = 0

        for (i in 0 until rows * columns) {
            val currentRow = i.asCoordinate(columns)

            if (currentRow.rowIndex != row) {
                text += "\n"
                row = currentRow.rowIndex
            }

            val value = when {
                getCell(i)?.isPainted() == true -> getCell(i)?.getDrawable() ?: ""
                getCell(i)?.getValue() == null -> "  "
                else -> "${getCell(i)?.getValue()}"
            }

            text += " [$value] "
        }
        return "\n$text\n"
    }
}