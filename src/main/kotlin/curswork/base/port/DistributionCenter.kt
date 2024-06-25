package curswork.base.port

import curswork.base.IDistributionItem
import curswork.goods.Good
import curswork.goods.food.FoodGoods
import curswork.goods.medium.MediumGood
import curswork.goods.oversized.OversizeGood
import curswork.goods.small.SmallGood
import curswork.utils.openLoadingPort
import curswork.utils.openUnloadingPort
import curswork.utils.produceLoadingTrucks
import curswork.utils.produceUnloadingTrucks
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.*
import kotlin.concurrent.timerTask
import kotlin.reflect.KClass

class DistributionCenter(
    private val scope: CoroutineScope,
    private val unloadingPortCount: Int,
    private val loadingPortCount: Int,
    countOfUnloadingTrucks: Int = 10,
    countOfLoadingTrucks: Int = 5
) {


    private val unloadingPorts = mutableListOf<Job>()

    private val loadingPorts = mutableListOf<Job>()

    private val unloadingChannel = scope.produceUnloadingTrucks(countOfUnloadingTrucks)
    private val loadingChannel = scope.produceLoadingTrucks(countOfLoadingTrucks)

    private val foodGoods = LinkedList<FoodGoods>()
    private val mediumGoods = LinkedList<MediumGood>()
    private val oversizeGoods = LinkedList<OversizeGood>()
    private val smallGoods = LinkedList<SmallGood>()

    private val mutex = Mutex()


    init {
        createUnloadingPorts()
        createLoadingPorts()
        createLogger()
    }

    private fun createUnloadingPorts() {
        repeat(unloadingPortCount) { id ->
            val port = scope.openUnloadingPort(id, unloadingChannel, ::addItemToStore)
            unloadingPorts.add(port)
        }
        scope.launch {
            unloadingPorts.joinAll()
        }
    }

    private fun addItemToStore(item: IDistributionItem): Boolean = when (item) {
        is FoodGoods -> foodGoods.add(item)
        is MediumGood -> mediumGoods.add(item)
        is OversizeGood -> oversizeGoods.add(item)
        is SmallGood -> smallGoods.add(item)
        else -> false
    }


    suspend fun <T : IDistributionItem> MutableMap<KClass<*>, MutableList<T>>.getItemsByCategory(category: KClass<*>): MutableList<T> {
        mutex.withLock {
            if (this[category] == null)
                this[category] = mutableListOf()
            return this[category]!!
        }
    }

    suspend fun getItemByCategory(category: Good.GoodCategory): IDistributionItem? {
        mutex.withLock {
            return when (category) {
                Good.GoodCategory.OVERSIZE -> oversizeGoods.poll()
                Good.GoodCategory.MEDIUM -> mediumGoods.poll()
                Good.GoodCategory.SMALL -> smallGoods.poll()
                Good.GoodCategory.FOOD -> foodGoods.poll()
            }
        }
    }

    suspend fun <T : IDistributionItem> MutableMap<KClass<*>, MutableList<T>>.getItems(): List<T> {
        mutex.withLock {
            return this.values.flatten()
        }
    }


    private fun createLoadingPorts() {
        repeat(loadingPortCount) { id ->
            val job = scope.openLoadingPort(id, loadingChannel, ::getItemByCategory)
            loadingPorts.add(job)
        }
        scope.launch { loadingPorts.joinAll() }
    }

    fun createLogger() {

        val job = scope.launch {
            do {
                delay(2000)
                printStorageInfo(Good.GoodCategory.FOOD, foodGoods)
                printStorageInfo(Good.GoodCategory.OVERSIZE, oversizeGoods)
                printStorageInfo(Good.GoodCategory.SMALL, smallGoods)
                printStorageInfo(Good.GoodCategory.MEDIUM, mediumGoods)
            } while (unloadingPorts.all { it.isActive } && loadingPorts.all { it.isActive })
        }

        scope.launch {
            job.join()
        }
    }

    private fun <T : IDistributionItem> printStorageInfo(category: Good.GoodCategory, list: LinkedList<T>) {
        println("==========================================================================================")
        list.forEach { item ->
            println("Склад. Товары категории ${category.name.uppercase()} - ${item::class.simpleName}, count=${list.count()}")
        }
    }

}

