package curswork.base.port

import curswork.base.IDistributionItem
import curswork.base.IDistributor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import java.util.*

abstract class AbstractPort {

    private val distributorQueue: Queue<IDistributor> = LinkedList()

    protected val _distributionProcess = MutableSharedFlow<DistributionProcess<IDistributionItem>>()
    val distributionProcess: SharedFlow<DistributionProcess<IDistributionItem>> = _distributionProcess.asSharedFlow()


    /**
     * Получить дистрибьютера и удалить его из очереди
     */
    protected fun getNextDistributor(): IDistributor? {
        return distributorQueue.poll()
    }

    /**
     * Получить дистрибьютора из очереди, но не удалять его
     */
    protected fun getFirstDistributor(): IDistributor? {
        return distributorQueue.peek()
    }

    fun addDistributor(distributor: IDistributor): Boolean {
        return distributorQueue.add(distributor)
    }

    abstract fun getDistributionProcessText(
        distributor: IDistributor,
        item: IDistributionItem
    ): String
}