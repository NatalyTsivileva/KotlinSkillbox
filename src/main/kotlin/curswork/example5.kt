package curswork

import curswork.goods.Good
import curswork.goods.food.FoodGoods
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

fun findSubclasses(parentClass: Class<*>): List<Class<*>> {
    val allClasses = getAllClasses()
    return allClasses.filter { it.isAssignableFrom(parentClass) && it != parentClass }
}

fun main() {
    val abstractClass = FoodGoods::class.java
    val subclasses = findSubclasses(abstractClass)

    println("Subclasses of ${abstractClass.simpleName}:")
    subclasses.forEach { subclass ->
        println(subclass.simpleName)
    }
}

    fun getAllClasses(): List<Class<*>> {
        val packagePath = "curswork/goods/food" // Replace with the package path you want to scan
        val packageDirectory = Paths.get(ClassLoader.getSystemResource(packagePath).toURI())

        val classes = mutableListOf<Class<*>>()

        try {
            Files.walk(packageDirectory)
                .filter { Files.isRegularFile(it) && it.toString().endsWith(".class") }
                .forEach { file ->
                    val className = file.toString()
                        .substring(packageDirectory.toString().length + 1)
                        .replace('/', '.')
                        .replace(".class", "")

                    val classInstance = Class.forName(className)
                    classes.add(classInstance)
                }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }

        return classes
}