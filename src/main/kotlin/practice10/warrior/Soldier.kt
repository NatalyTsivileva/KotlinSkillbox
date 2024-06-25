package practice10.warrior

import practice10.weapon.AbstractWeapon
import practice10.weapon.Weapon

class Soldier : AbstractWarrior(maxHealth = 2000) {
    override val evasionChance: Int = 20
    override val hittingChance: Int = 70
    override val weapon: AbstractWeapon<*> = Weapon.createPistol()
}