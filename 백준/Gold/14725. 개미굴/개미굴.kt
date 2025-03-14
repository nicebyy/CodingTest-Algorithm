import java.util.TreeMap

fun main() {

    val n = readln().toInt()

    val root = Node(TreeMap())
    repeat(n){

        val input = readln().split(" ")
        val size = input[0].toInt()

//        println(input.drop(1))

//        for(i in 1..size){
            root.pushWord(input.drop(1))
//        }
    }

    root.printWord("")

}

data class Node(
    val map: TreeMap<String,Node>,
){
    fun pushWord(words: List<String>){

        val thisTarget = words[0]
        val rest = words.drop(1)

        if(map[thisTarget] == null){
            map[thisTarget] = Node(TreeMap())
        }
        if(rest.isNotEmpty()){
            map[thisTarget]?.pushWord(rest)
        }
    }

    fun printWord(prefix: String){
        for (entry in map) {
            println("$prefix${entry.key}")
            entry.value.printWord("$prefix--")
        }
    }
}
/**
 * 4
 * 2 KIWI BANANA
 * 2 KIWI APPLE
 * 2 APPLE APPLE
 * 3 APPLE BANANA KIWI
 */