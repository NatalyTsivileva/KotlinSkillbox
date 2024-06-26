package curswork.goods.medium

class Chair : MediumGood() {
    override fun getVolume(): Int {
        return 120
    }

    override fun getTime(): Long {
        return 1200
    }
}