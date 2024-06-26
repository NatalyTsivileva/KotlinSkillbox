package curswork.goods.oversized

class Bed : OversizeGood() {
    override fun getVolume(): Int {
        return 220
    }

    override fun getTime(): Long {
        return 1500
    }
}