package curswork.goods.food

import curswork.goods.Good

/**
 * Пищевые товары
 */
abstract class FoodGoods : Good() {

    override val category = GoodCategory.FOOD

}