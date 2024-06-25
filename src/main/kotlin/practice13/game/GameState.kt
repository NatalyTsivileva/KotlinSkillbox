package practice13.game

sealed class GameState<T> {
    class Progress<T>(val data: T) : GameState<T>()
    class Initial<T> : GameState<T>()
    class Complete<T>(val message:String) : GameState<T>()
}