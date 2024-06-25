package curswork.goods.oversized

class Oven : OversizeGood() {
    override fun getVolume(): Int {
        return 600
    }

    override fun getTime(): Long {
        return 2000
    }
}