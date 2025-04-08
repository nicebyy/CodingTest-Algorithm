import java.util.LinkedList

val br = System.`in`.bufferedReader()

fun main() {

    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val distance = IntArray(200001) {
        Int.MAX_VALUE
    }

    val q = ArrayDeque<Point>()
    q.add(Point(n,0))
    distance[n] = 0

    while (q.isNotEmpty()) {

        val cur = q.removeFirst()

        if(cur.pos == k){
            println(distance[k])
            return
        }

        // forward
        if(cur.pos + 1 in 0..100000 && distance[cur.pos + 1] > distance[cur.pos] + 1){
            distance[cur.pos + 1] = distance[cur.pos] + 1
            q.addLast(Point(cur.pos+1, distance[cur.pos + 1]))
        }

        // backward
        if(cur.pos - 1 in 0..100000 && distance[cur.pos - 1] > distance[cur.pos] + 1){
            distance[cur.pos - 1] = distance[cur.pos] + 1
            q.addLast(Point(cur.pos-1, distance[cur.pos + 1]))
        }

        // warp
        if(cur.pos*2 in 0..100000 && distance[cur.pos*2] > distance[cur.pos]){
            distance[cur.pos*2] = distance[cur.pos]
            q.addFirst(Point(cur.pos*2, distance[cur.pos*2]))
        }
//        cur.next()
//            .filter { it.pos in 0..100000 }
//            .forEach {
//                if (distance[it.pos] < distance[cur.pos] + it.weight){
//                    distance[it.pos] = distance[cur.pos] + it.weight
//                    when(it.weight){
//                        0 -> q.addFirst(it)
//                        1 -> q.addLast(it)
//                    }
//                }
//            }
    }
}

data class Point(
    val pos: Int,
    val weight: Int,
) {

    fun next(): List<Point> {
        return listOf(
            Point(pos + 1, weight + 1),
            Point(pos - 1, weight + 1),
            Point(pos * 2, weight),
        )
    }
}


/**
 * 5 17
 */