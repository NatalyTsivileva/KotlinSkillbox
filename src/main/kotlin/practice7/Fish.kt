package practice7

class Fish(
    name: String,
    weight: Int,
    energy: Int,
    maxAge: Int
) : Animal(name, weight, energy, maxAge) {

    override fun walk(): Boolean {
        val isWalked = super.walk()
        if (isWalked) println("плывет")
        return isWalked
    }

    override fun bornChild(): Fish {
        val child = super.bornChild()
        return Fish(
            name = child.name,
            maxAge = child.maxAge,
            energy = child.energy,
            weight = child.weight
        )
    }
}