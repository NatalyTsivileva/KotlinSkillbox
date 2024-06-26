package curswork.trucks

import curswork.distributor.IDistributionItem

class FireTruck<T : IDistributionItem> : Truck<T>() {

    override fun getCapacity(): Int {
        return 5000
    }
}