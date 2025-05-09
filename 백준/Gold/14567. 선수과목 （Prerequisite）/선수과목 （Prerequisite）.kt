import java.util.LinkedList
import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {

    val (n, m) = readLine().split(" ").map { it.toInt() }
    val list = ArrayList<ArrayList<Int>>()
    val inDegree = IntArray(n+1)

    repeat(n+1){list.add(ArrayList())}
    repeat(m){
        val (from, to) = readLine().split(" ").map { it.toInt() }
        list[from].add(to)
        inDegree[to]++
    }

    val processQueue = LinkedList<Int>()
    val waitingQueue = LinkedList<Int>()

    inDegree.forEachIndexed { index, value ->
        if(value == 0 && index >0){
            waitingQueue.add(index)
        }
    }
    var cost = 0
    val resultArray = IntArray(n+1)
    while (waitingQueue.isNotEmpty()){
        cost++
        processQueue.addAll(waitingQueue)
        waitingQueue.clear()

        while (processQueue.isNotEmpty()){
            val cur = processQueue.poll() !!
            resultArray[cur] = cost
            for (next in list[cur]) {
                if(--inDegree[next] == 0){
                    waitingQueue.add(next)
                }
            }
        }
    }
    println(resultArray.drop(1).joinToString(" "))
}

/**
 * 3 2
 * 2 3
 * 1 2
 */