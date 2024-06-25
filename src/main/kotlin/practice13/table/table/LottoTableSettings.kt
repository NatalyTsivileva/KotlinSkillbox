package practice13.table.table

data class LottoTableSettings (
    /**
     * Количество рядов в карточке ЛОТО
     */
    val rowsCount: Int,

    /**
     * Количество столбцов в карточке ЛОТО
     */
    val columnsCount: Int,

    /**
     * Максимальное возможное значение в ячейке ЛОТО
     */
    val maxValue: Int,

    /**
     * Количество выйгрышных слотов в ряду
     */
    val winCountInRow: Int
)