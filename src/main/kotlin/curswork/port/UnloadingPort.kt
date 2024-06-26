package curswork.port

import curswork.distributor.IDistributionItem
import curswork.goods.Good
import curswork.types.AnyDistributor
import curswork.utils.UnloadingTruckGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UnloadingPort(
    scope: CoroutineScope,
    portID: Int,
    channel: ReceiveChannel<AnyDistributor>,
    val saveItemFunction: suspend (item: Good) -> Boolean
) : AbstractPort<AnyDistributor>(scope = scope, portID = portID, channel = channel) {


    override fun open() {
        scope.launch {
            for (distributor in channel) {

                distributor.getItems().forEach { item ->
                    delay(item.getTime())

                    (item as? Good)?.apply { saveItemFunction(this) }

                    logPortation(distributor, item)
                }
            }
        }
    }

    override fun logPortation(portable: AnyDistributor, item: IDistributionItem) {
        val greenColor = "\u001B[32m"
        println("\t${greenColor}ВЫГРУЗКА: PortID=$portID [${portable::class.simpleName}][${portable.hashCode()}] Выгрузил: ${item::class.simpleName}, вес: ${item.getVolume()}, за время ${item.getTime()}")
    }

    companion object {

        fun createUnloadingChannel(truckCount: Int, scope: CoroutineScope) = scope.produce {
            var itemsCount = truckCount
            while (itemsCount > 0) {
                val truck = UnloadingTruckGenerator.getRandomTruck()
                send(truck)
                itemsCount--
                delay(1000)
            }
        }
    }

}