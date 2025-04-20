val br = System.`in`.bufferedReader()

fun main() {

    val (n,m) = br.readLine().split(" ").map { it.toInt() }
    val parent = IntArray(n){it}

    fun find(x: Int):Int{

        if(x == parent[x]){
            return x
        }

        parent[x] = find(parent[x])
        return parent[x]
    }

    fun union(x: Int, y: Int){
        val xRoot = find(x)
        val yRoot = find(y)
        if(xRoot != yRoot){
            parent[xRoot] = yRoot
        }
    }

    for(turn in 1..m){
        val (from, to) = br.readLine().split(" ").map { it.toInt() }

        val xRoot = find(from)
        val yRoot = find(to)

        if(xRoot != yRoot){
            union(from, to)
        }else{
            println(turn)
            return
        }
    }
    println(0)
}

/**
 * 6 5
 * 0 1
 * 1 2
 * 1 3
 * 0 3
 * 4 5
 */

/**
 * 3 3
 * 0 1
 * 1 2
 * 2 0
 */


/**
 * 1) parent 배열로 일단 자기자신을 가르키게 함.
 * 2)
 */