package curswork.utils

import curswork.distributor.IDistributionItem
import curswork.distributor.IDistributor
import curswork.goods.Good
import curswork.goods.Good.GoodCategory.*
import curswork.goods.food.FoodGoods
import curswork.goods.medium.MediumGood
import curswork.goods.oversized.OversizeGood
import curswork.goods.small.SmallGood
import curswork.trucks.FireTruck
import curswork.trucks.TankerTruck
import kotlin.random.Random

object LoadingTruckGenerator {

    fun getRandomTruck(): IDistributor<out Good> = when (Good.GoodCategory.values().random()) {
        OVERSIZE -> createLoadingTruck<OversizeGood>()
        MEDIUM -> createLoadingTruck<MediumGood>()
        SMALL -> createLoadingTruck<SmallGood>()
        FOOD -> createLoadingTruck<FoodGoods>()
    }

    fun <T : IDistributionItem> createLoadingTruck(): IDistributor<T> =
        if (Random.nextBoolean()) FireTruck<T>() else TankerTruck<T>()
}