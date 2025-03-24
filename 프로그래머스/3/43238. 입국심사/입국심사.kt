class Solution {
    fun solution(n: Int, times: IntArray): Long {
        var answer: Long = 0
        
        var start: Long = 1
        var end: Long = 1000000000L*n
        
        while(start < end){
            
            var mid = (start+end)/2
            
            var count = 0L
            
            for(time in times){
                count += mid/time
            }
            
            if(count >= n){ // 시간을 많이 잡아서 더 줄여야함.
                end = mid
            }else{ // 
                start = mid+1
            }
        }
        return start
    }
}

// [left, mid) 