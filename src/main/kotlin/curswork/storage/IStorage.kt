package curswork.storage

import curswork.distributor.IDistributionItem

interface IStorage<T:IDistributionItem> {

    suspend fun addItem(item: T): Boolean

}