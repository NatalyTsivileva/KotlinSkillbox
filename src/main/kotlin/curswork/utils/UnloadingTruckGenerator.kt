package curswork.utils

import curswork.base.IDistributionItem
import curswork.base.IDistributor
import curswork.goods.food.Bread
import curswork.goods.food.Milk
import curswork.goods.food.Potato
import curswork.goods.medium.Chair
import curswork.goods.medium.Mattress
import curswork.goods.medium.Mirror
import curswork.goods.oversized.Bed
import curswork.goods.oversized.Fridge
import curswork.goods.oversized.Oven
import curswork.goods.small.Cosmetic
import curswork.goods.small.MobilePhone
import curswork.goods.small.Toy
import curswork.trucks.FireTruck
import curswork.trucks.TankerTruck
import curswork.trucks.VanTruck
import kotlin.random.Random

object UnloadingTruckGenerator {

    fun getRandomTruck(): IDistributor {
        val truck: IDistributor = when (Random.nextInt(0, 3)) {
            0 -> FireTruck()
            1 -> TankerTruck()
            else -> VanTruck()
        }
        fillTruck(truck)
        return truck
    }

    private fun fillTruck(truck: IDistributor) {
        val isFoodItem = Random.nextBoolean()
        var count = Random.nextInt(1, 1000)
        while (count > 0) {
            val item = if (isFoodItem) createFoodGood() else createNonFoodGood()
            truck.addItem(item)
            count--
        }
    }

    private fun createFoodGood() = when (Random.nextInt(0, 3)) {
        0 -> Bread()
        1 -> Milk()
        else -> Potato()
    }

    private fun createNonFoodGood() = when (Random.nextInt(0, 9)) {
        0 -> Chair()
        1 -> Mattress()
        2 -> Mirror()
        3 -> Bed()
        4 -> Fridge()
        5 -> Oven()
        6 -> Cosmetic()
        7 -> MobilePhone()
        else -> Toy()
    }
}