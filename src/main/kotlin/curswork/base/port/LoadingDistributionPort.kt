package curswork.base.port

import curswork.base.IDistributionItem
import curswork.base.IDistributor
import curswork.utils.UnloadingTruckGenerator
import curswork.utils.getTypeFromList
import kotlinx.coroutines.delay

/*class LoadingDistributionPort : AbstractPort() {

    private var isFree = true

    suspend fun startLoading(items: List<IDistributionItem>) {
        var distributor = getFirstDistributor()

        if (distributor == null) {
            distributor = UnloadingTruckGenerator.getRandomLoadingTruck()
            addDistributor(distributor)
        }

        val distributorItemsType = getTypeFromList(distributor.getItems())
        val currentItemsTypes = getTypeFromList(items)

        if (distributorItemsType == currentItemsTypes) {
            items.forEach { item ->
                delay(item.getTime())
                distributor.addItem(item)
                val processText = getDistributionProcessText(distributor, item)
                _distributionProcess.emit(DistributionProcess.InProgress(item, processText))
            }
        }
    }


    override fun getDistributionProcessText(
        distributor: IDistributor<IDistributionItem>,
        item: IDistributionItem
    ): String {
        return "ЗАГРУЗКА:::: Дистрибьютор:[${distributor::class.simpleName}] [${distributor.hashCode()}] загрузил товар: ${item::class.simpleName}, вес: ${item.getVolume()} за время ${item.getTime()}"
    }
}*/
