package practice11.secondpart.battle

import practice11.secondpart.logger.BattleLogger
import practice11.secondpart.team.Team


class Battle(
    private val firstTeam: Team,
    private val secondTeam: Team,
    private val logger: BattleLogger
) {

    fun startBattle() {
        logger.logTeamInfo(firstTeam)
        logger.logTeamInfo(secondTeam)

        while (getBattleState() is BattleState.Progress) {
            firstTeam.regroup()
            secondTeam.regroup()

            attack(firstTeam, secondTeam)
            attack(secondTeam, firstTeam)

            logger.logSurvivalInfo(firstTeam, secondTeam)

            Thread.sleep(100)
        }
    }

    private fun getBattleState(): BattleState = when {
        firstTeam.getTeammateCount() == 0 && secondTeam.getTeammateCount() == 0 -> BattleState.Draw
        firstTeam.getTeammateCount() > 0 && secondTeam.getTeammateCount() == 0 -> BattleState.FirstTeamWin
        secondTeam.getTeammateCount() > 0 && firstTeam.getTeammateCount() == 0 -> BattleState.SecondTeamWin
        else -> BattleState.Progress(
            firstTeamSurvivors = firstTeam.getTeammateCount(), secondTeamSurvivors = secondTeam.getTeammateCount()
        )
    }

    private fun attack(aggressorTeam: Team, protectorTeam: Team) {
        aggressorTeam.getTeammates().forEach { aggressor ->
            val protector = protectorTeam.getTeammates().randomOrNull()
            if (protector != null) {
                val damage = aggressor.attack(protector)

                protectorTeam.removeKilledTeammates()

                logger.logAttackInfo(
                    state = getBattleState(),
                    warrior = aggressor,
                    opponent = protector,
                    damage = damage,
                    warriorTeam = aggressorTeam,
                    opponentTeam = protectorTeam
                )
            }
        }
    }


}