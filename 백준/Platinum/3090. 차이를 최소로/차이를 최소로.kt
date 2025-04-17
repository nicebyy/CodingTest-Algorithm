import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, t) = br.readLine().split(" ").map { it.toLong() }
    val arr = br.readLine().split(" ").map { it.toLong() }

    // 이분 탐색 범위: 차이 c ∈ [0, 10^9]
    var lo = 0L
    var hi = 1_000_000_000L
    var result = List(n.toInt()) { 0L }

    // 가능한지 여부와 조정된 배열을 함께 반환
    fun possible(c: Long): Pair<Boolean, List<Long>> {
        val b = MutableList(n.toInt()) { 0L }
        var ops = 0L

        // 정방향 스캔
        b[0] = arr[0]
        for (i in 1 until n.toInt()) {
            val maxAllowed = b[i - 1] + c
            if (arr[i] > maxAllowed) {
                ops += arr[i] - maxAllowed
                b[i] = maxAllowed
            } else {
                b[i] = arr[i]
            }
        }
        // 역방향 스캔
        for (i in n.toInt() - 2 downTo 0) {
            val maxAllowed = b[i + 1] + c
            if (b[i] > maxAllowed) {
                ops += b[i] - maxAllowed
                b[i] = maxAllowed
            }
        }
        return Pair(ops <= t, b)
    }

    // 이분 탐색으로 최소 c 찾기
    while (lo <= hi) {
        val mid = (lo + hi) ushr 1
        val (ok, candidate) = possible(mid)
        if (ok) {
            result = candidate
            hi = mid - 1
        } else {
            lo = mid + 1
        }
    }

    // 결과 출력
    println(result.joinToString(" "))
}
