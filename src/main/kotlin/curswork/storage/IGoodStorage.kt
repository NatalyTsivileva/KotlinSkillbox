package curswork.storage

import curswork.goods.Good

interface IGoodStorage : IStorage<Good> {

    suspend fun fetchGoodByCategory(category: Good.GoodCategory): Good?

    suspend fun getGoodByCategory(category: Good.GoodCategory): Good?

}