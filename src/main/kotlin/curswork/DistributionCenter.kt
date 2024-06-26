package curswork

import curswork.port.IPort
import curswork.port.LoadingPort
import curswork.port.UnloadingPort
import curswork.storage.DistributionGoodStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

class DistributionCenter(
    private val scope: CoroutineScope,
    private val unloadingPortCount: Int,
    private val loadingPortCount: Int,
    private val workTimeInMls: Long
) {

    private val unloadingPorts = mutableListOf<IPort>()
    private val loadingPorts = mutableListOf<IPort>()

    private val unloadingChannel = UnloadingPort.createUnloadingChannel(scope)
    private val loadingChannel = LoadingPort.createLoadingChannel(scope)

    private val storage = DistributionGoodStorage()


    init {
        createUnloadingPorts()
        openUnloadingPorts()

        createLoadingPorts()
        openLoadingPorts()

        startTimer()
    }

    private fun createUnloadingPorts() {
        repeat(unloadingPortCount) { id ->

            val port = UnloadingPort(
                scope = scope,
                portID = id,
                saveItemFunction = storage::addItem,
                channel = unloadingChannel
            )

            unloadingPorts.add(port)
        }
    }

    private fun openUnloadingPorts() {
        unloadingPorts.forEach { it.open() }
    }


    private fun createLoadingPorts() {
        repeat(loadingPortCount) { id ->
            val port = LoadingPort(
                scope = scope,
                portID = id,
                getItemFunction = storage::fetchGoodByCategory,
                channel = loadingChannel
            )
            loadingPorts.add(port)
        }
    }

    private fun openLoadingPorts() {
        loadingPorts.forEach { it.open() }
    }

    private fun startTimer() {
        scope.launch {
            withTimeoutOrNull(workTimeInMls) {
                delay(workTimeInMls)
                loadingPorts.forEach { it.close() }
                unloadingPorts.forEach { it.close() }
            }
        }
    }

}
