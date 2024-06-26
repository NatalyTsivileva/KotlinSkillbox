package curswork.goods.food

class Bread : FoodGoods() {
    override fun getVolume(): Int {
        return 500
    }

    override fun getTime(): Long {
        return 500
    }
}