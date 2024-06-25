package practice11.secondpart.team

import practice10.utils.isSuccessProbability
import practice11.secondpart.warrior.AbstractWarrior
import practice11.secondpart.warrior.Captain
import practice11.secondpart.warrior.General
import practice11.secondpart.warrior.Soldier

class Team(val name: String, teammateCount: Int) {

    private var teammates: MutableList<AbstractWarrior> = (0 until teammateCount).map {
        val generalChance = 10
        val captainChance = 20

        when {
            generalChance.isSuccessProbability() -> General()
            captainChance.isSuccessProbability() -> Captain()
            else -> Soldier()
        }
    }.toMutableList()

    fun regroup() = teammates.shuffle()

    fun getTeammates(): List<AbstractWarrior> = teammates

    fun getTeammateCount() = getTeammates().count()

    fun removeKilledTeammates() {
        teammates.removeAll { it.isKilled }
    }

}