val br = System.`in`.bufferedReader()

fun main() {

    val (n, target) = br.readLine().split(" ").map { it.toInt() }
    val bids = br.readLine().split(" ").map { it.toInt() }

    var start = bids.max()
    var end = bids.sum()

    while (start < end) {
        val mid = (start + end) / 2
        val groupCount = findGroupCount(bids, mid)
        if (groupCount <= target) {
            end = mid
        } else {
            start = mid + 1
        }
    }
    println(start)

    var curIndex = 0
    var resultList = mutableListOf<Int>()
    repeat(target){

        var bind = 1

        if(curIndex < bids.size){
            var acc = bids[curIndex]

            while (true){
                curIndex++

                if((target - it) + curIndex > bids.size){
                    break
                }

                if(acc + bids[curIndex] > start){
                    break
                }else{
                    acc += bids[curIndex]
                    bind++
                }
            }
        }
        resultList.add(bind)
    }
    println(resultList.joinToString(separator = " "))
}

private fun findGroupCount(bids: List<Int>, mid: Int): Int {

    var groupCount = 0
    var acc = 0

    for (index in bids.indices) {

        if (acc + bids[index] <= mid) {
            acc += bids[index]
        }else{
            groupCount ++
            acc = bids[index]
        }
    }

    if (acc > 0) {
        groupCount++
    }
    return groupCount
}


/**
 * 8 3
 * 5 4 2 6 9 3 8 7
 */

/**
 * 9 2
 * 1 1 1 1 1 1 1 1 1
 */

/**
 * 9 3
 * 1 1 1 1 1 1 1 1 1
 */

/**
 * 9 6
 * 1 1 1 1 1 1 1 1 1
 */