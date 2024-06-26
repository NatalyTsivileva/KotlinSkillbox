package curswork.storage

import curswork.goods.Good
import curswork.goods.food.FoodGoods
import curswork.goods.medium.MediumGood
import curswork.goods.oversized.OversizeGood
import curswork.goods.small.SmallGood
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.*

class DistributionGoodStorage : IGoodStorage {

    private val addMutex = Mutex()
    private val getMutex = Mutex()

    private val foodGoods = LinkedList<FoodGoods>()
    private val mediumGoods = LinkedList<MediumGood>()
    private val oversizeGoods = LinkedList<OversizeGood>()
    private val smallGoods = LinkedList<SmallGood>()

    override suspend fun addItem(item: Good): Boolean {
        addMutex.withLock {
            return when (item) {
                is FoodGoods -> foodGoods.add(item)
                is MediumGood -> mediumGoods.add(item)
                is OversizeGood -> oversizeGoods.add(item)
                is SmallGood -> smallGoods.add(item)
                else -> false
            }
        }
    }


    override suspend fun fetchGoodByCategory(category: Good.GoodCategory): Good? {
        getMutex.withLock {
            return when (category) {
                Good.GoodCategory.OVERSIZE -> oversizeGoods.poll()
                Good.GoodCategory.MEDIUM -> mediumGoods.poll()
                Good.GoodCategory.SMALL -> smallGoods.poll()
                Good.GoodCategory.FOOD -> foodGoods.poll()
            }
        }
    }

}