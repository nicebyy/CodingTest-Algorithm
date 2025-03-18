var totalCount = 0
var totalSet = HashSet<String>()

fun main() {

    val childMap = HashMap<String, HashSet<FileNode>>()

    repeat(readln().split(" ").sumOf { it.toInt() }) {
        val (parent, child, type) = readln().split(" ")

        if (!childMap.containsKey(parent)) {
            childMap[parent] = HashSet()
        }
        childMap[parent]?.add(FileNode(child, type.toInt()))
    }

    repeat(readln().toInt()){
        val (from, to) = readln().split(" ");

        val fromPathList = from.split("/")
        var targetSet = HashSet<FileNode>()

        if(childMap.containsKey(fromPathList.last())){
            targetSet = childMap[fromPathList.last()] !!
            childMap.remove(fromPathList.last())
        }

        val toPathList = to.splitToSequence("/")

        val toPathKey = toPathList.last()
        if(!childMap.containsKey(toPathKey)){
            childMap[toPathKey] = HashSet()
        }
        childMap[toPathKey]?.addAll(targetSet)
    }

    repeat(readln().toInt()) {
        val pathList = readln().split("/")
        val last = pathList.last()
        totalCount = 0
        totalSet = HashSet()
        dfs(last, childMap)
        println("${totalSet.size} ${totalCount}")
    }

}


fun dfs(key: String, childMap: HashMap<String, HashSet<FileNode>>){

    if(!childMap.containsKey(key)){
        return
    }

    for (next in childMap[key]!!) {

        if(next.fileType == 0){
            totalSet.add(next.fileName)
            totalCount++
        }else{
            dfs(next.fileName, childMap)
        }
    }
}

data class FileNode(
    var fileName: String,
    var fileType: Int,
)

/**
 * 3 6
 * main FolderA 1
 * main FolderB 1
 * FolderA File1 0
 * FolderA File2 0
 * FolderB FolderC 1
 * FolderC File4 0
 * FolderC File5 0
 * FolderB File1 0
 * FolderB File3 0
 * 1
 * main/FolderB main/FolderA
 * 3
 * main
 * main/FolderA
 * main/FolderA/FolderC
 *
 */