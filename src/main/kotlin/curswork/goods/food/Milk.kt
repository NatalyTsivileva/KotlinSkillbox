package curswork.goods.food

class Milk: FoodGoods() {
    override fun getVolume(): Int {
        return 200
    }

    override fun getTime(): Long {
        return 300
    }
}