package curswork.goods.medium

class Mirror : MediumGood() {
    override fun getVolume(): Int {
        return 150
    }

    override fun getTime(): Long {
        return 1020
    }
}