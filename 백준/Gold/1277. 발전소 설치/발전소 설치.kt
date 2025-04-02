import java.util.PriorityQueue
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

val br = System.`in`.bufferedReader()
var n = 0
var m = 0
var limit = 0.0

fun main() {

    val (
        nodeCount, edgeCount
    ) = br.readLine().split(" ").map { it.toInt() }
    n = nodeCount
    m = edgeCount
    limit = br.readLine().toDouble()

    val distance = DoubleArray(n+1){Double.MAX_VALUE}
    val nodeList = Array(n+1){Node()}

    for(nodeNum in 1..n){
        val (
            x,y
        ) = br.readLine().split(" ").map { it.toInt() }
        nodeList[nodeNum] = Node(x,y)
    }
    repeat(edgeCount){
        val (
            from,to
        ) = br.readLine().split(" ").map { it.toInt() }
        nodeList[from].children.add(to)
        nodeList[to].children.add(from)
    }

    distance[1] = 0.0

    val pq = PriorityQueue<Edge>() //next, weight
    pq.add(Edge(1,0.0))
    while (pq.isNotEmpty()){

        val (curNode, curWeight) = pq.poll() !!

        for(index in 1..n){ // 다른 노드들 넘버 순회

            if(index == curNode){
                continue
            }

            if(nodeList[curNode].children.contains(index)){ // 이미 연결된 상태 라면

                if(distance[index] > curWeight){
                    distance[index] = curWeight
                    pq.add(Edge(index,curWeight))
                }
            }else{
                val diff = nodeList[index] - nodeList[curNode]

                if(limit < diff){
                    continue
                }

                if(distance[index] > distance[curNode] + diff){ // 더 짧은 거리로 갱신이 가능하다면
                    distance[index] = distance[curNode] + diff
                    pq.add(Edge(index,distance[curNode] + diff))
                }
            }
        }
    }
    println((distance[n]*1000.0).toLong())
}

data class Node(
    val x: Int = 0,
    val y: Int = 0,
    val children: MutableList<Int> = mutableListOf()
){

    operator fun minus(o: Node): Double {
        val xDiff = (this.x - o.x).toDouble()
        val yDiff = (this.y - o.y).toDouble()
        return sqrt(xDiff.pow(2) + yDiff.pow(2))
    }
}

data class Edge(
    val next: Int,
    val weight: Double,
) : Comparable<Edge>{
    override fun compareTo(other: Edge): Int {
        return this.weight.compareTo(other.weight)
    }
}

/**
 * 9 3
 * 2.0
 * 0 0
 * 0 1
 * 1 1
 * 2 1
 * 2 2
 * 3 2
 * 3 3
 * 4 1
 * 4 3
 * 1 2
 * 2 3
 * 3 4
 */

/**
 * 다익스트라 핵심이론
 * 하나의 start. 탐색할 때마다 해당 위치에서 가장 거리가 짧은 다른 노드들을 탐색.
 * 한번 탐색한 노드들은 재 방문 x
 *
 *
 * 3 1
 * 200000.0
 * -100000 -100000
 * 100000 0
 * 100000 100000
 * 1 2
 */

/**
 *
 *
 */