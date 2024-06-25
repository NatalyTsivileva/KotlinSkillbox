package practice13.table.table

import practice13.table.cell.ICell
import practice13.table.cell.ICellGenerator

abstract class AbstractTable<T, C : ICell<T>>(
    generator: ICellGenerator<T, C?>
) : ITable<T, C> {

    private val cellsList: Array<C?> = generator.generateCells()

    override fun getCell(index: Int): C? {
        return cellsList.getOrNull(index)
    }

    override fun getCell(coordinate: ICell.Coordinate): C? {
        return cellsList.find { it?.getCoordinate() == coordinate }
    }

    override fun findCell(value: T): C? {
        return cellsList.find { it?.getValue() == value }
    }

    override fun updateCell(cell: C, update: (c: C) -> C) {
        val index = cellsList.indexOf(cell)
        if (index != -1) {
            val item = cellsList[index]
            if (item != null) {
                cellsList[index] = update(item)
            }
        }
    }

    override fun getCells(): List<C?> {
        return cellsList.toList()
    }

    override fun getRow(rowNumber: Int): List<C?> = if (
        cellsList.isEmpty() ||
        rowNumber > rows ||
        rowNumber <= 0
    ) {
        emptyList()
    } else {
        val startIndex = (rowNumber - 1) * columns
        val endIndex = (startIndex + columns) - 1
        cellsList.sliceArray(startIndex..endIndex).toList()
    }

    override fun isEmpty(): Boolean {
        return cellsList.isEmpty()
    }
}