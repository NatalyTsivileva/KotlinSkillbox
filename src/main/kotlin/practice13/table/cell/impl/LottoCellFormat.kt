package practice13.table.cell.impl

import practice13.table.cell.ICellFormat

class LottoCellFormat : ICellFormat<Int> {

    private var minValue = DEFAULT_MIN_VALUE

    private var maxValue = DEFAULT_MAX_VALUE

    override fun setMaxValue(value: Int) {
        maxValue = value
    }

    override fun setMinValue(value: Int) {
        minValue = value
    }

    override fun getMaxValue(): Int {
        return maxValue
    }

    override fun getMinValue(): Int {
        return minValue
    }

    companion object {
        const val DEFAULT_MIN_VALUE = 1
        const val DEFAULT_MAX_VALUE = 90
    }
}