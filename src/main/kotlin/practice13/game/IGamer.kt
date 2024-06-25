package practice13.game.base

interface IGamer<T> {
    fun makeMove(data: T)

    fun isWin(): Boolean
}