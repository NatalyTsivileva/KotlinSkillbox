import kotlinx.coroutines.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import practice13.game.GameState
import practice13.game.LottoGame

class LottoGameTests {


    @Test
    fun testGame() = runBlocking {
        val gamers = generateGamers(defaultSettings, 10)
        val game = LottoGame(gamers, defaultSettings)

        val scope = CoroutineScope(Job() + Dispatchers.IO)

        scope.launch {
            game
                .getGameState()
                .collect { state ->
                    when (state) {
                        is GameState.Initial -> {
                            Assertions.assertTrue(scope.isActive)
                        }

                        is GameState.Complete -> {
                            scope.cancel()
                            Assertions.assertTrue(!scope.isActive)
                            println(state.message)
                        }

                        is GameState.Progress -> {
                            Assertions.assertTrue(scope.isActive)
                            println("==================================================================================\n")
                            println("                    Достали бочонок из катушки = ${state.data}             \n")
                            gamers.forEach { it.makeMove(state.data) }
                        }
                    }
                }
        }

        game.startGame()
    }



    @Test
    fun testWin() {
        val gamers = generateGamers(defaultSettings, 4)
        val firstGamer = gamers.first()
        Assertions.assertFalse(firstGamer.isWin())

        val lastCard = firstGamer.cards.last()
        println(lastCard)

        val firstRowValues = lastCard.getRow(1).map { it?.getValue() }

        firstRowValues.forEach {
            if (it != null) {
                firstGamer.makeMove(it)
            }
        }

        Assertions.assertTrue(firstGamer.isWin())
    }



}