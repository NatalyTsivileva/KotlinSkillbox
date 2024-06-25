package curswork.goods.small

class Toy : SmallGood() {

    override fun getVolume(): Int {
        return 300
    }

    override fun getTime(): Long {
        return 200
    }
}