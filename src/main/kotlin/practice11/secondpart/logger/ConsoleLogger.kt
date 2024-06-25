package practice11.secondpart.logger

open class ConsoleLogger : Logger {
    override fun log(text: String) {
        println(text)
    }
}