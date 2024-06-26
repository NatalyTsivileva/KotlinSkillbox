package curswork

import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking{
    val center = DistributionCenter(
        scope = this,
        unloadingPortCount = 5,
        loadingPortCount = 3,
        countOfUnloadingTrucks = 200,
        countOfLoadingTrucks = 200
    )
}