package curswork.storage

import curswork.goods.Good
import curswork.goods.food.FoodGoods
import curswork.goods.medium.MediumGood
import curswork.goods.oversized.OversizeGood
import curswork.goods.small.SmallGood
import curswork.logger.ColorfulLogger
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.*

class DistributionGoodStorage(
    private val logger: ColorfulLogger
) : IGoodStorage {

    private val mutex = Mutex()

    private val foodGoods = LinkedList<FoodGoods>()
    private val mediumGoods = LinkedList<MediumGood>()
    private val oversizeGoods = LinkedList<OversizeGood>()
    private val smallGoods = LinkedList<SmallGood>()

    override suspend fun addItem(item: Good): Boolean {
        val added = when (item) {
            is FoodGoods -> foodGoods.add(item)
            is MediumGood -> mediumGoods.add(item)
            is OversizeGood -> oversizeGoods.add(item)
            is SmallGood -> smallGoods.add(item)
            else -> false
        }
        return added
    }

    override suspend fun fetchGoodByCategory(
        category: Good.GoodCategory,
        needFetch: (good: Good) -> Boolean
    ): Good? {
        mutex.withLock {
            var removed: Good? = null

            val goods: LinkedList<out Good> = getGoodsByCategory(category)

            val item = goods.peek()

            if (item != null && needFetch(item)) {
                removed = goods.poll()
            }

            return removed
        }
    }

    private fun getGoodsByCategory(category: Good.GoodCategory) = when (category) {
        Good.GoodCategory.OVERSIZE -> oversizeGoods
        Good.GoodCategory.MEDIUM -> mediumGoods
        Good.GoodCategory.SMALL -> smallGoods
        Good.GoodCategory.FOOD -> foodGoods
    }



    fun logStorageInfo() {
        logger.logColorful("")
        logger.logColorful("============= Storage Info ===============")
        logger.logColorful("${Good.GoodCategory.FOOD.name.uppercase()} count=${foodGoods.count()}")
        logger.logColorful("${Good.GoodCategory.SMALL.name.uppercase()} count=${smallGoods.count()}")
        logger.logColorful("${Good.GoodCategory.MEDIUM.name.uppercase()} count=${mediumGoods.count()}")
        logger.logColorful("${Good.GoodCategory.OVERSIZE.name.uppercase()} count=${oversizeGoods.count()}")
    }
}
