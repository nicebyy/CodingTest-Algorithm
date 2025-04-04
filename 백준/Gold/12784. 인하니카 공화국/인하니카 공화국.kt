import kotlin.math.min

val br = System.`in`.bufferedReader()
var tc = 0
lateinit var graph: MutableList<MutableList<Node>>

fun main() {

    val tc = br.readLine().toInt()
    repeat(tc) {

        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        graph = MutableList(n + 1) {
            mutableListOf()
        }

        repeat(m) {
            val (from, to, cost) = br.readLine().split(" ").map { it.toInt() }
            graph[from].add(Node(to, cost))
            graph[to].add(Node(from, cost))
        }

        val result = dfs(1,-1, Int.MAX_VALUE)
        println(
            when {
                result == Int.MAX_VALUE -> 0
                else -> result
            }
        )
    }
}

fun dfs(cur:Int, prev: Int, bomb: Int): Int{

    var used = 0

    for (next in graph[cur]) {

        if(prev == next.node){
            continue
        }
        used += dfs(next.node, cur, next.cost)
    }

    if(used == 0){ // leaf 노드 일때
        used += bomb
    }
    return min(used, bomb)
}

data class Node(
    val node: Int,
    val cost: Int,
)