package practice13.utils

import practice13.table.cell.ICell
import kotlin.math.abs

/**
 * Функция, которая по индексу определяет какой ряд и столбец занимает элемент.
 *
 *  Например для таблицы
 *
 *[0] [14] [10] [29]
 *[1] [19] [20] [12]
 * элемент с индексом 5 будет иметь ряд=2, столбец=2 (и значение 19).
 * Счет происходит слево направо, сверху вниз.
 *
 * @param columnsCount количество столбцов подразумеваемой таблице
 */
fun Int.asCoordinate(columnsCount: Int): ICell.Coordinate = if (columnsCount > 0) {
    val rowIndex = this / columnsCount
    val columnIndex = abs((rowIndex * columnsCount) - this)
    ICell.Coordinate(
        rowIndex = rowIndex,
        columnIndex = columnIndex
    )
} else {
    ICell.Coordinate(-1, -1)
}

