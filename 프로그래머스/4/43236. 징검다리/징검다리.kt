
class Solution {
    fun solution(distance: Int, rocks: IntArray, n: Int): Int {
        var answer = 0
        var rockList = intArrayOf(0,*rocks.sortedArray(),distance)

        var start = 1
        var end = distance+1

        while (start < end){

            var mid = (start+end)/2

            var prev = rockList[0]
            var removed = 0
            for(index in 1..rockList.size-1){

                if(rockList[index]-prev < mid){
                    removed ++
                    continue
                }
                prev = rockList[index]
            }

            if(removed <= n){
                start = mid+1
            }else{
                end = mid
            }
        }
        return start-1
    }
}


/**
 * 0 1 25
 *
 *
 */