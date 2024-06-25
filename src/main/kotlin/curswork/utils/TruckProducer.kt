package curswork.utils

import curswork.base.IDistributionItem
import curswork.base.IDistributor
import curswork.goods.Good
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

fun CoroutineScope.produceUnloadingTrucks(count: Int) = produce {
    var itemsCount = count
    while (itemsCount > 0) {
        val truck = UnloadingTruckGenerator.getRandomTruck()
        send(truck)
        itemsCount--
        delay(1000)
    }
    cancel()
}

fun CoroutineScope.openUnloadingPort(
    portId: Int,
    channel: ReceiveChannel<IDistributor<IDistributionItem>>,
    saveItemFun: suspend (item: IDistributionItem) -> Boolean
) = launch {
    for (distributor in channel) {
        val redColor = "\u001B[31m"

        distributor.getItems().forEach {
            delay(it.getTime())
            saveItemFun(it)
            println("\t${redColor}ВЫГРУЗКА: PortID=$portId [${distributor::class.simpleName}][${distributor.hashCode()}] Выгрузил: ${it::class.simpleName}, вес: ${it.getVolume()}, за время ${it.getTime()}")
        }
    }
    cancel()
}

typealias AnyDistributor = IDistributor<IDistributionItem>
typealias AnyDistributorWithGoodCategory = Pair<AnyDistributor, Good.GoodCategory>

fun CoroutineScope.produceLoadingTrucks(count: Int): ReceiveChannel<AnyDistributorWithGoodCategory> = produce {
    var itemsCount = count
    while (itemsCount > 0) {
        val truck = LoadingTruckGenerator.createLoadingTruck<IDistributionItem>()
        val type = Good.GoodCategory.values().random()
        send(truck to type)
        itemsCount--
    }
    // println("тип списка:${getTypeFromList(truck.getItems())}")
    cancel()
}

fun CoroutineScope.openLoadingPort(
    portId: Int,
    channel: ReceiveChannel<AnyDistributorWithGoodCategory>,
    getItem: suspend (category: Good.GoodCategory) -> IDistributionItem?
) = launch {

    for (pair in channel) {
        do {
            val distributor = pair.first
            val item = getItem(pair.second)
            delay(10)
            if (item != null) {
                delay(item.getTime())
                val isItemAdded = distributor.addItem(item)
                if(!isItemAdded) break
                val freePlace = distributor.getCapacity() - distributor.getItems().sumOf { it.getVolume() }
                println("Загрузка: PortID=$portId [${distributor::class.simpleName}][${distributor.hashCode()}] ${item::class.simpleName}, вес: ${item.getVolume()}, за время ${item.getTime()}. Осталось места: $freePlace")
            }
        } while (!distributor.isFull())
    }
    cancel()
}

inline fun <reified T : Any> getTypeFromList(items: List<T>): KClass<T> {
    return T::class
}