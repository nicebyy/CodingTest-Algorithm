import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {

    val (n, start, end, tp) = readln().split(' ').map { it.toInt() }
    val edges = ArrayList<Edge>()
    val map = ArrayList<ArrayList<Int>>()
    repeat(n){map.add(ArrayList())}
    val incomes = IntArray(n) { 0 }
    val distance = LongArray(n) { Long.MAX_VALUE }
    repeat(tp) {
        val (from, to, cost) = readln().split(' ').map { it.toInt() }
        map[from].add(to)
        edges.add(Edge(from, to, cost))
    }
    readLine().split(" ")
        .map { it.toInt() }
        .forEachIndexed { index, value ->
            incomes[index] = value
        } // end init

    distance[start] = -incomes[start].toLong()

    repeat(n - 1) {
        for ((from, to, cost) in edges) {
            val nextCost = distance[from] + cost - incomes[to]
            if (distance[from] != Long.MAX_VALUE && distance[to] > nextCost) {
                distance[to] = nextCost
            }
        }
    }

    var cond = true
    var cycleNodeList = ArrayList<Int>()
    for ((from, to, cost) in edges) {
        val nextCost = distance[from] + cost - incomes[to]
        if (distance[from] != Long.MAX_VALUE && distance[to] > nextCost) {
            cond = false
            cycleNodeList.add(to)
        }
    }

    if(!cond){ // 1. 사이클이 발견 되었고, 그 사이클에서 밖으로 빠져나갈 수 있는 길이 없는 경우.

        for (cycleStart in cycleNodeList) {
            val visit = BooleanArray(n){false}
            visit[cycleStart] = true

            val queue = LinkedList<Int>()
            queue.add(cycleStart)
            while(queue.isNotEmpty()){

                val cur = queue.poll() !!

                for (next in map[cur]) {

                    if(!visit[next]){
                        visit[next] = true
                        queue.add(next)
                    }
                }
            }

            if(visit[end]){
                println("Gee")
                return
            }
        }
        cond = true
    }
    println(
        when {
            distance[end] == Long.MAX_VALUE -> "gg"
            !cond -> "Gee"
            else -> -distance[end]
        }
    )
}

data class Edge(
    val from: Int,
    val to: Int,
    val cost: Int,
)


/**
 * 4 0 1 4
 * 0 1 0
 * 3 1 10
 * 2 3 3
 * 3 2 3
 * 0 10 10 10
 */

/**
 * 4 0 3 4
 * 0 1 0
 * 1 2 0
 * 2 3 0
 * 3 0 0
 * 1000000 1000000 1000000 1000000
 *
 * 1 0 0 2
 * 0 0 0
 * 0 0 7
 * 6
 */