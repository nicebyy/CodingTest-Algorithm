import java.lang.StringBuilder

fun main() {

    val n = readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }
    val sorted = arr.sorted().distinct()
    val sb = StringBuilder()

    for (i in 0..<n){
        sb.append(sorted.binarySearch(arr[i])).append(" ")
    }
    println(sb.toString())
}