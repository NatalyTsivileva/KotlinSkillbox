package curswork

import curswork.logger.ColorfulLogger
import curswork.port.IPort
import curswork.port.LoadingPort
import curswork.port.UnloadingPort
import curswork.storage.DistributionGoodStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DistributionCenter(
    private val scope: CoroutineScope,
    private val unloadingPortCount: Int,
    private val loadingPortCount: Int,
    private val workTimeInMls: Long,
    val logger: ColorfulLogger = ColorfulLogger()
) {

    private val unloadingPorts = mutableListOf<IPort>()
    private val loadingPorts = mutableListOf<IPort>()

    private val unloadingChannel = UnloadingPort.createUnloadingChannel(scope)
    private val loadingChannel = LoadingPort.createLoadingChannel(scope)

    private val storage = DistributionGoodStorage(logger)


    init {
        createUnloadingPorts()
        openUnloadingPorts()

        createLoadingPorts()
        openLoadingPorts()

        startLogger()
    }

    private fun createUnloadingPorts() {
        repeat(unloadingPortCount) { id ->

            val port = UnloadingPort(
                scope = scope,
                portID = id,
                saveItemFunction = storage::addItem,
                channel = unloadingChannel,
                timeOutInMls = workTimeInMls,
                logger = logger
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
                portID = id,
                scope = scope,
                channel = loadingChannel,
                timeOutInMls = workTimeInMls,
                getItemFunction = storage::getGoodByCategory,
                fetchItemFunction = storage::fetchGoodByCategory,
                logger = logger
            )
            loadingPorts.add(port)
        }
    }

    private fun openLoadingPorts() {
        loadingPorts.forEach { it.open() }
    }

    private fun startLogger() {
        scope.launch {
            delay(workTimeInMls + 100)
            println(storage.getStorageInfo())
        }
    }
}

