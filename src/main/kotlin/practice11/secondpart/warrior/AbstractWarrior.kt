package practice11.secondpart.warrior

import practice11.secondpart.exception.NoAmmoException
import practice11.secondpart.utils.isSuccessProbability
import practice11.secondpart.weapon.AbstractWeapon

abstract class AbstractWarrior(val maxHealth: Int) : Warrior {

    abstract val weapon: AbstractWeapon<*>

    private var currentHealth: Int = maxHealth

    override val isKilled: Boolean
        get() = currentHealth <= 0

    override fun attack(opponent: Warrior): Int {
        var damage = 0
        try {
            val ammoList = weapon.takeAmmoForShot()
            ammoList.forEach { ammo ->
                if (isSuccessAttack(opponent)) {
                    damage = ammo.getDamageCount()
                    opponent.takeDamage(damage)
                }
            }
        } catch (e: NoAmmoException) {
            weapon.recharge()
        }

        return damage
    }


    override fun takeDamage(amount: Int) {
        if (!isKilled)
            currentHealth -= amount
    }

    private fun isSuccessAttack(opponent: Warrior) =
        hittingChance.isSuccessProbability() && !opponent.evasionChance.isSuccessProbability()

}