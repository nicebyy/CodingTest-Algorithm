import java.util.PriorityQueue

val br = System.`in`.bufferedReader()

fun main() {

    val (
        n, m
    ) = br.readLine().split(" ").map { it.toInt() }

    val parent = IntArray(n + 1) { it }
    val pq = PriorityQueue<IntArray>() { o1, o2 -> o1[2] - o2[2] }
    var edgeTotalCost: Long = 0
    repeat(m) {

        val (
            from,
            to,
            cost
        ) = br.readLine().split(" ").map { it.toInt() }

        val edge = intArrayOf(from, to, cost)
        pq.add(edge)
        edgeTotalCost += cost
    }

    var totalCost: Long = 0
    var connected = 0
    while (pq.isNotEmpty()) {

        val curEdge = pq.poll()!!

        fun find(x: Int): Int {
            when {
                x == parent[x] -> return x
                else -> {
                    parent[x] = find(parent[x])
                    return parent[x]
                }
            }
        }

        fun union(from: Int, to: Int) {
            val fromRoot = find(from)
            val toRoot = find(to)

            if (fromRoot != toRoot) {
                parent[fromRoot] = to
            }
        }

        val from = curEdge[0]
        val to = curEdge[1]

        if(find(from) == find(to)){
            continue
        }
        union(from, to)
        totalCost += curEdge[2]
        connected++
    }

    var result: Long = -1L
    if(connected == n-1){
        result = edgeTotalCost - totalCost
    }
    println(result)
}

