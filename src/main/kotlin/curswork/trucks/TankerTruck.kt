package curswork.trucks

import curswork.distributor.IDistributionItem


class TankerTruck<T : IDistributionItem> : Truck<T>() {

    override fun getCapacity(): Int {
        return 7000
    }

}