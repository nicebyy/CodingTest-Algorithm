
fun main() {

    val fileMap = HashMap<String, HashSet<FileNode>>()

    repeat(readln().split(" ").sumOf { it.toInt() }) {
        val (parent, child, type) = readln().split(" ")

        fileMap.putIfAbsent(parent, HashSet())
        fileMap[parent]?.add(FileNode(child, type.toInt()))
    }

    repeat(readln().toInt()) {
        val pathList = readln().split("/")
        val last = pathList.last()
        val found = mutableListOf<String>()

        find(last, fileMap, found)
        println("${found.distinct().size} ${found.size}")
    }
}


fun find(key: String, fileMap: HashMap<String, HashSet<FileNode>>, found: MutableList<String>) {
    fileMap[key]?.forEach { (name, type) ->
        if (type == 0) {
            found.add(name)
        } else {
            find(name, fileMap, found)
        }
    }
}

data class FileNode(
    var fileName: String,
    var fileType: Int,
)

