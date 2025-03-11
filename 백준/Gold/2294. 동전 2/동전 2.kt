import java.util.*
import kotlin.math.min


fun main(args: Array<String>) {

    val (n, target) = readln().split(" ").map { it.toInt() }
    val coins = IntArray(n)
    val MAX = 1000000
    repeat(n){
        coins[it] = readln().toInt()
    }
    val dp = Array(target+1){MAX}
    dp[0] = 0

    for(i in 1..target){
        for (coin in coins) {

            if(i - coin >= 0){
                dp[i] = min(dp[i], dp[i-coin]+1)
            }
        }
    }

    val result = if(dp[target] == MAX) -1 else dp[target]
    println(result)
}
