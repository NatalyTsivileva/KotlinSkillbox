package practice10.weapon

import practice10.FireType
import practice10.Stack
import practice10.ammos.Ammo


abstract class AbstractWeapon<T : Ammo> {
    private var ammoMagazine = Stack<T>()

    abstract val maxAmmoCount: Int

    abstract val fireType: FireType

    val isMagazineEmpty
        get() = ammoMagazine.isEmpty()

    abstract fun createNewAmmo(): T

    fun recharge() {
        ammoMagazine = Stack()
        (0 until maxAmmoCount).forEach { _ ->
            ammoMagazine.push(createNewAmmo())
        }
    }

    fun takeAmmoForShot(): List<Ammo> {
        return when (val type = fireType) {
            is FireType.singleShot -> {
                popAmmoSafety(1)
            }

            is FireType.burstShot -> {
                popAmmoSafety(type.burstSize)
            }
        }
    }

    private fun popAmmoSafety(count: Int): List<Ammo> {
        val ammoList = mutableListOf<Ammo>()
        if (ammoMagazine.count() >= count) {
            repeat(count) {
                ammoMagazine.pop()?.let { ammoList.add(it) }
            }
        }
        return ammoList
    }

}