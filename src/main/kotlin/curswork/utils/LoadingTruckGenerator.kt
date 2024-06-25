package curswork.utils

import curswork.base.IDistributionItem
import curswork.base.IDistributor
import curswork.goods.Good
import curswork.goods.food.FoodGoods
import curswork.goods.medium.MediumGood
import curswork.goods.oversized.OversizeGood
import curswork.goods.small.SmallGood
import curswork.trucks.FireTruck
import curswork.trucks.TankerTruck
import curswork.trucks.Truck
import kotlin.random.Random

object LoadingTruckGenerator {

    fun getRandomTruck(): IDistributor =  if (Random.nextBoolean()) FireTruck() else TankerTruck() /*when (Random.nextInt(0, 4)) {
        0 -> createLoadingTruck<FoodGoods>()
        1 -> createLoadingTruck<MediumGood>()
        2 -> createLoadingTruck<OversizeGood>()
        else -> createLoadingTruck<SmallGood>()*/

}