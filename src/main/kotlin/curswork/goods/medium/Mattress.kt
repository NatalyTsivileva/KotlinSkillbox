package curswork.goods.medium

class Mattress : MediumGood() {
    override fun getVolume(): Int {
        return 100
    }

    override fun getTime(): Long {
        return 1000
    }
}