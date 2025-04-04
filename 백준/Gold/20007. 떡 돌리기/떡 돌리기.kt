import java.util.*

val br = System.`in`.bufferedReader()

fun main() {

    val (n, m, limit, start) = br.readLine().split(" ").map { it.toInt() }
    val list = ArrayList<ArrayList<Node>>()
    repeat(n) { list.add(ArrayList()) }
    repeat(m) {
        val (from, to, cost) = br.readLine().split(" ").map { it.toInt() }
        list[from].add(Node(to, cost))
        list[to].add(Node(from, cost))
    }

    val pq = PriorityQueue<Node>() { node1, node2 -> node1.cost - node2.cost }
    val distance = IntArray(n) { Int.MAX_VALUE }
    pq.add(Node(start, 0))
    distance[start] = 0

    while (pq.isNotEmpty()) {

        val (curNode, curCost) = pq.poll()!!

        for ((nextNode, nextCost) in list[curNode]) {

            if (distance[nextNode] > curCost + nextCost) {
                distance[nextNode] = curCost + nextCost
                pq.add(Node(nextNode, distance[nextNode]))
            }
        }
    }

    var days = 0
    var todayCost = 0
    distance.sort()
    for (value in distance) {

        if (value * 2 > limit) {
            println(-1)
            return
        }

        if (value * 2 + todayCost <= limit) {
            todayCost += value * 2
        } else {
            days++
            todayCost = value * 2
        }
    }
    if (todayCost > 0) {
        days++
    }
    println(days)
}

data class Node(
    val node: Int,
    val cost: Int,
)

