package practice13.table.table

import practice13.table.cell.ICell

/**
 * Базовый интерфейс для таблиц
 *
 * Интерфейс, описывающий таблицу и что можно делать с ячейками в таблице
 *
 * @param T тип данных внутри ячейки таблицы
 * @param C тип данных для самой ячейки
 */
interface ITable<T, C : ICell<T>> {
    /**
     * Количество рядов в таблице
     */
    val rows: Int

    /**
     * Количество столбцов в таблице
     */
    val columns: Int

    /**
     * Получить ячейку по индексу
     */
    fun getCell(index: Int): C?

    /**
     * Получить ячейку по координате
     */
    fun getCell(coordinate: ICell.Coordinate): C?

    /**
     * Обновить значение ячейки
     * @param cell ячейка для обновления
     * @param update функция, которая создаёт новую ячейку - реплику к уже имеющейся
     */
    fun updateCell(cell: C, update: (c: C) -> C)

    /**
     * Найти ячейку по значению
     */
    fun findCell(value: T): C?

    /**
     * Получить список всех ячеек
     */
    fun getCells(): List<C?>

    /**
     * По номеру ряда получить все ячейки из этого ряда
     */
    fun getRow(rowNumber: Int): List<C?>

    /**
     * Проверить, пуста ли таблица
     */
    fun isEmpty():Boolean
}