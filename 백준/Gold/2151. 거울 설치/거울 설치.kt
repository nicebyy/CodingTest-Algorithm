import Node.Companion.dx
import Node.Companion.dy
import Node.Companion.isRange
import java.util.*

val br = System.`in`.bufferedReader()
var n: Int = 0
lateinit var map: Array<CharArray>
val doors = mutableListOf<Pair<Int, Int>>()
lateinit var dp: Array<IntArray>

fun main() {

    n = br.readLine().toInt()
    map = Array(n) { br.readLine().toCharArray() }
    map.forEachIndexed { y, rows ->
        rows.forEachIndexed { x, value ->
            if (value == '#') {
                doors.add(Pair(x, y))
            }
        }
    }
    dp = Array(n) { IntArray(n) { Int.MAX_VALUE } }
    // end init

    val pq = LinkedList<Node>()
    pq.add(Node(doors[0], 0))
    dp[doors[0].second][doors[0].first] = 0
    while (pq.isNotEmpty()) {

        val poll = pq.poll()!!

        for (dir in 0..3) {

            var scale = 0

            while (true) {
                scale++
                val nx = scale * dx[dir] + poll.point.first
                val ny = scale * dy[dir] + poll.point.second

                if(!isRange(nx,ny) || map[ny][nx] == '*'){ // 벽
                    break
                }else if(map[ny][nx] == '.'){ // 통과
                    continue
                }else if(map[ny][nx] == '!' && poll.weight+1 < dp[ny][nx]){ //
                    pq.add(Node(Pair(nx, ny), poll.weight + 1))
                    dp[ny][nx] = poll.weight+1
                }else if(map[ny][nx] == '#' && poll.weight < dp[ny][nx]){
                    pq.add(Node(Pair(nx, ny), poll.weight))
                    dp[ny][nx] = poll.weight
                }
            }
        }
    }
    print(dp[doors[1].second][doors[1].first])
}

data class Node(
    val point: Pair<Int, Int>,
    val weight: Int = 0,
) {

    companion object {
        val dx = listOf(0, 0, 1, -1)
        val dy = listOf(1, -1, 0, 0)

        fun isRange(x: Int, y: Int): Boolean = x in 0 until n && y in 0 until n
    }
}

