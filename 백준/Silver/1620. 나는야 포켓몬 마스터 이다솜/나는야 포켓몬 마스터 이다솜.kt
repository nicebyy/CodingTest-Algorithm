fun main() {

    val (n, queryCount) = readln().split(" ").map { it.toInt() }
    val nameMap = HashMap<String,Int>()
    val idMap = HashMap<Int,String>()
    repeat(n){
        val id = it+1
        val name = readln()
        nameMap[name] = id
        idMap[id] = name
    }
    repeat(queryCount){
        val query = readln()

        val nameValue = nameMap[query]
        if(nameValue!=null){
            println(nameValue)
        }else{
            println(idMap[query.toInt()])
        }
    }
}
