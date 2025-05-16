fun main() = with(System.`in`.bufferedReader()) {

    val (h, _, x, y, _) = readLine().split(" ").map { it.toInt() }
    val map = Array(h) {
        readLine().split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    var dice = Dice(y, x)
    readLine().split(" ")
        .map { it.toInt() }
        .forEach {
//            println("command : $it")
            when (it) {
                1 -> dice.rollRight(map)
                2 -> dice.rollLeft(map)
                3 -> dice.rollUp(map)
                4 -> dice.rollDown(map)
            }
        }
}

data class Dice(
    var x: Int,
    var y: Int,
    var status: Array<IntArray> = Array(4) { IntArray(3) { 0 } }
) {

    fun rollDown(map: Array<IntArray>) {
        if (y + 1 >= map.size) {
            return
        }
        this.y = y + 1
        val nextStatus: Array<IntArray> = Array(4) { IntArray(3) { 0 } }
        nextStatus[3][1] = status[0][1]
        nextStatus[0][1] = status[1][1]
        nextStatus[2][1] = status[3][1]

        nextStatus[1][0] = status[1][0]
        nextStatus[1][2] = status[1][2]

        if (map[y][x] > 0) {
            nextStatus[1][1] = map[y][x]
            map[y][x] = 0
        } else {
            map[y][x] = status[2][1]
            nextStatus[1][1] = status[2][1]
        }
        status = nextStatus
        printUp(map)
    }

    fun rollUp(map: Array<IntArray>) {
        if (y - 1 < 0) {
            return
        }
        this.y = y - 1
        val nextStatus: Array<IntArray> = Array(4) { IntArray(3) { 0 } }
        nextStatus[0][1] = status[3][1]
        nextStatus[2][1] = status[1][1]
        nextStatus[3][1] = status[2][1]

        nextStatus[1][0] = status[1][0]
        nextStatus[1][2] = status[1][2]

        if (map[y][x] > 0) {
            nextStatus[1][1] = map[y][x]
            map[y][x] = 0
        } else {
            map[y][x] = status[0][1]
            nextStatus[1][1] = status[0][1]
        }
        status = nextStatus
        printUp(map)
    }

    fun rollRight(map: Array<IntArray>) {
        if (x + 1 >= map[0].size) {
            return
        }
        this.x = x + 1
        val nextStatus: Array<IntArray> = Array(4) { IntArray(3) { 0 } }

        nextStatus[1][0] = status[1][1]
        nextStatus[1][2] = status[3][1]
        nextStatus[3][1] = status[1][0]

        nextStatus[0][1] = status[0][1]
        nextStatus[2][1] = status[2][1]

        if (map[y][x] > 0) {
            nextStatus[1][1] = map[y][x]
            map[y][x] = 0
        } else {
            map[y][x] = status[1][2]
            nextStatus[1][1] = status[1][2]
        }
        status = nextStatus
        printUp(map)
    }

    fun rollLeft(map: Array<IntArray>) {
        if (x - 1 < 0) {
            return
        }
        this.x = x - 1
        val nextStatus: Array<IntArray> = Array(4) { IntArray(3) { 0 } }

        nextStatus[1][0] = status[3][1]
        nextStatus[1][2] = status[1][1]
        nextStatus[3][1] = status[1][2]

        nextStatus[0][1] = status[0][1]
        nextStatus[2][1] = status[2][1]

        if (map[y][x] > 0) {
            nextStatus[1][1] = map[y][x]
            map[y][x] = 0
        } else {
            map[y][x] = status[1][0]
            nextStatus[1][1] = status[1][0]
        }
        status = nextStatus
        printUp(map)
    }

    fun printUp(map: Array<IntArray>) {

//        for(rows in status.indices){
//            println(status[rows].contentToString())
//        }
//        println("주사위 맨 위 상테 =======")
//        println("맵 상태 =======")
//        for(rows in map.indices){
//            println(map[rows].contentToString())
//        }
        println(
            status[3][1]
//            "====== result : ${status[3][1]}=========="
        )
    }
}

/**
 * 3 3 0 0 16
 * 0 1 2
 * 3 4 5
 * 6 7 8
 * 4 4 1 1 3 3 2 2 4 4 1 1 3 3 2 2
 *
 */