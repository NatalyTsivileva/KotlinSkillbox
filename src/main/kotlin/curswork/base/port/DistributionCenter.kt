package curswork.base.port

import curswork.base.IDistributionItem
import curswork.goods.food.FoodGoods
import curswork.goods.medium.MediumGood
import curswork.goods.oversized.OversizeGood
import curswork.goods.small.SmallGood
import curswork.utils.openLoadingPort
import curswork.utils.openUnloadingPort
import curswork.utils.produceLoadingTrucks
import curswork.utils.produceUnloadingTrucks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.*
import kotlin.reflect.KClass

class DistributionCenter(
    private val scope: CoroutineScope,
    private val unloadingPortCount: Int,
    private val loadingPortCount: Int,
    countOfUnloadingTrucks: Int = 10,
    countOfLoadingTrucks: Int = 50
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
    }

    private fun createUnloadingPorts() {
        repeat(unloadingPortCount) { id ->
            val port = scope.openUnloadingPort(id, unloadingChannel, ::addItemToStore)
            unloadingPorts.add(port)
        }
        scope.launch {
            unloadingPorts.joinAll()
             printStorageInfo()
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

    suspend fun getItemByCategory(category: KClass<*>): IDistributionItem? {
        mutex.withLock {
            val item = when (category) {
                FoodGoods::class -> foodGoods.poll()
                OversizeGood::class -> oversizeGoods.poll()
                MediumGood::class -> mediumGoods.poll()
                else -> smallGoods.poll()
            }
            println(item)
            return item
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
        /* do {

             val items =
                 foodGoods.getItems() + oversizeGoods.getItems() + mediumGoods.getItems() + smallGoods.getItems()

             val truck = LoadingTruckGenerator.getRandomTruck()


             val clazz = getTypeFromList(truck.getItems())

             *//*  val items:MutableList<out Good> = when (clazz) {
                      FoodGoods::class -> foodGoods.getItemsByCategory(FoodGoods::class)
                      OversizeGood::class -> oversizeGoods.getItemsByCategory(OversizeGood::class)
                      MediumGood::class -> mediumGoods.getItemsByCategory(MediumGood::class)
                      else -> smallGoods.getItemsByCategory(SmallGood::class)
                  }
  *//*
                   *//* do {
                        items.forEach {
                            delay(it.getTime())
                            truck.addItem(it)
                        }
                    } while ()
                    //while (items.)
*//*
                }while (items.isNotEmpty())*/

        scope.launch { loadingPorts.joinAll() }
    }


    private fun printStorageInfo() {
        foodGoods.forEach {
            println(getInfo("FoodGoods",it::class,foodGoods.count()))
        }
        oversizeGoods.forEach {
            println(getInfo("OversizedGood",it::class,oversizeGoods.count()))
        }
        mediumGoods.forEach {
            println(getInfo("MediumGoods",it::class,mediumGoods.count()))
        }
        smallGoods.forEach {
            println(getInfo("SmallGoods",it::class,smallGoods.count()))
        }

    }

    private fun getInfo(name:String, itemClass:KClass<*>,count:Int)=println("Склад. Товары категории ${name} - ${itemClass.simpleName}, count=${count}")


}

