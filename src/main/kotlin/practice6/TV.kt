package practice6

class TV(
    val brand: String,
    val model: String,
    val diagonal: Int
) {

    var isOn: Boolean = false
        private set

    private val channelList: List<Channel> = ChannelsUtils.getRandomChannels()

    private var volume = MAX_VOLUME

    private var currentChannel: Channel? = channelList.firstOrNull()

    fun turnOn() {
        isOn = true
        println("[turnOn] Телевизор включён? : ${isOn}")
    }

    fun turnOff() {
        isOn = false
        println("[turnOff] Телевизор включён? : ${isOn}")
    }

    fun increaseVolume(amount: Int) {
        volume = if (volume + amount >= MAX_VOLUME)
            MAX_VOLUME
        else
            volume + amount

        println("[increaseVolume] Громкость: ${volume}")
    }

    fun decreaseVolume(amount: Int) {
        volume = if (volume - amount <= MIN_VOLUME) {
            MIN_VOLUME
        } else {
            volume - amount
        }

        println("[decreaseVolume] Громкость: ${volume}")
    }

    fun switchChanel(number: Int) {
        if (!isOn) turnOn()
        if (channelList.count() >= number) {
            currentChannel = channelList[number - 1]
        }

        println("[switchChanel $number] Включён канал: ${currentChannel}")
    }

    fun switchChannelUp() {
        if (isOn) {
            val index = channelList.indexOf(currentChannel)
            if (index == -1) return

            val newIndex = if (index == 0) channelList.lastIndex else index - 1

            currentChannel = channelList[newIndex]
        } else {
            turnOn()
        }

        println("[switchChanelUp] Включён канал: ${currentChannel}")

    }

    fun switchChannelDown() {
        if (isOn) {
            val index = channelList.indexOf(currentChannel)
            if (index == -1) return

            val newIndex = if (index == channelList.lastIndex) 0 else index + 1

            currentChannel = channelList[newIndex]
        } else {
            turnOn()
        }

        println("[switchChanelDown] Включён канал: ${currentChannel}")
    }

    fun getChannelsList() = channelList.mapIndexed { index, channel ->
        "$index - \"${channel.name}\""
    }.joinToString(",\n")


    companion object {
        const val MAX_VOLUME = 100;
        const val MIN_VOLUME = 0;
    }
}