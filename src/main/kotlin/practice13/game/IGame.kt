package practice13.game.base

import kotlinx.coroutines.flow.StateFlow
import practice13.game.GameState

interface IGame<T> {
    suspend fun startGame()

    fun finishGame()

    fun getGameState(): StateFlow<GameState<T>>
}