package curswork.base

/**
 * Элемент дистрибьюции.
 */
interface IDistributionItem {
    /**
     * Узнать вес/объём товара/услуг
     */
    fun getVolume():Int

    /**
     * Узнать время, необходимое для дистрибуции элемента
     */
    fun getTime():Long
}