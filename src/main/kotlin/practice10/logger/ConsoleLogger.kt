package practice10.logger

open class ConsoleLogger : Logger {
    override fun log(text: String) {
        println(text)
    }
}