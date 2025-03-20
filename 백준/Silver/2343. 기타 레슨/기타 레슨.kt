fun main() {

    val (n, k) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toLong() }

    var left: Long = arr.max()
    var right = arr.sum()+1

    while (left < right){

        val mid = (left+right)/2
        val diskCount = getDiskCount(arr, mid)
        if(diskCount <= k){
            right = mid
        }else{
            left = mid+1
        }
    }
    println(left)
}

fun getDiskCount(arr:List<Long>, target:Long): Int{

    var count = 1
    var sum: Long = 0
    for (size in arr) {

        if(sum + size <= target){
            sum += size
        }else{
            count++
            sum = size
        }
    }
    return count
}
