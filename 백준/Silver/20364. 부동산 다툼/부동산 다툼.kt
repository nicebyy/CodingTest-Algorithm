import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow

fun main() = with(System.`in`.bufferedReader()) {

    val (
        size, queryCount
    ) = readLine().split(' ').map { it.toInt() }

    val height = ceil(log2(size.toDouble())).toInt()
    val array = IntArray((2.0).pow(height+1).toInt()) { -1 }
    val result = StringBuilder()
    repeat(queryCount) {
        val query = readLine().toInt()

        var value = query
        var cond = true
        var closed = value
        while (value > 1) {

            if (array[value] == 1) {
                cond = false
                closed = value
            }
            value = value / 2
        }

        if (cond) {
            result.append("0\n")
            value = query
            array[query] = 1
            while (value < array.size) {
                if (1 + value * 2 < array.size) {
                    array[value * 2] = 1
                    array[1 + value * 2] = 1
                }
                value = value * 2
            }
        } else {
            result.append("$closed\n")
        }
    }

    println(result)
}

/**
 * 6 4
 * 3
 * 5
 * 6
 * 2
 */