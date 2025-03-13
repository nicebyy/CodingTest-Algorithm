import java.lang.StringBuilder
import java.util.Arrays
import java.util.LinkedList

var dx: Array<Int> = arrayOf(0,0,1,-1)
var dy: Array<Int> = arrayOf(1,-1,0,0)

fun main(args: Array<String>) {

    val (h, w) = readln().split(" ").map { it.toInt() }
    val queue = LinkedList<Point>()
    val visit = Array(h){Array(w){false}}
    val map = Array(h) { y ->
        val input = readln().split(" ")
        IntArray(w) { x ->
            val value = input[x].toInt()
            if (input[x].toInt() == 2) {
                queue.add(Point(x,y))
            }
            value
        }
    }
    val start = queue.peek()
    map[start.y][start.x] = 0
    visit[start.y][start.x] = true
    
    while (!queue.isEmpty()) {
        val cur = queue.poll()
        for (i in dx.indices) {
            val nextX = dx[i] + cur.x
            val nextY = dy[i] + cur.y

            if(isRange(nextX,nextY,w,h) && !visit[nextY][nextX] && map[nextY][nextX] != 0){
                visit[nextY][nextX] = true;
                map[nextY][nextX] = cur.depth+1
                queue.add(Point(nextX,nextY,cur.depth+1));
            }
        }
    }

    for(i in map.indices){
        val row = StringBuilder()
        for(j in map[0].indices){
            if(visit[i][j]){
                row.append("${map[i][j]} ")
            }else if(map[i][j]==0){
                row.append("0 ")
            }else{
                row.append("-1 ")
            }
        }
        println(row.toString())
    }
}

fun isRange(x:Int, y:Int, width: Int, height: Int): Boolean = x>=0 && y>=0 && x<width && y<height
data class Point(var x: Int = 0, var y: Int = 0, var depth: Int=0)

/**
 * 15 15
 * 2 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 0 0 0 0 0
 * 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 0 1 0 0 0
 * 1 1 1 1 1 1 1 1 1 1 0 1 1 1 1
 */