package curswork.trucks

import curswork.base.IDistributionItem

class VanTruck<T : IDistributionItem> : Truck<T>() {

    override fun getCapacity(): Int {
        return 9550
    }

}