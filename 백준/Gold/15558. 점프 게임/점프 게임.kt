import java.util.*

val br = System.`in`.bufferedReader()

fun main() {

    val (n, amount) = br.readLine().split(" ").map { it.toInt() }
    val left = br.readLine().split("").filter { it != "" }.map { it.toInt() }
    val right = br.readLine().split("").filter { it!="" }.map { it.toInt() }
    //left : 0 right : 1
    val visit = Array(n) {
        BooleanArray(2) {
            false
        }
    }

    val start = Node(0, 0, 0)
    visit[start.pos][start.dir] = true
    val queue = LinkedList<Node>()
    queue.add(start)

    while (queue.isNotEmpty()) {

        val cur = queue.poll()!!

        val movable = mutableListOf(
            Node(cur.pos + 1, cur.dir, cur.depth + 1),
            Node(cur.pos - 1, cur.dir, cur.depth + 1),
            Node(cur.pos + amount, when(cur.dir){
                1 -> 0
                else -> 1
            }, cur.depth + 1)
        )

        for (next in movable) {

            if(next.pos >= n ){
                println(1)
                return
            }else if(next.pos>=0 && !visit[next.pos][next.dir] && next.pos >= next.depth){

                if((next.dir == 0 && left[next.pos] == 0) || next.dir ==1 && right[next.pos] == 0){
                    continue
                }

                visit[next.pos][next.dir] = true
                queue.add(next)
            }
        }
    }
    println(0)
}

data class Node(
    val pos: Int,
    val dir: Int,
    val depth: Int = 0,
)


/**
 * 7 3
 * 1110110
 * 1011001
 */