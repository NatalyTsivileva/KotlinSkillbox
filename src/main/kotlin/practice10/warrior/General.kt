package practice10.warrior

import practice10.weapon.AbstractWeapon
import practice10.weapon.Weapon

class General : AbstractWarrior(maxHealth = 3000) {
    override val evasionChance: Int = 40
    override val hittingChance: Int = 90
    override val weapon: AbstractWeapon<*> = Weapon.createMachineGun()
}