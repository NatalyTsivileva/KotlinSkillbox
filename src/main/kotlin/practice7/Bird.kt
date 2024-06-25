package practice7

class Bird(
    name: String,
    weight: Int,
    energy: Int,
    maxAge: Int
) : Animal(name, weight, energy, maxAge) {

    override fun walk(): Boolean {
        val isWalked = super.walk()
        if (isWalked) println("летит")
        return isWalked
    }

    override fun bornChild(): Bird {
        val child = super.bornChild()
        return Bird(
            name = child.name,
            maxAge = child.maxAge,
            energy = child.energy,
            weight = child.weight
        )
    }

}