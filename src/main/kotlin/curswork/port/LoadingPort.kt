package curswork.port

import curswork.distributor.IDistributionItem
import curswork.goods.Good
import curswork.logger.ColorfulLogger
import curswork.trucks.FireTruck
import curswork.trucks.TankerTruck
import curswork.types.AnyDistributor
import curswork.types.AnyGoodsDistributor
import curswork.types.FetchAnyGoodFunction
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlin.random.Random

class LoadingPort(
    portID: Int,
    scope: CoroutineScope,
    channel: ReceiveChannel<AnyGoodsDistributor>,
    timeOutInMls: Long,
    val fetchItemFunction: FetchAnyGoodFunction,
    val logger: ColorfulLogger
) : AbstractPort<AnyGoodsDistributor>(portID = portID, scope = scope, channel = channel, timeOutInMls = timeOutInMls) {

    private var job: Job? = null

    override fun open() {
        job = scope.launch {
            try {
                withTimeout(timeOutInMls) {

                    for (pair in channel) {

                        do {
                            val distributor = pair.first
                            val goodCategory = pair.second
                            val item = fetchItemFunction(goodCategory, distributor::addItem)
                            delay(10)
                            if (item != null) {
                                logPortation(pair, item)
                            }
                        } while (!distributor.isFull())

                        logDistributorFetching(pair)
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

    override fun logPortation(portable: AnyGoodsDistributor, item: IDistributionItem) {
        val distributor = portable.first
        val freePlace = distributor.getCapacity() - distributor.getItems().sumOf { it.getVolume() }
        val distributorName = "${distributor::class.simpleName}[ID=${distributor.hashCode()}]"
        val itemName = "${item::class.simpleName}[${item.hashCode()}]"
        val categoryName = portable.second.name.uppercase()

        val color = ColorfulLogger.Color.RED
        var text = "\tЗАГРУЗКА: PortID=$portID $distributorName ГРУЗИТ ТОЛЬКО $categoryName"
        logger.logColorful(text, color)

        text = " \t\t$itemName вес: ${item.getVolume()}, за время ${item.getTime()}. Осталось места: $freePlace"
        logger.logColorful(text, color)
    }


    private fun logDistributorFetching(portable: AnyGoodsDistributor) {
        val text =
            "\tПолностью загружен и выехал из порта: PortID=$portID ${portable.first::class.simpleName}, категория:${portable.second.name.uppercase()}"
        logger.logColorful(text)
    }


    companion object {
        fun createLoadingChannel(scope: CoroutineScope) = scope.produce {
            while (isActive) {
                val truck: AnyDistributor = if (Random.nextBoolean()) FireTruck() else TankerTruck()
                val type = Good.GoodCategory.values().random()
                send(truck to type)
            }
        }
    }

}