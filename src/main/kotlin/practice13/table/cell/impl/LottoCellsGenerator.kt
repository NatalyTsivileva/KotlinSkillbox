package practice13.table.cell.impl

import practice13.table.cell.ICellGenerator
import practice13.table.table.LottoTableSettings
import practice13.utils.asCoordinate
import java.util.*

class LottoCellsGenerator(
    val settings: LottoTableSettings
) : ICellGenerator<Int, DrawableCell?> {


    override fun generateCells(): Array<DrawableCell?> {

        if (!isLottoTableValid()) return emptyArray()

        /*все значения выйгрышных номеров*/
        val winValues = LinkedList((0 until settings.maxValue).shuffled())

        /* индексы в ряду(!), которым будут установлены выйгрышные номера*/
        var validColumnIndexes = emptyList<Int>()

        var row = -1

        return Array(getTableSize()) { index ->

            val cellCoordinate = index.asCoordinate(settings.columnsCount)

            /*начало следующего ряда в матрице ("нитка" в данном случае)*/
            if (cellCoordinate.rowIndex != row) {

                /*если начался новый ряд, то берём следущую пачку индексов(номера столбцов) с выйгрышными значениями*/
                validColumnIndexes = (0 until settings.columnsCount)
                    .shuffled()
                    .take(settings.winCountInRow)

                row = cellCoordinate.rowIndex
            }

            /* если индекс столбца для текущего ряда входит в выйгрышные индексы столбцов,
            то создаём ячейку со значением из winValue, иначе делаем "дырку" в ряду  */
            if (cellCoordinate.columnIndex in validColumnIndexes) {
                DrawableCell(
                    id = index,
                    value = winValues.pop(),
                    coordinate = index.asCoordinate(settings.columnsCount)
                )
            } else {
                null
            }
        }
    }

    private fun getTableSize() = settings.rowsCount * settings.columnsCount

    private fun isLottoTableValid() = settings.maxValue >= settings.rowsCount * settings.winCountInRow


}