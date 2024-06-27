import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.*
import kotlin.random.Random

fun main(args: Array<String>) = runBlocking {
    val storage = Storage()

    val job1 = launch {
        repeat(10) {
            val added = storage.add(it)
            if (added) println("Добавлено: $it") else println("Ошибка добавления: $it")
            delay(randomTime())
        }

    }
    val job2 = launch {
        repeat(10) {
            val item = storage.fetchLast()
            if(item!= null) println("Извлечено: $item") else println("Не извлечено, нет больше элементов")
            delay(randomTime())
        }
    }

    val job3 = launch {
        withTimeoutOrNull(10000) {
            while (isActive) {
                delay(randomTime())
                println("====================================")
                println("logger: ${storage.getInfo()}")
                println("====================================")
            }
        }
    }
    job1.join()
    job2.join()
    job3.join()
    println("итог:${storage.getInfo()}")
}

fun randomTime() = Random.nextInt(300, 1000).toLong()

class Storage() {
    val items = LinkedList<Int>()

    private val mutex = Mutex()

    suspend fun add(item: Int): Boolean {
        mutex.withLock {
            return items.add(item)
        }
    }

    suspend fun watchLast(): Int? {
        mutex.withLock {
            return items.peek()
        }
    }

    suspend fun fetchLast(): Int? {
        mutex.withLock {
            return items.poll()
        }
    }

    fun getInfo() = items.joinToString(",")

}
