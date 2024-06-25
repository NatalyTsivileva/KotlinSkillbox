package practice11.secondpart

class Stack<T> {

    private var items = mutableListOf<T>()

    fun push(item: T) {
        items.add(item)
    }

    fun pop(): T? {
        var item: T? = null

        if (items.isNotEmpty()) {
            item = items.removeLast()
        }
        return item
    }


    fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    fun count() = items.count()

    override fun toString() = items.toString()
}