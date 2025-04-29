import kotlin.math.max

val br = System.`in`.bufferedReader()

fun main() {

    val size = br.readLine().toInt()
    val map = Array(size) {
        br.readLine().split(" ")
            .map { it.toInt() }
    }
    val dp = Array(size) { IntArray(size) { 0 } }
    val last = Array(size){ IntArray(size){-1} }

    if(map[0][0] == 0){
        dp[0][0] = 1
        last[0][0] = 0
    }else{
        last[0][0] = 2
    }

    fun isRange(x:Int, y:Int): Boolean = x in 0..<size && y in 0..<size
    val dx = arrayOf(1,0)
    val dy = arrayOf(0,1)

    for(y in 0 until size){
        for(x in 0 until size){
            for(dir in dx.indices){
                val nx = dx[dir] + x
                val ny = dy[dir] + y

                if(isRange(nx, ny)){

                    if((last[y][x]+1)%3 == map[ny][nx] && dp[ny][nx] < dp[y][x]+1){
                        dp[ny][nx] = dp[y][x] + 1
                        last[ny][nx] = map[ny][nx]
                    }else if(dp[y][x] > dp[ny][nx]){
                        dp[ny][nx] = dp[y][x]
                        last[ny][nx] = last[y][x]
                    }
                }
            }
        }
    }
    println(dp[size-1][size-1])
}


/**
 * 3
 * 0 1 2
 * 1 1 1
 * 2 2 2
 */

/**
 * 2
 * 1 0
 * 0 1
 */

/**
 * 2
 * 0 1
 * 1 2
 */

/**
 * 3
 * 2 2 1
 * 2 1 1
 * 1 1 0
 */

/**
 * 2
 * 0 2
 * 0 0
 */

/**
 * 4
 * 2 2 1 2
 * 1 2 1 2
 * 1 2 1 2
 * 0 1 2 0
 */

/**
 * 2
 * 1 1
 * 1 1
 */

/**
 * 3
 * 0 0 0
 * 0 1 0
 * 0 0 1
 */