package curswork.trucks

import curswork.distributor.IDistributionItem

class VanTruck<T : IDistributionItem> : Truck<T>() {

    override fun getCapacity(): Int {
        return 9550
    }

}