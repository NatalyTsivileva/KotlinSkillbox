package practice13.game

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import practice13.game.base.IGame
import practice13.table.table.LottoTableSettings
import kotlin.random.Random

class LottoGame(
    private val gamers: List<LottoGamer>,
    private val settings: LottoTableSettings,
) : IGame<Int> {

    private var state: MutableStateFlow<GameState<Int>> = MutableStateFlow(GameState.Initial())

    private var isGameStopped = false

    override suspend fun startGame() {
        while (!gamers.any { it.isWin() } && !isGameStopped) {
            delay(500)
            val randomValue = Random.nextInt(0, settings.maxValue)
            state.emit(GameState.Progress(randomValue))
        }
        val text = "Лотерея завершена! Поздравляем\n"
        state.emit(GameState.Complete(text))
    }

    override fun finishGame() {
        isGameStopped = true
    }

    override fun getGameState(): StateFlow<GameState<Int>> {
        return state
    }
}