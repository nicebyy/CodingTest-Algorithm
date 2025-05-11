import java.text.DecimalFormat
import java.util.LinkedList
import java.util.PriorityQueue
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {

    val (n, alreadyCount) = readLine().split(" ").map { it.toInt() }
    val points = ArrayList<Pair<Int, Int>>()
    repeat(n) {
        points.add(
            readLine().split(" ")
                .map { it.toInt() }
                .let { Pair(it[0], it[1]) }
        )
    }
    val pairs = getComb(n)
    val edges = PriorityQueue<Edge>()

    for ((fromIndex, toIndex) in pairs) {
        val fromPoint = points[fromIndex]
        val toPoint = points[toIndex]

        val diff = fromPoint.diff(toPoint)
        edges.add(Edge(fromIndex, toIndex, diff))
    }

    val parents = IntArray(n) { it }

    fun find(x: Int): Int {
        if (parents[x] != x) {
            parents[x] = find(parents[x])
        }
        return parents[x]
    }

    fun union(x: Int, y: Int) {
        val xRoot = find(x)
        val yRoot = find(y)
        parents[xRoot] = yRoot
    }

    repeat(alreadyCount) {
        val (fromIndex, toIndex) = readLine().split(" ").map { it.toInt() }
        union(fromIndex - 1, toIndex - 1)
    }

    var totalCost: Double = 0.0
    while (edges.isNotEmpty()) {
        val edge = edges.poll()
        if (find(edge.from) != find(edge.to)) {
            union(edge.from, edge.to)
            totalCost += edge.cost
        }
    }
    totalCost = round(totalCost * 100) / 100
    val df = DecimalFormat("#.00")
    print(df.format(totalCost))
}

fun Pair<Int, Int>.diff(other: Pair<Int, Int>): Double {
    return sqrt(
        (this.first - other.first).toDouble().pow(2.0)
            .plus((this.second - other.second).toDouble().pow(2.0))
    )
}

fun getComb(size: Int): ArrayList<Pair<Int, Int>> {
    val list = ArrayList<Pair<Int, Int>>()
    for (i in 0..<size) {
        for (j in i + 1..<size) {
            list.add(Pair(i, j))
        }
    }
    return list
}

data class Edge(
    val from: Int,
    val to: Int,
    val cost: Double
) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int = (this.cost).compareTo(other.cost)
}

/**
 * 4 1
 * 1 1
 * 3 1
 * 2 3
 * 4 3
 * 1 4
 */