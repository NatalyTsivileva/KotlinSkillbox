package curswork.port

import curswork.distributor.IDistributionItem
import curswork.goods.Good
import curswork.trucks.FireTruck
import curswork.trucks.TankerTruck
import curswork.types.AnyDistributor
import curswork.types.AnyGoodsDistributor
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlin.random.Random

class LoadingPort(
    portID: Int,
    scope: CoroutineScope,
    channel: ReceiveChannel<AnyGoodsDistributor>,
    val getItemFunction: suspend (category: Good.GoodCategory) -> IDistributionItem?
) : AbstractPort<AnyGoodsDistributor>(portID = portID, scope = scope, channel = channel) {

    private var job: Job? = null

    override fun open() {
        job = scope.launch {

            for (pair in channel) {

                do {
                    val distributor = pair.first
                    val item = getItemFunction(pair.second)
                    delay(10)

                    if (item != null) {
                        delay(item.getTime())
                        distributor.addItem(item)
                        logPortation(pair, item)
                    }

                } while (!distributor.isFull())
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
        val itemName = item::class.simpleName
        val categoryName = portable.second.name.uppercase()

        val redColor = "\u001B[31m"
        println("\t${redColor}ЗАГРУЗКА: PortID=$portID $distributorName ГРУЗИТ ТОЛЬКО $categoryName")
        println(" \t\t${redColor}[$itemName] вес: ${item.getVolume()}, за время ${item.getTime()}. Осталось места: $freePlace")
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