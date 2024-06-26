package curswork.base.port

import curswork.base.IDistributionItem
import curswork.base.IDistributor
import kotlinx.coroutines.delay

class UnloadingDistributionPort(
    val interruptFun: InterruptFunction = hundredInterruptFunction
) : AbstractPort() {

    private var isFree = true

    private var distributorNumber = 0


    suspend fun startUnloading() {
        do {
            val distributor = getNextDistributor()

            if (distributor !== null) {
                distributorNumber++

                distributor.getItems().forEach { item ->
                    isFree = false
                    delay(item.getTime())
                    val stateText = getDistributionProcessText(distributor, item)

                    _distributionProcess.emit(
                        DistributionProcess.InProgress(item, stateText)
                    )
                }
            }
            isFree = true
        } while (distributor!=null)

        _distributionProcess.emit(DistributionProcess.Finished())
    }



    override fun getDistributionProcessText(
        distributor: IDistributor,
        item: IDistributionItem
    ): String {
        return "ВЫГРУЗКА::: Дистрибьютор:[${distributor::class.simpleName}] [${distributor.hashCode()}] выгрузил товар: ${item::class.simpleName}, вес: ${item.getVolume()} за время ${item.getTime()}"
    }
}