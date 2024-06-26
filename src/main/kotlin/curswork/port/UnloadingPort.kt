package curswork.port

import curswork.distributor.IDistributionItem
import curswork.goods.Good
import curswork.types.AnyDistributor
import curswork.utils.UnloadingTruckGenerator
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

class UnloadingPort(
    scope: CoroutineScope,
    portID: Int,
    channel: ReceiveChannel<AnyDistributor>,
    timeOutInMls: Long,
    val saveItemFunction: suspend (item: Good) -> Boolean
) : AbstractPort<AnyDistributor>(scope = scope, portID = portID, channel = channel, timeOutInMls = timeOutInMls) {

    private var job: Job? = null

    override fun open() {
        job = scope.launch {
            try {
                withTimeout(timeOutInMls) {
                    for (distributor in channel) {

                        distributor.getItems().forEach { item ->
                            delay(item.getTime())

                            (item as? Good)?.apply { saveItemFunction(this) }

                            logPortation(distributor, item)
                        }
                    }
                }
            } finally {
                close()
            }
        }
    }

    override fun close() {
        super.close()
        job?.cancel()
    }

    override fun logPortation(portable: AnyDistributor, item: IDistributionItem) {
        val greenColor = "\u001B[32m"
        println("\t${greenColor}ВЫГРУЗКА: PortID=$portID [${portable::class.simpleName}][${portable.hashCode()}] Выгрузил: ${item::class.simpleName}, вес: ${item.getVolume()}, за время ${item.getTime()}")
    }

    companion object {

        fun createUnloadingChannel(scope: CoroutineScope) = scope.produce {
            while (isActive) {
                val truck = UnloadingTruckGenerator.getRandomTruck()
                send(truck)
                delay(1000)
            }
        }
    }

}