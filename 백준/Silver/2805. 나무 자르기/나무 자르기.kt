fun main(args: Array<String>) {

    val (n, target) = readln().split(" ").map { it.toInt() }
    val arr: LongArray = readln().split(" ").map { it.toLong() }.sorted().toLongArray()

    var left = 0
    var right = arr[n-1]
    var result = -1
    while (left < right){

        val mid = (left + right)/2;
        val sliced = arr.filter { it > mid }.sumOf { it - mid }
//        println("sliced = $sliced  target = $target")

        if(sliced == target.toLong()){
            left = (mid+1).toInt()
        }else if(sliced < target){
            right = mid
        }else if(sliced > target){
            left = (mid+1).toInt()
        }
    }

    println(left-1)
}