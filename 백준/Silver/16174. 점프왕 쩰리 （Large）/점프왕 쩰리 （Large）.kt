import java.util.LinkedList

val br = System.`in`.bufferedReader()


fun main() {

    val n = br.readLine().toInt()
    var cond = false
    val map = Array(n) {
        br.readLine().split(" ").map { it.toInt() }.toIntArray()
    }
    val visit = Array(n){BooleanArray(n){false}}

    val queue = LinkedList<Pair<Int, Int>>()
    queue.add(Pair(0, 0))
    visit[0][0] = true
    while (queue.isNotEmpty()) {

        val cur = queue.poll()!!

        if (map[cur.second][cur.first] == -1) {
            cond = true
            break
        }

        cur.next(n, map[cur.second][cur.first])
            .filter {
                !visit[it.second][it.first]
            }
            .forEach {
                queue.add(it)
                visit[it.second][it.first] = true
            }
    }

    if (cond) {
        println("HaruHaru")
    } else {
        println("Hing")
    }
}

fun Pair<Int, Int>.next(range: Int, amount: Int): List<Pair<Int, Int>> {
    val right = Pair(this.first + amount, this.second)
    val down = Pair(this.first, this.second + amount)
    return listOf(right, down).filter { it.first in 0..<range && it.second in 0..<range }
}

/**
 * 3
 * 1 1 10
 * 1 5 1
 * 2 2 -1
 */