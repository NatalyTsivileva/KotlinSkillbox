package curswork.utils

import curswork.base.IDistributionItem
import curswork.base.IDistributor
import curswork.goods.Good
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
}


fun CoroutineScope.produceLoadingTrucks(count: Int):ReceiveChannel<IDistributor<IDistributionItem>> = produce {
    var itemsCount = count
    while (itemsCount > 0) {
        val truck = LoadingTruckGenerator.getRandomTruck()
        send(truck)
        itemsCount--
    }
    // println("тип списка:${getTypeFromList(truck.getItems())}")
}

fun CoroutineScope. openLoadingPort(
    portId: Int,
    channel: ReceiveChannel<IDistributor<IDistributionItem>>,
    getItem: suspend (clazz: KClass<*>) -> IDistributionItem?
) = launch {

    for (distributor in channel) {
        val clazz = getTypeFromList(distributor.getItems())
        val item = getItem(clazz)
        delay(item?.getTime()?:0)
        if (item != null) {
            distributor.addItem(item)
            println("Загрузка: PortID=$portId [${distributor::class.simpleName}][${distributor.hashCode()}] Выгрузил: ${item::class.simpleName}, вес: ${item.getVolume()}, за время ${item.getTime()}")
        }
    }
}

inline fun <reified T : Any> getTypeFromList(items: List<T>): KClass<T> {
    return T::class
}