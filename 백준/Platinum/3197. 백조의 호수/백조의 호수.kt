import java.util.LinkedList

val br = System.`in`.bufferedReader()

fun main() {
    val solution = Solution()
    br.readLine().split(" ")
        .map { it.toInt() }
        .let { (h, w) ->
            solution.h = h
            solution.w = w
        }
    Array(solution.h) {
        br.readLine().toCharArray()
    }.let { solution.map = it }
    solution.solution()
}

class Solution(
    var h: Int = 0,
    var w: Int = 0,
    var map: Array<CharArray> = arrayOf(),
    var entry: MutableList<Point> = mutableListOf(),
    var visit: Array<BooleanArray> = arrayOf(),
    var start: Point = Point(0, 0),
    var end: Point = Point(0, 0),
    var waterQueue: LinkedList<Point> = LinkedList(),
    var searchQueue: LinkedList<Point> = LinkedList()
) {

    fun solution() {

        for (i in map.indices) {
            for (j in map[i].indices) {
                if (map[i][j] == 'L') {
                    entry.add(Point(j, i))
                    map[i][j] = '.'
                }

                if (map[i][j] == '.') {
                    waterQueue.add(Point(j, i))
                }
            }
        }

        visit = Array(h){ BooleanArray(w){false} }
        start = entry.first()
        end = entry.last()
        searchQueue.add(start)
        visit[start.y][start.x] = true
        var day = 0
        while (true) {
            if(move()){
                break
            }
            melt()
            day++
        }
        println(day)
    }

    fun move(): Boolean {

        val nextTargets = LinkedList<Point>()

        while (searchQueue.isNotEmpty()) {
            val cur = searchQueue.poll() !!

            if(cur == end){
                return true
            }

            for (next in cur.nextDir()) {

                if(!isRange(next.x, next.y) || visit[next.y][next.x]){
                    continue
                }

                visit[next.y][next.x] = true

                if(map[next.y][next.x] == '.'){
                    searchQueue.add(next)
                }else{
                    nextTargets.add(next)
                }
            }
        }
        searchQueue = nextTargets
        return false
    }

    fun melt(){
        val size = waterQueue.size
        for(i in 0..<size){

            val cur = waterQueue.poll()

            for (next in cur.nextDir()) {
                if(!isRange(next.x, next.y)){
                    continue
                }

                if(map[next.y][next.x] == 'X'){
                    map[next.y][next.x] = '.'
                    waterQueue.add(next)
                }
            }
        }
    }

    fun isRange(x: Int, y: Int): Boolean {
        return x in 0..<w && y in 0..<h
    }
}

data class Point(
    val x: Int,
    val y: Int
) {
    fun nextDir(): List<Point> {
        return direction.map { Point(it.x + this.x, it.y + this.y) }
    }

    companion object {
        val direction = arrayOf(Point(0, 1), Point(0, -1), Point(1, 0), Point(-1, 0))
    }
}


/**
 * 1) 빙판을 녹인다
 * 2) 백조를 출발 시켜본다 ?
 */