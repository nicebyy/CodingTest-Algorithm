import java.util.PriorityQueue

fun main() = with(System.`in`.bufferedReader()) {

    val target = readLine().toInt()

    val cost = Array(2001) {
        IntArray(2001) {
            Int.MAX_VALUE
        }
    }
    val q = PriorityQueue<Move>()
    q.add(Move(1, 0, 0))
    cost[1][0] = 0
    while (q.isNotEmpty()) {

        val (display, clipboard, time) = q.poll()

        if(display == target){
            println(time)
            return
        }

        // clipboard to display (paste)
        if(display+clipboard in 0..cost.size && cost[display+clipboard][clipboard] > time + 1){
            cost[display+clipboard][display] = time + 1
            q.add(Move(display+clipboard, clipboard, time + 1))
        }

        // display to clipboard (copy)
        if (display in 0..cost.size && cost[display][display] > time + 1) {
            cost[display][display] = time + 1
            q.add(Move(display, display, time + 1))
        }

        //remove 1 on display
        if(display-1 in 0..cost.size && cost[display-1][clipboard] > time + 1){
            cost[display-1][display] = time + 1
            q.add(Move(display-1, clipboard, time + 1))
        }
    }
}

data class Move(
    val display: Int,
    val clipboard: Int,
    val time: Int,
): Comparable<Move> {
    override fun compareTo(other: Move): Int = this.time - other.time
}


