package practice13.table.cell.impl

import practice13.table.cell.ICell
import practice13.table.cell.ICellFormat

abstract class AbstractCell<T>(
    private val id: Int,
    private var value: T,
    private val format: ICellFormat<T>,
    private val coordinate: ICell.Coordinate,
) : ICell<T> {


    override fun getId(): Int {
        return id
    }


    override fun getCoordinate(): ICell.Coordinate {
        return coordinate
    }

    override fun getFormat(): ICellFormat<T> {
        return format
    }

    override fun getValue(): T {
        return value
    }

    override fun setValue(
        value: T
    ): Boolean {
        return if (this.value != value) {
            this.value = value
            true
        } else {
            false
        }
    }
}