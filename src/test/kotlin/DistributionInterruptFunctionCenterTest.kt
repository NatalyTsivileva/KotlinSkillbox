import curswork.DistributionCenter
import curswork.goods.food.FoodGoods
import curswork.utils.UnloadingTruckGenerator
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DistributionInterruptFunctionCenterTest {

    @Test
    fun testTruckGenerator() {
        val truck = UnloadingTruckGenerator.getRandomTruck()

        val itemsVolume = truck.getItems().sumOf { it.getVolume() }
        val isAllFood = truck.getItems().all { it is FoodGoods }
        val isNonGoodFood = truck.getItems().all { it !is FoodGoods }

        println(truck)

        Assertions.assertTrue(truck.getItems().isNotEmpty())
        Assertions.assertTrue(itemsVolume <= truck.getCapacity())
        Assertions.assertTrue(isAllFood || isNonGoodFood)
    }

    @Test
    fun testUnloadingDistributionPort(): Unit = runBlocking {
        val center = DistributionCenter(
            scope = this,
            unloadingPortCount = 5,
            loadingPortCount = 3,
            countOfUnloadingTrucks = 200,
            countOfLoadingTrucks = 500
        )
    }
}