val br = System.`in`.bufferedReader()

fun main() {

    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val seq = Array(n + 1) {
        IntArray(n + 1) {
            0
        }
    }

    repeat(m) {
        val (from, to) = br.readLine().split(" ").map { it.toInt() }
        seq[from][to] = 1
        seq[to][from] = -1
    }
    for (interval in 1..n) {
        for (from in 1..n) {
            for (to in 1..n) {
                if (seq[from][interval] == 1) {
                    if (seq[interval][to] == 1) {
                        seq[from][to] = 1
                        seq[to][from] = -1
                    }
                }
            }
        }
    }
    repeat(br.readLine().toInt()) {
        val (start, end) = br.readLine().split(" ").map { it.toInt() }
        println(seq[start][end]*-1)
    }
}
