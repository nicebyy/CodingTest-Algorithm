import java.util.LinkedList
import java.util.PriorityQueue
import java.util.TreeMap
import kotlin.collections.ArrayList

var map = HashMap<String, ArrayList<String>>()
var graph = TreeMap<String, ArrayList<String>>()
var inDegree = HashMap<String, Int>()
val answer = StringBuilder()
fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val names = readLine().split(" ")
    names.forEach { name ->
        inDegree[name] = 0
        map[name] = ArrayList()
        graph[name] = ArrayList()
    }

    val m = readLine().toInt()
    repeat(m) {
        val (from, to) = readLine().split(" ")
        inDegree[from] = inDegree[from]!! +1
        map[to]!!.add(from)
    }
    val rootList = ArrayList<String>()
    inDegree
        .filter { (_, value) ->  value == 0}
        .forEach { (name,_)->rootList.add(name) }
    val q = LinkedList<String>()
    q.addAll(rootList)


    while (q.isNotEmpty()){

        val poll = q.poll() !!
        for (next in map[poll]!!) {
            inDegree[next] = inDegree[next]!!-1
            if(inDegree[next] == 0){
                graph[poll]!!.add(next)
                q.add(next)
            }
        }
    }

    answer.append("${rootList.size}\n")
        .append("${rootList.joinToString(" ")}\n")
    graph.forEach { (key, value) ->
        answer.append("$key ${value.size} ${value.sorted().joinToString(" ")}\n")
    }
    print(answer)
}
