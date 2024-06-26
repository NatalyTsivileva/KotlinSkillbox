package curswork.trucks

import curswork.base.AbstractIDistributor
import curswork.base.IDistributionItem

abstract class Truck<T : IDistributionItem> : AbstractIDistributor<T>()