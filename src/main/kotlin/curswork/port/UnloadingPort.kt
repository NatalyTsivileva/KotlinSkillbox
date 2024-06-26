package curswork.port

import curswork.distributor.IDistributionItem
import curswork.goods.Good
import curswork.logger.ColorfulLogger
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
    val saveItemFunction: suspend (item: Good) -> Boolean,
    val logger: ColorfulLogger
) : AbstractPort<AnyDistributor>(scope = scope, portID = portID, channel = channel, timeOutInMls = timeOutInMls) {

    private var job: Job? = null

    override fun open() {
        job = scope.launch {
            try {
                withTimeout(timeOutInMls) {
                    for (distributor in channel) {

                        distributor.getItems().forEach { item ->
                            delay(item.getTime())

                            (item as? Good)?.apply {
                               val saved = saveItemFunction(this)
                                if (saved) logPortation(distributor, item)
                            }


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
          val text =
            "\tВЫГРУЗКА: PortID=$portID [${portable::class.simpleName}][${portable.hashCode()}] Выгрузил: ${item::class.simpleName}, вес: ${item.getVolume()}, за время ${item.getTime()}"
        logger.logColorful(text, ColorfulLogger.Color.GREEN)
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