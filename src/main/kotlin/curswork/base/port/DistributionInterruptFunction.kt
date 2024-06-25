package curswork.base.port

typealias InterruptFunction = (distributorNumber: Int) -> Boolean

val hundredInterruptFunction: InterruptFunction = { it == 100 }