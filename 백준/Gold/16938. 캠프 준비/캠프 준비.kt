import kotlin.math.abs
import kotlin.properties.Delegates

lateinit var arr: List<Int>
lateinit var visit: Array<Boolean>
var min = 0
var max = 0
var maxDiff = 0
var ans = 0

fun main() {

    val (
        n, l, r, x
    ) = readln().split(" ").map { it.toInt() }
    min = l
    max = r
    maxDiff = x
    arr = readln().split(" ").map { it.toInt() }
    visit = Array(n) { false }
    val list = ArrayList<Int>()
    find(0, 0, list)
    println(ans)
}

fun find(cur: Int, depth: Int, list: ArrayList<Int>) {

    if (depth > 1) {
        val cond = list.sum() in min..max && abs(list.min()-list.max()) >= maxDiff
        if(cond){
            ans++
        }
    }

    for (next in cur..<arr.size) {

        if (!visit[next]) {
            visit[next] = true
            list.add(arr[next])
            find(next, depth + 1, list)
            list.remove(arr[next])
            visit[next] = false
        }
    }
}
