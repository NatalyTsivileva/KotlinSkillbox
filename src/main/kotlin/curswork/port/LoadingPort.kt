package curswork.port

import curswork.distributor.IDistributionItem
import curswork.goods.Good
import curswork.trucks.FireTruck
import curswork.trucks.TankerTruck
import curswork.types.AnyDistributor
import curswork.types.AnyGoodsDistributor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class LoadingPort(
    portID: Int,
    scope: CoroutineScope,
    channel: ReceiveChannel<AnyGoodsDistributor>,
    val getItemFunction: suspend (category: Good.GoodCategory) -> IDistributionItem?
) : AbstractPort<AnyGoodsDistributor>(portID = portID, scope = scope, channel = channel) {


    override fun open() {
        scope.launch {
            for (pair in channel) {
                do {
                    val distributor = pair.first
                    val item = getItemFunction(pair.second)
                    delay(10)

                    if (item != null) {
                        delay(item.getTime())

                        val isItemAdded = distributor.addItem(item)

                        if (!isItemAdded) break

                        logPortation(pair, item)
                    }

                } while (!distributor.isFull())
            }
        }
    }

    override fun logPortation(portable: AnyGoodsDistributor, item: IDistributionItem) {
        val distributor = portable.first
        val freePlace = distributor.getCapacity() - distributor.getItems().sumOf { it.getVolume() }
        val redColor = "\u001B[31m"

        println("\t\t${redColor}ЗАГРУЗКА: PortID=$portID [${distributor::class.simpleName}][${distributor.hashCode()}] ${item::class.simpleName}, вес: ${item.getVolume()}, за время ${item.getTime()}. Осталось места: $freePlace")
    }

    companion object {
        fun createLoadingChannel(truckCount: Int, scope: CoroutineScope) = scope.produce {
            var itemsCount = truckCount
            while (itemsCount > 0) {
                val truck: AnyDistributor = if (Random.nextBoolean()) FireTruck() else TankerTruck()
                val type = Good.GoodCategory.values().random()
                send(truck to type)
                itemsCount--
            }
        }
    }

}