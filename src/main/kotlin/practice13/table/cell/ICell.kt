package practice13.table.cell

interface ICell<T> {
    fun getId(): Int
    fun getValue(): T
    fun getCoordinate(): Coordinate

    fun getFormat(): ICellFormat<T>

    fun setValue(value: T): Boolean

    data class Coordinate(
        val rowIndex: Int,
        val columnIndex: Int
    )
}