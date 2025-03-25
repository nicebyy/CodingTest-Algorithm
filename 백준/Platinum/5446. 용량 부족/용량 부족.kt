fun main() {

    val tc = readln().toInt()
    repeat(tc) {
        val root = Node(HashMap())
        Node.deleteCount = 0
        repeat(readln().toInt()) {
            val word = readln()
            root.insert(word, 0)
        }

        repeat(readln().toInt()) {
            val word = readln()
            root.mark(word, 0)
        }

        var checkAllDeletable = true
        for ((key, value) in root.map) {
            if(!value.isDeletable){
                checkAllDeletable = false
                break
            }
        }

        if(!checkAllDeletable){
            root.findDeletable()
        }else{
            Node.deleteCount = 1
        }
        println(Node.deleteCount)
    }
}

data class Node(
    val map: HashMap<Char, Node>,
    var isEnd: Boolean = false,
    var isDeletable: Boolean = true,
) {
    fun insert(word: String, index: Int) {
        if (index == word.length) {
            return
        }

        if (!map.containsKey(word[index])) {
            map[word[index]] = Node(HashMap(), false)
        }
        val nextNode = map[word[index]]!!
        nextNode.insert(word, index + 1)
        if (index == word.length - 1) {
            nextNode.isEnd = true
        }
    }

    fun mark(word: String, index: Int) {

        if (index == word.length || !map.containsKey(word[index])) {
            return
        }

        val nextNode = map[word[index]]!!
        nextNode.isDeletable = false
        nextNode.mark(word, index + 1)
    }

    fun findDeletable() {

        for ((key, nextNode) in map) {

            if (nextNode.isDeletable) {
                deleteCount++
                continue
            } else if (nextNode.isEnd) {
                deleteCount++
            }
            nextNode.findDeletable()
        }
    }

    companion object {
        var deleteCount = 0
    }
}