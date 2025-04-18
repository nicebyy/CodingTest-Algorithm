val br = System.`in`.bufferedReader()
val result = mutableListOf<Int>()

fun main() {

    val components = IntArray(1000001) { it }
    val componentCount = IntArray(1000001) { 1 }

    repeat(br.readLine().toInt()) {

        val input = br.readLine().split(" ")
        val query = input[0]

        when (query) {
            "I" -> {
                val (from, to) = input.drop(1).map { it.toInt() }
                val fromRoot = components.find(from)
                val toRoot = components.find(to)
                components.union(fromRoot, toRoot, componentCount)
            }

            else -> {
                val target = input[1].toInt()
                val root = components.find(target)
                val count = componentCount[root]
                result.add(count)
            }
        }
    }
    println(result.joinToString(separator = "\n"))
}

fun IntArray.find(x: Int): Int {
    if (this[x] == x) {
        return x
    }
    this[x] = find(this[x])
    return this[x]
}

fun IntArray.union(x: Int, y: Int, componentCount: IntArray) {
    val xRoot = find(x)
    val yRoot = find(y)

    if (xRoot != yRoot) {
        this[yRoot] = xRoot
        componentCount[xRoot] += componentCount[yRoot]
    }
}