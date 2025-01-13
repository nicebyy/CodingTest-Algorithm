
lateinit var map: Array<IntArray>
lateinit var visit: Array<BooleanArray>

fun main(args: Array<String>) {

    var tc = readln().toInt()

    while (tc --> 0){

        val (w, h, k) = readln().split(" ").map { it.toInt() }

        map = Array(h) { IntArray(w) }
        visit = Array(h) {BooleanArray(w)}

        for (i in 1..k) {
            val (x, y) = readln().split(" ").map { it.toInt() }
            map[y][x] = 1
        }

        var result: Int = 0

        for(i in 0..< h){
            for(j in 0..< w){

                if(!visit[i][j] && map[i][j] == 1){
                    dfs(j,i)
                    result++;
                }
            }
        }

        println(result)
    }
}

fun dfs(x: Int, y: Int){

    val dx = arrayOf(0,0,1,-1)
    val dy = arrayOf(1,-1,0,0)

    visit[y][x] = true

    for(i in dx.indices){

        val nextX = x+dx[i]
        val nextY = y+dy[i]

        if(isRange(nextX, nextY) && !visit[nextY][nextX] && map[nextY][nextX] == 1){
            dfs(nextX, nextY);
        }
    }
}

fun isRange(x:Int, y:Int): Boolean{
    return x>=0 && y>=0 && x<map[0].size && y< map.size;
}
