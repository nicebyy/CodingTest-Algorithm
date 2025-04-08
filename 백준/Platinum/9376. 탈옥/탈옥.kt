val br = System.`in`.bufferedReader()

fun main() {

    val tc = br.readLine().toInt()

    repeat(tc) {
        val (h, w) = br.readLine().split(" ").map { it.toInt() }
        Point.setRange(h + 2, w + 2)
        Point.initMap(Array(h) {
            br.readLine().toCharArray()
        })
        val prisoner1Dist = Point.prisoner.first().getDist()
        val prisoner2Dist = Point.prisoner.last().getDist()
        val outsideDist = Point.outside.getDist()
        val sumDist = Array(Point.map.size){Array(Point.map[0].size){0}}

        var min = 1000000
        for(i in Point.map.indices){
            for(j in Point.map[i].indices){
                sumDist[i][j] = prisoner1Dist[i][j] + prisoner2Dist[i][j] + outsideDist[i][j]
                if(Point.map[i][j] == '#'){
                    sumDist[i][j] -=2
                }
                min = Math.min(min, sumDist[i][j])
            }
        }
        println(min)
    }
}


data class Point(
    val x: Int,
    val y: Int,
) {

    fun isRange(x: Int, y: Int): Boolean = x in 0..<w && y in 0..<h

    fun isRange(): Boolean = x in 0..<w && y in 0..<h

    fun getDist(): Array<Array<Int>> {
        var distance = Array(h) { Array(w) { 1000000 } }
        distance[this.y][this.x] = 0
        val list = ArrayDeque<Point>()
        list.addFirst(Point(this.x, this.y))

        while (list.isNotEmpty()) {

            val cur = list.removeFirst()

            for (next in cur.nextDir()) {

                if(map[next.y][next.x] == '*'){
                    continue
                }

                if (map[next.y][next.x] == '.') {
                    if(distance[next.y][next.x] > distance[cur.y][cur.x]){
                        distance[next.y][next.x] = distance[cur.y][cur.x]
                        list.addFirst(next)
                    }
                } else{
                    if (distance[next.y][next.x] > distance[cur.y][cur.x] + 1) {
                        distance[next.y][next.x] = distance[cur.y][cur.x] + 1
                        list.addLast(next)
                    }
                }
            }
        }
        return distance
    }

    operator fun plus(o: Point): Point {
        return Point(this.x + o.x, this.y + o.y)
    }

    fun nextDir(): List<Point> {
        return listOf(
            Point(0, 1),
            Point(0, -1),
            Point(1, 0),
            Point(-1, 0),
        ).map { it+this}.filter { it.isRange() }
    }

    companion object {
        var h = 0
        var w = 0
        lateinit var map: Array<CharArray>
        var prisoner = mutableListOf<Point>()
        var outside = Point(0, 0)

        fun setRange(h: Int, w: Int) {
            this.h = h
            this.w = w
            this.map = Array(this.h) {
                CharArray(this.w) { '.' }
            }
        }

        fun initMap(arr: Array<CharArray>) {

            prisoner.clear()

            // 상단, 하단 테두리 포함하여 초기화된 map은 이미 setRange에서 생성됨
            for (y in 1..arr.size) {
                map[y] = charArrayOf('.') + arr[y - 1] + charArrayOf('.')
            }

            // 상단, 하단도 '.' 로 유지 (이미 setRange에서 생성됨)

            // 죄수 위치 찾기 및 저장
            for (y in 0 until h) {
                for (x in 0 until w) {
                    when (map[y][x]) {
                        '$' -> {
                            prisoner.add(Point(x, y))
                            map[y][x] = '.' // 죄수 자리는 빈 공간으로 간주
                        }
                    }
                }
            }
        }
    }
}

/**
 * 1
 * 9 9
 * *#**#**#*
 * *#**#**#*
 * *#**#**#*
 * *#**.**#*
 * *#*#.#*#*
 * *$##*##$*
 * *#*****#*
 * *.#.#.#.*
 * *********
 */
