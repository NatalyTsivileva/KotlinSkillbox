package curswork.port

interface IPort {

    fun open()

    fun close()

    fun isOpen(): Boolean
}