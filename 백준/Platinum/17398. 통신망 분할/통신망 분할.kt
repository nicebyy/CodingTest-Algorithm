import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {

    val (n, edgeCount, queryCount) = readLine().split(" ").map { it.toInt() }
    val parent = IntArray(n + 1) { it }
    val size = IntArray(n+1){1}
    val edges = Array(edgeCount + 1) { Pair(0, 0) }
    val isRemove = BooleanArray(edgeCount + 1) { false }

    fun find(x: Int): Int {
        if (x == parent[x]) {
            return x
        }
        parent[x] = find(parent[x])
        return parent[x]
    }

    fun union(x: Int, y: Int) {
        val xRoot = find(x)
        val yRoot = find(y)
        if (xRoot == yRoot) {
            return
        }      
        parent[yRoot] = xRoot
        size[xRoot] += size[yRoot]
    }

    repeat(edgeCount) {
        val (from, to) = readLine().split(" ").map { it.toInt() }
        edges[it + 1] = Pair(from, to)
    }
    val removeQueue = ArrayList<Int>()
    repeat(queryCount) {
        val target = readLine().toInt()
        isRemove[target] = true
        removeQueue.add(target)
    }

    edges.forEachIndexed { i, edge ->
        if (!isRemove[i] && i > 0) {
            val (from, to) = edge
            union(from, to)
        }
    }

    var cost = 0L
    removeQueue.reversed().forEach { it ->
        val (from, to) = edges[it]

        val fromRoot = find(from)
        val toRoot = find(to)

        if(fromRoot != toRoot){
            val fromRootSize = size[fromRoot]
            val toRootSize = size[toRoot]
            cost += fromRootSize.toLong() * toRootSize.toLong()
        }
        union(from, to)
    }
    println(cost)
}

/**
 * 입력
 * 4 4 3
 * 1 2
 * 2 3
 * 3 4
 * 1 4
 * 4
 * 2
 * 3
 */

