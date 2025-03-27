import java.util.*

fun main(){
    val scanner = Scanner(System.`in`)
    val answers = StringBuilder()

    while (true) {
        val n = scanner.nextInt()
        val m = scanner.nextInt()
        if (n == 0 && m == 0) {
            println(answers)
            break
        }
        scanner.nextLine() // 개행 문자 처리

        val memberMap: MutableMap<Int, Int> = HashMap()

        for (i in 0..<n) {
            val input = scanner.nextLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (numStr in input) {
                val num = numStr.toInt()
                memberMap[num] = memberMap.getOrDefault(num, 0) + 1
            }
        }

        val rankMap = TreeMap<Int, MutableList<Int>>(Comparator.reverseOrder())

        for ((number, score) in memberMap) {
            rankMap.putIfAbsent(score, ArrayList())
            rankMap[score]!!.add(number)
        }

        var rank = 0
        for ((_, list) in rankMap) {
            rank++
            if (rank == 2) {
                Collections.sort(list)
                for (num in list) {
                    answers.append(num).append(" ")
                }
                answers.append("\n")
                break
            }
        }
    }
    scanner.close()
}