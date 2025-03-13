import java.lang.StringBuilder

fun main() {

    val n = readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }
    val map = HashMap<Int, Int>()
    var rank = 0
    arr.sorted().forEach {
        if (!map.containsKey(it)) {
            map[it] = rank++
        }
    }
    val sb = StringBuilder()
    arr.map { map[it] }.forEach { sb.append("$it ") }
    println(sb.toString())
}