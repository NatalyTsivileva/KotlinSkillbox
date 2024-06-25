package curswork.utils

import curswork.base.IDistributionItem
import curswork.base.IDistributor
import kotlinx.coroutines.CoroutineScope
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
}

fun CoroutineScope.openUnloadingPort(
    portId: Int,
    channel: ReceiveChannel<IDistributor>,
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
}


fun CoroutineScope.produceLoadingTrucks(count: Int): ReceiveChannel<IDistributor> = produce {
    var itemsCount = count
    while (itemsCount > 0) {
        val truck = LoadingTruckGenerator.getRandomTruck()
        send(truck)
        itemsCount--
    }
}

fun CoroutineScope.openLoadingPort(
    portId: Int,
    channel: ReceiveChannel<IDistributor>,
    getItem: suspend (clazz: KClass<*>) -> IDistributionItem?
) = launch {
    delay(2000)

    for (distributor in channel) {
        do {
            var isAdded = false
            val clazz = getTypeFromList(distributor.getItems())
            val item = getItem(clazz)
            delay(item?.getTime() ?: 0)
            if (item != null) {
                isAdded = distributor.addItem(item)
                println("Загрузка: PortID=$portId [${distributor::class.simpleName}][${distributor.hashCode()}] Выгрузил: ${item::class.simpleName}, вес: ${item.getVolume()}, за время ${item.getTime()}")
            }
        } while (isAdded)
    }
}

inline fun <reified T : Any> getTypeFromList(items: List<T>): KClass<T> {
    return T::class
}