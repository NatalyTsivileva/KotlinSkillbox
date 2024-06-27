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

    private val fetchMutex = Mutex()

    private val foodGoods = LinkedList<FoodGoods>()
    private val mediumGoods = LinkedList<MediumGood>()
    private val oversizeGoods = LinkedList<OversizeGood>()
    private val smallGoods = LinkedList<SmallGood>()

    override suspend fun addItem(item: Good): Boolean = when (item) {
        is FoodGoods -> foodGoods.add(item)
        is MediumGood -> mediumGoods.add(item)
        is OversizeGood -> oversizeGoods.add(item)
        is SmallGood -> smallGoods.add(item)
        else -> false
    }

    override suspend fun fetchGoodByCategory(
        category: Good.GoodCategory,
        needFetch: (good: Good) -> Boolean
    ): Good? {
        fetchMutex.withLock {
            var removed: Good? = null

            val goods: LinkedList<out Good> = getGoodsByCategory(category)

            val item = goods.peek()

            if (item != null && needFetch(item)) {
                removed = goods.poll()
            }

            return removed
        }
    }

    private fun getGoodsByCategory(category: Good.GoodCategory): LinkedList<out Good> {
        return when (category) {
            Good.GoodCategory.OVERSIZE -> oversizeGoods
            Good.GoodCategory.MEDIUM -> mediumGoods
            Good.GoodCategory.SMALL -> smallGoods
            Good.GoodCategory.FOOD -> foodGoods
        }
    }


    fun logStorageInfo() {
        logger.logColorful("")
        val text = """
          ============= ============= ============= Storage Info ===============  =============  =============
          
                                        ${getGoodsInfo(Good.GoodCategory.FOOD)}
                                        ${getGoodsInfo(Good.GoodCategory.SMALL)}
                                        ${getGoodsInfo(Good.GoodCategory.MEDIUM)}
                                        ${getGoodsInfo(Good.GoodCategory.OVERSIZE)}
                                        
          ============= ============= ============= ============= ============= ============= ============= ==
        """.trimIndent()

        logger.logColorful(text)

    }

    private fun LinkedList<out Good>.getTypes() = this
        .groupBy { it::class }
        .map { (type, items) -> "${type.simpleName}:${items.count()}шт" }
        .joinToString(separator = ", ")


    private fun getGoodsInfo(category: Good.GoodCategory): String {
        val list = getGoodsByCategory(category)
        return "${category.name.uppercase()}(${list.count()}штук); ${list.getTypes()}"
    }
}
