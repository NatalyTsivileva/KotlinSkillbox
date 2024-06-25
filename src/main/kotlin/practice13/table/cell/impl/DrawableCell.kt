package practice13.table.cell.impl

import practice13.table.cell.ICell
import practice13.table.cell.ICellFormat

class DrawableCell(
    id: Int,
    value: Int,
    format: ICellFormat<Int> = LottoCellFormat(),
    coordinate: ICell.Coordinate,
) : AbstractCell<Int>(
    id = id,
    value = value,
    format = format,
    coordinate = coordinate
) {

    private var isPaintOver: Boolean = false

    fun paintOver() {
        isPaintOver = true
    }

    fun isPainted() = isPaintOver

    fun getDrawable() = " * "

}
