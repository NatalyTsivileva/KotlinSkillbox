package practice10.weapon

import practice10.FireType
import practice10.ammos.Ammo

object Weapon {

    fun createPistol() = object : AbstractWeapon<Ammo>() {
        override val maxAmmoCount: Int = 10
        override val fireType: FireType = FireType.singleShot
        override fun createNewAmmo() = Ammo.WARRIOR
    }

    fun createShotgun() = object : AbstractWeapon<Ammo>() {
        override val maxAmmoCount: Int = 8
        override val fireType: FireType = FireType.singleShot
        override fun createNewAmmo() = Ammo.SHOOTER
    }

    fun createMachineGun() = object : AbstractWeapon<Ammo>() {
        override val maxAmmoCount: Int = 100
        override val fireType: FireType = FireType.burstShot(50)
        override fun createNewAmmo() = Ammo.MAGNUM
    }
}