package practice6

fun main(args: Array<String>) {
    val tvList = listOf(
        TV(brand = "Philips", model = "55PUS8729", diagonal = 55),
        TV(brand = "Haier", model = "S3", diagonal = 55),
        TV(brand = "Samsung", model = "UE75CU8500UXRU", diagonal = 75)
    )

    tvList.forEach { tv ->
        println("\n[TV ${tv.brand}, ${tv.model}, ${tv.diagonal}d]")
        println(tv.getChannelsList())
        tv.turnOn()
        tv.turnOff()
        tv.increaseVolume(10)
        tv.decreaseVolume(40)
        tv.decreaseVolume(20)
        tv.increaseVolume(110)
        tv.switchChannelDown()
        tv.switchChannelUp()
        tv.switchChanel(5)
        tv.switchChannelDown()
        tv.switchChannelUp()
        tv.switchChannelDown()
        tv.turnOff()
        tv.switchChannelDown()
        tv.switchChannelDown()
        tv.switchChannelDown()
        tv.switchChannelDown()
        tv.switchChannelDown()
        tv.turnOff()
        tv.switchChanel(10)
        tv.switchChannelUp()
        tv.switchChannelUp()
    }
}