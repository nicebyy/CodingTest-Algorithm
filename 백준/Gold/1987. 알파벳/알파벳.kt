import java.util.LinkedList
import kotlin.math.max

val br = System.`in`.bufferedReader()
val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)
var h = 0
var w = 0
lateinit var map: Array<CharArray>
var visit: BooleanArray = BooleanArray('Z' - 'A' + 1) { false }
var maxCount = Int.MIN_VALUE

fun main() {

    val (
        height, width
    ) = br.readLine().split(" ").map { it.toInt() }
    h = height
    w = width
    map = Array(h) {
        br.readLine().toCharArray()
    }
    visit[map[0][0] - 'A'] = true
    dfs(0, 0, 1)

    println(maxCount)
}

fun dfs(x: Int, y: Int, count: Int) {

    maxCount = max(count, maxCount)

    for (i in dx.indices) {

        val nextX = x + dx[i]
        val nextY = y + dy[i]

        if (isRange(nextX, nextY) && !visit[map[nextY][nextX] - 'A']) {
            visit[map[nextY][nextX] - 'A'] = true
            dfs(nextX, nextY, count + 1)
            visit[map[nextY][nextX] - 'A'] = false
        }
    }
}

fun isRange(x: Int, y: Int): Boolean {
    return x in 0..<w && y in 0..<h;
}


data class Node(
    val x: Int,
    val y: Int,
)

/**
 * 2 4
 * CAAB
 * ADCB
 */