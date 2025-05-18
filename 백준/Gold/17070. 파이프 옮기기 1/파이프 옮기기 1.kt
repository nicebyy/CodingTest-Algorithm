fun main() = with(System.`in`.bufferedReader()) {

    val n = readLine().toInt()
    val map = Array(n){
        readLine().split(" ").map { it.toInt() }
    }

    var ans = 0

    fun move(x:Int, y:Int, dir:Int){

        if(x == n-1 && y == n-1){
            ans++
            return
        }

        when(dir){
            0 -> {
                if(x+1 < n && map[y][x+1] == 0){
                    move(x+1, y, dir)
                }
            }
            1 -> {
                if(y+1 < n && map[y+1][x] == 0){
                    move(x, y+1, dir)
                }
            }
            2-> {
                if(y+1 < n && map[y+1][x] == 0){
                    move(x, y+1, 1)
                }
                if(x+1 < n && map[y][x+1] == 0){
                    move(x+1, y, 0)
                }
            }
        }

        if(x+1 < n && y+1 < n && map[y+1][x+1] == 0 && map[y][x+1] == 0 && map[y+1][x] == 0){
            move(x+1, y+1, 2)
        }
    }

    move(1,0,0)
    println(ans)
}

/**
 * 3
 * 0 0 0
 * 0 0 0
 * 0 0 0
 */