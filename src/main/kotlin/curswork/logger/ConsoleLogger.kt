package curswork.logger

open class ConsoleLogger : Logger {

    override fun log(text: String) {
        println(text)
    }

}