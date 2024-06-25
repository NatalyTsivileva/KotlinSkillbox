package curswork.goods

import curswork.base.IDistributionItem

class UndefinedItem:IDistributionItem {
    override fun getVolume(): Int {
        return 0
    }

    override fun getTime(): Long {
        return 0
    }
}