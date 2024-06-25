package practice13.table.cell


/**
 *
 */
interface ICellGenerator<T, C : ICell<T>?> {
    fun generateCells(): Array<C?>
}