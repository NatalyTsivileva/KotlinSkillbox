package practice7

class Dog(
    name: String,
    weight: Int,
    energy: Int,
    maxAge: Int
) : Animal(name, weight, energy, maxAge) {

    override fun walk(): Boolean {
        val isWalked = super.walk()
        if (isWalked) println("бежит")
        return isWalked
    }

    override fun bornChild(): Dog {
        val child = super.bornChild()
        return Dog(
            name = child.name,
            maxAge = child.maxAge,
            energy = child.energy,
            weight = child.weight
        )
    }
}