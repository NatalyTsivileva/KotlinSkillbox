package curswork.base

/**
 * Торговый посредник, поставляющий товары и услуги от производителя потребителю.
 */
interface IDistributor {
    /**
     * Узнать вместимость - объем услуг/товаров дестрибьютора
     */
    fun getCapacity(): Int

    /**
     * Получить предоставляемые дистрибьютором товары/услуги
     */
    fun getItems(): List<IDistributionItem>

    /**
     * Добавить услугу/товар дистрибьютору
     */
    fun addItem(item: IDistributionItem):Boolean

}