package practice10.warrior

interface Warrior {
    val isKilled: Boolean

    /**
     * шанс избежать попадания
     */
    val evasionChance: Int

    /**
     * шанс попадания
     */
    val hittingChance: Int

    fun attack(opponent: Warrior): Int

    fun takeDamage(amount: Int)
}