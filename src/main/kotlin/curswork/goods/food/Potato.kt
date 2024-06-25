package curswork.goods.food

class Potato: FoodGoods() {
    override fun getVolume(): Int {
        return 300
    }

    override fun getTime(): Long {
        return 600
    }
}