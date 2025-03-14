fun main() {

    var arr = Array(readln().toInt()+1){"I"}
    val regString = arr.joinToString(separator = "O")
    val length = readln()
    val target = readln()
    val regex = Regex(regString)
    var count = 0
    var index = 0
    while (true){
        val match = regex.find(target, index) ?: break
        count++
        index = match.range.first + 1
    }
    println(count)
}
/**
 * 1
 * 13
 * OOIOIOIOIIOII
 */