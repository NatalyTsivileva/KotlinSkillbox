package curswork.goods.oversized

import curswork.goods.Good

/**
 * Класс крупногабаритных товаров
 */
abstract class OversizeGood : Good(){

    override val category = GoodCategory.OVERSIZE

}