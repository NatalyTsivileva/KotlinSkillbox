package curswork

import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) = runBlocking {
    val center = DistributionCenter(
        scope = this,
        unloadingPortCount = 5,
        loadingPortCount = 3,
        workTimeInMls = TimeUnit.MINUTES.toMillis(1)
    )
}