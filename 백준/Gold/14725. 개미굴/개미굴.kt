import java.util.TreeMap

fun main() {

    val n = readln().toInt()
    val root = Node(TreeMap())
    repeat(n) {
        val input = readln().split(" ")
        root.pushWord(input.drop(1))
    }
    root.printWord("")
}

data class Node(
    val map: TreeMap<String, Node>,
) {
    fun pushWord(words: List<String>) {

        val thisTarget = words[0]
        val rest = words.drop(1)

        if (map[thisTarget] == null) {
            map[thisTarget] = Node(TreeMap())
        }
        if (rest.isNotEmpty()) {
            map[thisTarget]?.pushWord(rest)
        }
    }

    fun printWord(prefix: String) {
        for (entry in map) {
            println("$prefix${entry.key}")
            entry.value.printWord("$prefix--")
        }
    }
}
