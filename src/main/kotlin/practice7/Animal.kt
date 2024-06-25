package practice7

import kotlin.random.Random

open class Animal(
    name: String,
    weight: Int,
    energy: Int,
    maxAge: Int
) {

    var name: String
        private set

    var weight: Int
        private set

    var energy: Int
        private set

    var maxAge: Int
        private set

    init {
        this.name = name
        this.weight = weight
        this.energy = energy
        this.maxAge = maxAge
    }

    private var currentAge: Int = 0

    val isTooOld: Boolean
        get() = currentAge >= maxAge

    fun sleep() {
        if (!isTooOld) {
            energy += 5
            currentAge += 1
            println("$name спит")
        }
    }

    fun eat() {
        if (!isTooOld) {
            energy += 3
            weight += 1
            tryIncrementAge()
            println("$name ест")
        }
    }

    open fun walk(): Boolean {
        val canWalk = !isTooOld && energy >= 5 && weight >= 1
        if (canWalk) {
            energy -= 5
            weight -= 1
            tryIncrementAge()
            println("$name передвигается")
        }
        return canWalk
    }

    private fun tryIncrementAge() {
        if (Random.nextBoolean()) {
            currentAge += 1
        }
    }

    open fun bornChild(): Animal {
        val animal = Animal(
            name = name,
            maxAge = maxAge,
            energy = (1..10).random(),
            weight = (1..5).random()
        )

        println("${animal.name} родился! energy = ${animal.energy}, weight = ${animal.weight}, maxAge=$maxAge")

        return animal
    }
}