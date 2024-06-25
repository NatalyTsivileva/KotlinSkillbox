package practice11.secondpart.warrior

import practice11.secondpart.weapon.AbstractWeapon
import practice11.secondpart.weapon.Weapon

class General : AbstractWarrior(maxHealth = 3000) {
    override val evasionChance: Int = 40
    override val hittingChance: Int = 90
    override val weapon: AbstractWeapon<*> = Weapon.createMachineGun()
}