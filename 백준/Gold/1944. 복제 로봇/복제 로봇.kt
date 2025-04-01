import java.util.LinkedList
import java.util.PriorityQueue

lateinit var parent: IntArray

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() };
    val map = Array(n) {
        readln().toCharArray()
    }
    val vertexList = ArrayList<Vertex>()
    val queue = LinkedList<Vertex>()
    var seq = 0
    for (y in map.indices) {
        for (x in map[y].indices) {
            if (map[y][x] == 'S' || map[y][x] == 'K') {
                val vertex = Vertex(x, y, 0, seq)
                vertexList.add(vertex)
                queue.add(vertex)
                seq++
            }
        }
    }
    val visit = Array(vertexList.size) {
        Array(n) {
            BooleanArray(n) {
                false
            }
        }
    }
    val priorityQueue = PriorityQueue<Edge>()

    while (queue.isNotEmpty()) {

        val cur = queue.poll()!!

        visit[cur.from][cur.y][cur.x] = true

        for (i in Vertex.dx.indices) {
            val nextX = cur.x + Vertex.dx[i]
            val nextY = cur.y + Vertex.dy[i]

            if (nextX in 0 until n && nextY in 0 until n && map[nextY][nextX] != '1' && !visit[cur.from][nextY][nextX]) {
                visit[cur.from][nextY][nextX] = true
                if (map[nextY][nextX] == 'K') {

                    for (vertexIndex in vertexList.indices) {
                        val arrival = vertexList[vertexIndex]

                        if (arrival.x == nextX && arrival.y == nextY) {
                            priorityQueue.add(Edge(vertexIndex, cur.from, cur.depth + 1))
                            queue.add(Vertex(nextX, nextY, cur.depth + 1, vertexIndex))
                            break
                        }
                    }
                } else {
                    queue.add(Vertex(nextX, nextY, cur.depth + 1, cur.from))
                }
            }
        }
    }
    parent = IntArray(vertexList.size){it}
    var totalCost = 0
    var connected = 0
    while (priorityQueue.isNotEmpty()){

        val cur = priorityQueue.poll() !!

        val from = cur.from
        val to = cur.to

        val fromRoot = find(from)
        val toRoot = find(to)

        if(fromRoot == toRoot){
            continue
        }
        totalCost += cur.weight
        union(fromRoot,toRoot)
        connected++
    }
    if(connected != (vertexList.size-1)){
        println(-1)
    }else{
        println(totalCost)
    }
}

fun find(x: Int): Int{
    if(x == parent[x]){
        return x
    }
    parent[x] = find(parent[x])
    return parent[x]
}

fun union(x: Int, y: Int){

    val xRoot =  find(x)
    val yRoot = find(y)

    if(xRoot != yRoot){
        parent[yRoot] = xRoot
    }
}


data class Vertex(
    val x: Int,
    val y: Int,
    var depth: Int = 0,
    var from: Int = -1,
) {

    companion object {
        val dx = arrayOf(0, 0, 1, -1)
        val dy = arrayOf(1, -1, 0, 0)
    }
}

data class Edge(
    val from: Int,
    val to: Int,
    val weight: Int,
) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int {
        return this.weight - other.weight
    }
}
