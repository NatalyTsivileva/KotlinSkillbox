package practice13.table.cell

interface ICellFormat<T> {
    fun setMaxValue(value: T)

    fun setMinValue(value: T)

    fun getMaxValue(): T

    fun getMinValue(): T
}