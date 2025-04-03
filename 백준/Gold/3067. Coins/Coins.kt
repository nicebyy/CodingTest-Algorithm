import java.util.LinkedList
import kotlin.math.max

val br = System.`in`.bufferedReader()

fun main() {

    val tc = br.readLine().toInt()

    repeat(tc){
        val n = br.readLine().toInt()
        val coins = ("0 ${br.readLine()}").split(" ").map { it.toInt() }.toIntArray()
        val target = br.readLine().toInt()
        val dp = Array(n+1){ IntArray(target+1) }

        for(i in 1..n){
            dp[i][0] = 1 // 0 원을 만들 수 있는 경우는 하나 밖에 없음.

            for(j in 1..target){ // 1~target 원 까지
                dp[i][j] = dp[i-1][j] //
                if(j>=coins[i]){
                    dp[i][j] += dp[i][j-coins[i]]
                }
            }
        }

        println(dp[n][target])
    }
}

/**
 * 3
 * 2
 * 1 2
 * 1000
 * 3
 * 1 5 10
 * 100
 * 2
 * 5 7
 * 22
 */

/**
 * 1
 * 2
 * 5 7
 * 22
 */