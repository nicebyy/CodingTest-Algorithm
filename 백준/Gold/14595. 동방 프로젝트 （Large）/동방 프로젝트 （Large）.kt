import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

lateinit var parent:IntArray

fun main() {

    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val actCount = br.readLine().toInt()

    parent = IntArray(n+1){it}
    val arr = Array(actCount){
        br.readLine().split(" ").map {
            it.toInt()
        }.toIntArray() // 0 : from 1: to
    }
    Arrays.sort(arr){o1,o2 -> o1[0] - o2[0]}

    var prevStart = 0
    var prevEnd = 0
    var room = 0
    for ((from, to) in arr) {

        if(prevEnd < from){
            prevEnd = to
            prevStart = from
            for(index in prevStart+1..to){
                union(prevStart, index)
            }
            continue
        }

        if(prevEnd < to){
            for(index in prevEnd+1..to){
                union(prevEnd, index)
            }
            prevEnd = to
        }
    }

    println(parent.distinct().size-1)
}

fun find(x:Int): Int{
    if(parent[x] == x){
        return x
    }
    parent[x] = find(parent[x])
    return parent[x]
}

fun union(x:Int, y:Int,){

    var xRoot = find(x)
    var yRoot = find(y)

    if(xRoot != yRoot){
        parent[yRoot] = xRoot
    }
}
/**
 * 5
 * 2
 * 1 2
 * 2 4
 */