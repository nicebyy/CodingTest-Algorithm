val br = System.`in`.bufferedReader()
lateinit var weights: IntArray
lateinit var tree: MutableList<MutableList<Int>>
lateinit var dp: Array<IntArray>
var rootNum = -1

fun main() {

    val (n, r) = br.readLine().split(" ").filter{it!=""}.map { it.toInt() }
    rootNum = r
    weights = ("0 ${br.readLine()}").split(" ").filter{it!=""}.map { it.toInt() }.toIntArray()
    tree = MutableList(n + 1) {
        mutableListOf()
    }
    repeat(n - 1) {
        val (from, to) = br.readLine().split(" ").filter{it!=""}.map { it.toInt() }
        tree[from].add(to)
        tree[to].add(from)
    }

    dp = Array(n + 1) { // 가중치 합
        IntArray(n+1) {
            -1
        } // 0: 특별노드 x  ,,, 1: 특별노드
    }
    val result = dfs(rootNum, rootNum, -1)//
    println(result)
}

fun dfs(curNode: Int, last: Int, parent: Int): Int {

    if(dp[curNode][last] != -1){
        return dp[curNode][last]
    }

    // 해당 노드가 특별노드일 경우
    var costSpecial = weights[curNode]
    for (next in tree[curNode]) {
        if(next == parent){
            continue
        }
        costSpecial += dfs(next, curNode, curNode)
    }

    var minCost: Int = Int.MAX_VALUE
    if(curNode == rootNum){
        minCost = costSpecial
    }else{ // 해당 노드가 일반 노드일 경우 비용 계산
        var costNotSpecial = weights[curNode] - weights[last]
        for (next in tree[curNode]) {
            if(next == parent){
                continue
            }
            costNotSpecial += dfs(next, last, curNode)
        }
        minCost = Math.min(costSpecial, costNotSpecial)
    }
    dp[curNode][last] = minCost
    return dp[curNode][last]
}
