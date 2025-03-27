import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))

    val n = br.readLine().toInt()
    val arr = Array(n) {
        br.readLine().split(" ").map {
            it.toInt()
        }.toIntArray() // 0 : from 1: to
    }
    Arrays.sort(arr) { o1, o2 -> o1[0] - o2[0] }

    var totalLength = 0
    var prevFrom = arr[0][0]
    var prevEnd = arr[0][1]
    for ((from, to) in arr) {

        // 분리 되어 있는 경우
        if (prevEnd < from) {
            totalLength += (prevEnd - prevFrom)
            prevFrom = from
            prevEnd = to
            continue
        }

        if (prevFrom <= from && prevEnd < to) {
            prevEnd = to
        }
    }
    totalLength += (prevEnd - prevFrom)
    println(totalLength)
}
