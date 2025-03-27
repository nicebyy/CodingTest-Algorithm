fun main() {

    val (n, k) = readln().split(" ").map { it.toInt() }
    val levels = Array(n) { 0L }

    repeat(n) {
        levels[it] = readln().toLong()
    }
    levels.sort()

    var count = k.toLong()
    var start = levels.min()
    var end = levels.max() + count + 1

    while (start < end) {

        var mid = (start + end) / 2

        var diff = 0L

        for (level in levels) {
            if (mid > level) {
                diff += (mid - level)
            }else{
                break
            }
        }

        if(diff > count){ // 목표레벨을 너무 높게 잡아버려서 count 보다 더 많은 레벨 업 기회를 써버렸다 => mid 를 더 낮춰야한다.
            end = mid
        }else {
            start = mid+1
        }
    }

    println(end-1)
}
