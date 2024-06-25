package practice11.secondpart.ammos

import practice10.utils.isSuccessProbability
import kotlin.math.roundToInt


enum class Ammo(
    val damage: Int,
    val criticalChance: Int,
    val criticalDamageRate: Float
) {
    WARRIOR(damage = 10, criticalChance = 5, criticalDamageRate = 1.5f),
    SHOOTER(damage = 20, criticalChance = 10, criticalDamageRate = 2f),
    MAGNUM(damage = 30, criticalChance = 15, criticalDamageRate = 2.5f);

    fun getDamageCount(): Int = if (criticalChance.isSuccessProbability()) {
        (damage * criticalDamageRate).roundToInt()
    } else {
        damage
    }

}
