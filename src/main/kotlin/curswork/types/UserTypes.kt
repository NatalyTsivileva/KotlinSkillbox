package curswork.types

import curswork.distributor.IDistributionItem
import curswork.distributor.IDistributor
import curswork.goods.Good


typealias AnyDistributor = IDistributor<IDistributionItem>

typealias AnyGoodsDistributor = Pair<AnyDistributor, Good.GoodCategory>
