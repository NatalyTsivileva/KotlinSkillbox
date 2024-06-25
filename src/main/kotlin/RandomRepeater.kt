import kotlin.random.Random

data class RandomRepeater(
    val to: Int = Random.nextInt(0, 10)
) {

    private val count
        get() = (0 until to).count()

    fun repeat(block: (Int) -> Unit) {
        kotlin.repeat(count, block)
    }
}