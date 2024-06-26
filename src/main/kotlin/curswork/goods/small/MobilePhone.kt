package curswork.goods.small

class MobilePhone : SmallGood() {
    override fun getVolume(): Int {
        return 200
    }

    override fun getTime(): Long {
        return 600
    }
}