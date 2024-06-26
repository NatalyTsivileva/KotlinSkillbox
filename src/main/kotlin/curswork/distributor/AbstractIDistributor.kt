package curswork.distributor

abstract class AbstractIDistributor<T : IDistributionItem> : IDistributor<T> {

    private var isFull: Boolean = false

    private val items = mutableListOf<T>()

    override fun getItems(): List<T> {
        return items
    }

    override fun addItem(item: T): Boolean {
        val totalSum = items.sumOf { it.getVolume() }
        val isCanAdd = totalSum + item.getVolume() <= getCapacity()
        if (isCanAdd) items.add(item)
        isFull = !isCanAdd
        return isCanAdd
    }

    override fun isFull(): Boolean {
        return isFull
    }

    override fun toString(): String {
        return """
            ============================================================================
            Дистрибьютор: ${this::class.simpleName}
            Количество элементов дистрибуции: ${items.count()}
            Доступный объем товаров/услуг: ${getCapacity()}
            Суммарный вес/объём элементов дистрибуции: ${items.sumOf { it.getVolume() }}   
            ============================================================================
            Элементы дистрибуции:[${items.map { it::class.simpleName }.joinToString(", ")}]
            
            
        """.trimIndent()
    }

}