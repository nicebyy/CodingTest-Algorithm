val br = System.`in`.bufferedReader()
var h = 0
var w = 0
lateinit var map: Array<CharArray>
lateinit var dp: Array<IntArray>

fun main() {

    val input = br.readLine()
        .split(" ")
        .map { it.toInt() }
    h = input[0]
    w = input[1]
    map = Array(h) { br.readLine().toCharArray() }
    dp = Array(h) { IntArray(w) { 0 } }

    var sum = 0
    for (y in 0..<h) {
        for (x in 0..<w) {
            if(dfs(x,y) == 2){
                sum++
            }
        }
    }
    println(sum)
}

fun dfs(x: Int, y: Int): Int{

    if(!isRange(x,y)){
        return 2
    }

    when(dp[y][x]){
        2,3 -> return dp[y][x]
        1 -> return 3
    }
    dp[y][x] = 1
    val (nextX, nextY) = when (map[y][x]) {
        'U' -> x to y - 1
        'D' -> x to y + 1
        'L' -> x - 1 to y
        else -> x + 1 to y
    }
    dp[y][x] = dfs(nextX, nextY)
    return dp[y][x]
}

fun isRange(x:Int, y:Int): Boolean = x in 0..<w && y in 0..<h

