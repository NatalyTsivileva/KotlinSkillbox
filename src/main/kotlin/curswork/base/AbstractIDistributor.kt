package curswork.base

abstract class AbstractIDistributor : IDistributor {

    private val items = mutableListOf<IDistributionItem>()

    override fun getItems(): List<IDistributionItem> {
        return items
    }

    override fun addItem(item: IDistributionItem): Boolean {
        val totalSum = items.sumOf { it.getVolume() }
        val isCanAdd = totalSum + item.getVolume() <= getCapacity()
        if (isCanAdd) items.add(item)
        return isCanAdd
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