package practice11.secondpart.warrior

import practice11.secondpart.weapon.AbstractWeapon
import practice11.secondpart.weapon.Weapon

class Soldier : AbstractWarrior(maxHealth = 2000) {
    override val evasionChance: Int = 20
    override val hittingChance: Int = 70
    override val weapon: AbstractWeapon<*> = Weapon.createPistol()
}