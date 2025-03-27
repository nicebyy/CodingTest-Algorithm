import java.util.*

lateinit var parents: IntArray

fun main() {

    val (n, m, _) = readln().split(" ").map { it.toInt() }
    val powerPlants = readln().split(" ").map { it.toInt() }

    val pq = PriorityQueue<Edge> { e1, e2 -> e1.cost - e2.cost };

    parents = IntArray(n + 1) { it }
    powerPlants.forEach { parents[it] = -1 } // 발전기는 -1 로 셋팅

    repeat(m) {
        val (from, to, cost) = readln().split(" ").map { it.toInt() }
        pq.add(Edge(from, to, cost))
    }

    var totalCost = 0
    while (pq.isNotEmpty() && !checkAllConnected()) {
        val edge = pq.poll()!!
        if (edge.isSameParent()) {
            continue
        }
        totalCost += edge.cost
        edge.union()
    }

    println(totalCost)
}

private fun checkAllConnected(): Boolean {
    for (i in 1..<parents.size) {
        if (parents[i] != -1) {
            return false
        }
    }
    return true
}


data class Edge(
    val from: Int,
    val to: Int,
    val cost: Int,
) {
    private fun find(x: Int): Int {

        if (parents[x] != -1 && parents[x] != x) {
            parents[x] = find(parents[x])
        }
        return parents[x]
    }

    fun isSameParent(): Boolean {
        return find(from) == find(to)
    }

    fun union() {//from:8 to: 9

        val fromRoot = find(from) // 7
        val toRoot = find(to) // -1

        if (fromRoot == toRoot) {
            return
        }

        if (fromRoot == -1) {
            parents[toRoot] = fromRoot
        } else if (toRoot == -1) {
            parents[fromRoot] = toRoot
        } else {
            parents[toRoot] = fromRoot
        }
    }
}


/**
5 5 1
1
2 3 2
3 4 2
2 4 2
1 5 2
1 2 2
 */