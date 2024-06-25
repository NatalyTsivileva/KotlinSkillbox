package curswork.trucks

import curswork.base.IDistributionItem


class TankerTruck<T : IDistributionItem> : Truck<T>() {

    override fun getCapacity(): Int {
       return 7000
    }

}