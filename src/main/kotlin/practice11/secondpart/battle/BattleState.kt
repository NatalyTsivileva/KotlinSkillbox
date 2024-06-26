package practice11.secondpart.battle

sealed class BattleState {
    object Draw : BattleState()
    object FirstTeamWin : BattleState()
    object SecondTeamWin : BattleState()

    class Progress(
        val firstTeamSurvivors: Int,
        val secondTeamSurvivors: Int
    ) : BattleState()
}