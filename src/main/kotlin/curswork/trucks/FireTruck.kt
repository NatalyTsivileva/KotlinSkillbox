package curswork.trucks

import curswork.base.IDistributionItem

class FireTruck<T : IDistributionItem> : Truck<T>() {

    override fun getCapacity(): Int {
        return 5000
    }
}