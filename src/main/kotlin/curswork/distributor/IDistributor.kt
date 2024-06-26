package curswork.distributor

/**
 * Торговый посредник, поставляющий товары и услуги от производителя потребителю.
 */
interface IDistributor<T : IDistributionItem> {
    /**
     * Узнать вместимость - объем услуг/товаров дестрибьютора
     */
    fun getCapacity(): Int

    /**
     * Получить предоставляемые дистрибьютором товары/услуги
     */
    fun getItems(): List<T>

    /**
     * Добавить услугу/товар дистрибьютору
     */
    fun addItem(item: T): Boolean

    /**
     * Полностью ли дистрибьютор заполнен товаром/услугами
     */
    fun isFull(): Boolean
}