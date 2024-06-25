package practice11.secondpart

import practice10.battle.Battle
import practice10.logger.BattleLogger
import practice10.team.Team

fun main(args: Array<String>) {
    val firstTeam = Team("Команда 1", 10)
    val secondTeam = Team("Команда 2", 10)
    val battle = Battle(firstTeam, secondTeam, BattleLogger())
    battle.startBattle()
}




