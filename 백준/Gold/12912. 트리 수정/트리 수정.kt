import kotlin.math.max

fun main() {
    val n = readLine()!!.toInt()
    val graph = Array(n) { mutableListOf<Triple<Int, Long, Int>>() }
    val edges = mutableListOf<Triple<Int, Int, Long>>()
    repeat(n - 1) { i ->
        val (u, v, w) = readLine()!!.split(' ').map { it.toLong() }
        graph[u.toInt()].add(Triple(v.toInt(), w, i))
        graph[v.toInt()].add(Triple(u.toInt(), w, i))
        edges += Triple(u.toInt(), v.toInt(), w)
    }

    fun findFarthest(start: Int, removedId: Int): Pair<Int, Long> {
        val dist = LongArray(n) { -1L }
        val stack = ArrayDeque<Int>()
        dist[start] = 0
        stack += start
        var farNode = start
        var maxD = 0L

        while (stack.isNotEmpty()) {
            val u = stack.removeLast()
            for ((v, w, id) in graph[u]) {
                if (id == removedId || dist[v] != -1L) continue
                dist[v] = dist[u] + w
                if (dist[v] > maxD) {
                    maxD = dist[v]
                    farNode = v
                }
                stack += v
            }
        }
        return farNode to maxD
    }

    fun computeDiameter(start: Int, removedId: Int): Long {
        val (farthest, _) = findFarthest(start, removedId)
        return findFarthest(farthest, removedId).second
    }

    var answer = 0L
    for (i in edges.indices) {
        val (u, v, w) = edges[i]
        val dA = computeDiameter(u, i)
        val dB = computeDiameter(v, i)
        answer = maxOf(answer, dA + dB + w)
    }

    println(answer)
}
/**
 * 4
 * 0 1 2
 * 0 2 4
 * 0 3 8
 */

/**
 * 5
 * 0 1 1
 * 1 2 2
 * 2 3 3
 * 3 4 4
 */

/**
 * 9
 * 1 6 1
 * 5 6 1
 * 6 4 1
 * 4 8 1
 * 4 0 1
 * 0 3 1
 * 3 2 1
 * 3 7 1
 */
