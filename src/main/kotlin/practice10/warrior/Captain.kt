package practice10.warrior

import practice10.weapon.AbstractWeapon
import practice10.weapon.Weapon

class Captain : AbstractWarrior(maxHealth = 2500) {
    override val evasionChance: Int = 30
    override val hittingChance: Int = 85
    override val weapon: AbstractWeapon<*> = Weapon.createShotgun()
}