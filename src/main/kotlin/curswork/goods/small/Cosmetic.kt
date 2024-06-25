package curswork.goods.small

class Cosmetic: SmallGood() {
    override fun getVolume(): Int {
        return 300
    }

    override fun getTime(): Long {
        return 500
    }
}