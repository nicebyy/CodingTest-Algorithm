import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()){

    val n = readLine().toInt()
    val arr = readLine().split(" ")
        .map { it.toInt() }
        .sorted()
    val target = readLine().toInt()

    if(arr.sum() <= target){
        println(arr.last())
        return
    }

    var start = 1
    var end = arr.last()

    while (start < end){

        val mid = (start + end)/2

        val sum = arr.sumOf {
            if (it > mid) {
                mid
            } else {
                it
            }
        }

        if(sum > target){ // mid 를 너무 크게 잡아서 모두 배정 할 수없는 상황 => 새로운 left 를 찾는
            end = mid
        }else{ // mid 너무 작게 잡아서 배정하고도 남느 상황 => 새로운 상한선을 찾아야하는 . 같은 경우에도 마찬가지
            start = mid+1
        }
    }

    println(end-1)
}

/**
 * 4
 * 120 110 140 150
 * 485
 */

