package curswork.goods.oversized

class Fridge: OversizeGood() {
    override fun getVolume(): Int {
        return 280
    }

    override fun getTime(): Long {
        return 1700
    }
}