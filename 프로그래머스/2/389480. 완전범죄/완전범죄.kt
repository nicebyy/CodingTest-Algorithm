class Solution {
    fun solution(info: Array<IntArray>, n: Int, m: Int): Int {
        val dp = Array(info.size+1){
            IntArray(m){n}
        }

        dp[0][0] = 0

        for(i in 1..info.size){
            val a = info[i-1][0]
            val b = info[i-1][1]

            for(j in 0 until m){
                //a steal
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j]+a)

                // b steal
                if(j+b < m){
                    dp[i][j+b] = Math.min(dp[i][j+b], dp[i-1][j])
                }
            }
        }

        var answer: Int = n
        for(j in 0 until m){
            answer = Math.min(answer, dp[info.size][j])
        }
        return when{
            answer>=n -> -1
            else -> answer
        }
    }
}

/**
 * info[i][0] : a의 흔적개수
 * info[i][1] : b의 흔적개수
 *
 * 완탐이 불가능한 이유 => 최악의 경우 2^40 개의 시간복잡도 ...
 *
 * dp 를 쓸경우 =>
 * dp[몇번째물건][b의 흔적] = a 최소 흔적 합
 */