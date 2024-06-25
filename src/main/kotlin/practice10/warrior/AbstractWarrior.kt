package practice10.warrior

import practice10.utils.isSuccessProbability
import practice10.weapon.AbstractWeapon

abstract class AbstractWarrior(val maxHealth: Int) : Warrior {

    abstract val weapon: AbstractWeapon<*>

    private var currentHealth: Int = maxHealth

    override val isKilled: Boolean
        get() = currentHealth <= 0

    override fun attack(opponent: Warrior): Int {
        var damage = 0
        if (!weapon.isMagazineEmpty) {
            val ammoList = weapon.takeAmmoForShot()
            ammoList.forEach { ammo ->
                if (isSuccessAttack(opponent)) {
                    damage = ammo.getDamageCount()
                    opponent.takeDamage(damage)
                }
            }
        } else {
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