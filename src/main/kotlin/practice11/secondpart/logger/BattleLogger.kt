package practice11.secondpart.logger

import practice11.secondpart.battle.BattleState
import practice11.secondpart.team.Team
import practice11.secondpart.warrior.AbstractWarrior
import practice11.secondpart.warrior.Warrior
import java.text.SimpleDateFormat
import java.util.*

class BattleLogger : ConsoleLogger() {

    fun logTeamInfo(team: Team) {
        log(
            """
            ===================================================================================
         
         
                                             Команда: "${team.name}"
                                         Количество участников: ${team.getTeammateCount()}
                                                 Участники:               
                                  
        """.trimIndent()
        )

        team.getTeammates().forEach { logWarriorInfo(it) }
    }

    fun logAttackInfo(
        state: BattleState,
        warrior: Warrior,
        opponent: Warrior,
        damage: Int,
        warriorTeam: Team,
        opponentTeam: Team
    ) {
        val formatter = SimpleDateFormat("HH:mm:ss")
        val time = formatter.format(Date())

        val stateText = when (state) {
            is BattleState.Draw -> "НИЧЬЯ"
            is BattleState.FirstTeamWin -> "ПОБЕДА ПЕРВОЙ КОМАНДЫ"
            is BattleState.Progress -> "В ПРОЦЕССЕ"
            is BattleState.SecondTeamWin -> "ПОБЕДА ВТОРОЙ КОМАНДЫ"
        }

        if (damage > 0)
            log(
                "[$time][$stateText] [${warriorTeam.name}] ${warrior.javaClass.simpleName} наносит урон противнику [${opponentTeam.name}][${opponent.javaClass.simpleName}] в размере $damage "
            )

    }


    fun logWarriorInfo(warrior: AbstractWarrior) {
        log(
            """
        ****************************************************************************** 
            [${warrior.javaClass.simpleName}]:
                максимальное здоровье: ${warrior.maxHealth}
                шанс попадания: ${warrior.hittingChance}
                шанс уклонения :${warrior.evasionChance}
                оружие: ${warrior.weapon.javaClass.simpleName}
                тип стрельбы: ${warrior.weapon.fireType.javaClass.simpleName}
                максимальное число патронов: ${warrior.weapon.maxAmmoCount}
                магазин патронов пуст?: ${warrior.weapon.isMagazineEmpty}               
        """.trimIndent()
        )
    }


    fun logSurvivalInfo(firstTeam: Team, secondTeam: Team) {
        log("")
        log("        Кол-во выживших 1й команды=${firstTeam.getTeammateCount()}, 2-й команды = ${secondTeam.getTeammateCount()} ")
        log("")
    }

}