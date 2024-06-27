package curswork.port

import curswork.distributor.IDistributionItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.ReceiveChannel

abstract class AbstractPort<T>(
    val portID: Int,
    val scope: CoroutineScope,
    val channel: ReceiveChannel<T>,
    val timeOutInMls: Long
) : IPort {

    protected var portJob: Job? = null

    abstract fun logPortation(portable: T, item: IDistributionItem)

    override fun close() {
        channel.cancel()
        portJob?.cancel()
    }

    override fun isOpen(): Boolean {
        return !channel.isClosedForReceive && portJob?.isActive == true
    }
}