import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.joinToString
import kotlin.collections.map
import kotlin.collections.toCollection

fun main() {

    repeat(readln().toInt()) {
        val visit = Array(10000) { false }
        val (start, end) = readln().split(" ").map { it.toInt() }
        val queue = LinkedList<Node>()
        queue.add(Node(start, ""))
        visit[start] = true
        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            if (cur.num == end) {
                println(cur.commands)
                break
            }
            listOf(cur.double(), cur.dec(), cur.right(), cur.left())
                .filter { !visit[it.num] }
                .forEach {
                    queue.add(it)
                    visit[it.num] = true
                }
        }
    }

}

data class Node(
    var num: Int = 0,
    val commands: String = "",
) {

    fun double(): Node {
        val nextNum = (this.num * 2) % 10000
        return Node(nextNum, "${commands}D")
    }

    fun dec(): Node {

        var nextNum = this.num - 1
        if (nextNum < 0) {
            nextNum += 10000
        }
        return Node(nextNum, "${commands}S")
    }

    fun left(): Node {
        val nextNum = (num%1000)*10 + num/1000
        return Node(nextNum, "${commands}L")
    }

    fun right(): Node {
        val nextNum = (num % 10)*1000 + num/10
        return Node(nextNum, "${commands}R")
    }
}


/**
 * 3
 * 1234 3412
 * 1000 1
 * 1 16
 *
 * 1
 * 1 16
 */