package practice11.secondpart.warrior

import practice11.secondpart.weapon.AbstractWeapon
import practice11.secondpart.weapon.Weapon


class Captain : AbstractWarrior(maxHealth = 2500) {
    override val evasionChance: Int = 30
    override val hittingChance: Int = 85
    override val weapon: AbstractWeapon<*> = Weapon.createShotgun()
}