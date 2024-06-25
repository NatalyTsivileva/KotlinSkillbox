package practice11.secondpart.weapon

import practice11.secondpart.FireType
import practice11.secondpart.Stack
import practice11.secondpart.ammos.Ammo
import practice11.secondpart.exception.NoAmmoException


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
                popAmmo(1)
            }

            is FireType.burstShot -> {
                popAmmo(type.burstSize)
            }
        }
    }

    private fun popAmmo(count: Int): List<Ammo> {
        if (fireType == FireType.singleShot && ammoMagazine.isEmpty())
            throw NoAmmoException()

        if (fireType is FireType.burstShot && ammoMagazine.count() < count)
            throw NoAmmoException()

        val ammoList = mutableListOf<Ammo>()

        repeat(count) {
            ammoMagazine.pop()?.let { ammoList.add(it) }
        }

        return ammoList
    }

}