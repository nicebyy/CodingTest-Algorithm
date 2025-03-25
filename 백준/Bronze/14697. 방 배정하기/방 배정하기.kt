import java.util.LinkedList

fun main() {

    val input = readln().split(" ").map { it.toInt() }
    val arr = mutableListOf(input[0], input[1], input[2])
    val num = input[3]

    val queue = LinkedList<Int>()
    queue.add(num)

    var answer = 0
    val visit = Array(num+1){false}
    while (queue.isNotEmpty()){
        val cur = queue.poll()

        if (cur == 0){
            answer = 1
            break
        }

        for (room in arr) {
            if(cur >= room && !visit[cur-room]){
                visit[cur-room] = true
                queue.add(cur-room)
            }
        }
    }
    println(answer)
}


