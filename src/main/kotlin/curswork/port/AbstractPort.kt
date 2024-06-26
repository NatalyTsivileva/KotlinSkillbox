package curswork.port

import curswork.distributor.IDistributionItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel

abstract class AbstractPort<T>(
    val portID: Int,
    val scope: CoroutineScope,
    val channel: ReceiveChannel<T>,
    val timeOutInMls: Long
) : IPort {

    override fun close() {
        channel.cancel()
    }

    abstract fun logPortation(portable: T, item: IDistributionItem)

}