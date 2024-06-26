package curswork.base.port

import curswork.distributor.IDistributionItem

sealed class DistributionProcess<T : IDistributionItem>() {
    class InProgress<T : IDistributionItem>(val item: T, val info: String) : DistributionProcess<T>()
    class Finished<T : IDistributionItem> : DistributionProcess<T>()
}