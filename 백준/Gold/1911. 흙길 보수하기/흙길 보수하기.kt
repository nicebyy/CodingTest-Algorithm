import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()){

    val (n, size) = readLine().split(" ").map { it.toInt() }

    val pq = PriorityQueue<Pair<Int,Int>>(){o1,o2 -> o1.first - o2.first}

    repeat(n){
        val (from, to) = readLine().split(" ").map { it.toInt() }
        pq.add(Pair(from, to))
    }

    var lastPos = 0
    var count = 0
    while (pq.isNotEmpty()) {

        val (from, to) = pq.poll()
        var width = to-from

        if(lastPos >= to){
            continue
        }else if(lastPos >= from){
            width -= (lastPos - from)
        }

        val overed = (size-(width % size))%size
        count += (width/size)
        if(overed>0){
            count++
        }
        lastPos = (to + overed)
    }

    println(count)
}

/**
 * 3 3
 * 1 6
 * 13 17
 * 8 12
 */

/**
 * 3 100000
 * 1 50
 * 51 55
 * 70 80
 */