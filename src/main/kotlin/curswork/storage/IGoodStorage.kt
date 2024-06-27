package curswork.storage

import curswork.goods.Good

interface IGoodStorage : IStorage<Good> {

    suspend fun fetchGoodByCategory(
        category: Good.GoodCategory,
        needFetch: (item: Good) -> Boolean
    ): Good?
}