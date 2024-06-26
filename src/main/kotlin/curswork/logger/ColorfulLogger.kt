package curswork.logger

class ColorfulLogger : ConsoleLogger() {

    fun logColorful(message: String, color: Color = Color.BLACK) {
        log("${color.value}$message")
    }

    enum class Color(val value: String) {
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        BLACK("\u001B[30m")
    }
}