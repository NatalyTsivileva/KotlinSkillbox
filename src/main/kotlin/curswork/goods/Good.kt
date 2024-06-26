package curswork.goods

import curswork.distributor.IDistributionItem

/**
 * Класс товаров, которые можно доставить
 */
abstract class Good : IDistributionItem {

    abstract val category: GoodCategory

    enum class GoodCategory {
        OVERSIZE, MEDIUM, SMALL, FOOD
    }
}