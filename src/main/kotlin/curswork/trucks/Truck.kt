package curswork.trucks

import curswork.distributor.AbstractIDistributor
import curswork.distributor.IDistributionItem

abstract class Truck<T : IDistributionItem> : AbstractIDistributor<T>()