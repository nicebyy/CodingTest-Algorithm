import java.util.LinkedList
import kotlin.math.max
import kotlin.math.min

var minTime = Int.MAX_VALUE

fun main() = with(System.`in`.bufferedReader()) {

    val (size, activableCount) = readLine().split(" ").map { it.toInt() }
    val map = Array(size){
        readLine().split(" ")
            .map { it.toInt() }
    }
    val activable = mutableListOf<Pair<Int,Int>>()
    map.forEachIndexed { y, row ->
        row.forEachIndexed { x, value ->
            if(value == 2){
                activable.add(Pair(x, y))
            }
        }
    }
    val visit = BooleanArray(activable.size){false}
    fun dfs(index:Int, count: Int, list: MutableList<Int>){

        if(activableCount == count){ // list iter
            val mapVisit = Array(size){
                BooleanArray(size){
                    false
                }
            }

            val processQueue = LinkedList<Node>()

            list.forEach { index ->
                val start = activable[index]
                processQueue.add(Node(start, 0))
                mapVisit[start.second][start.first] = true
            }

            var processMaxTime = 0
            var copied = Array(size){
                IntArray(size){-1}
            }

            while(processQueue.isNotEmpty()){

                val cur = processQueue.poll() !!
                copied[cur.point.second][cur.point.first] = cur.time

                if(map[cur.point.second][cur.point.first] == 0){
                    processMaxTime = max(processMaxTime, cur.time)
                }

                val dx = listOf(0,0,1,-1)
                val dy = listOf(1,-1,0,0)

                fun isRange(x:Int, y: Int): Boolean = x in 0..<size && y in 0..<size

                for (dir in dx.indices) {
                    val nextX = dx[dir] + cur.point.first
                    val nextY = dy[dir] + cur.point.second

                    if(isRange(nextX, nextY) && !mapVisit[nextY][nextX] && map[nextY][nextX] != 1){
                        mapVisit[nextY][nextX] = true
                        processQueue.add(Node(Pair(nextX, nextY), cur.time+1))
                    }
                }
            }

            var cond = true
            for(y in 0..<size){
                for(x in 0..<size){
                    if(!mapVisit[y][x] && map[y][x] == 0){
                        cond = false
                    }
                }
            }

            if(cond){
                minTime = min(minTime, processMaxTime)
            }
            return
        }

        for(next in index ..<activable.size){
            if(!visit[next]){
                visit[next] = true
                list.add(next)
                dfs(next, count+1, list)
                list.remove(next)
                visit[next] = false
            }
        }
    }

    dfs(0, 0, mutableListOf())
    if(minTime == Int.MAX_VALUE){
        println(-1)
    }else{
        println(minTime)
    }
}

data class Node(
    val point: Pair<Int,Int>,
    val time: Int
)

/**
 * 7 3
 * 2 0 0 0 1 1 0
 * 0 0 1 0 1 2 0
 * 0 1 1 0 1 0 0
 * 0 1 0 0 0 0 0
 * 0 0 0 2 0 1 1
 * 0 1 0 0 0 0 0
 * 2 1 0 0 0 0 2
 */