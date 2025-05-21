
fun main() = with(System.`in`.bufferedReader()) {

    val n = readLine().toInt()
    val treeNodes = Array(n+1){TreeNode(it)}

    for(it in 1..n){
        val (from, to) = readLine().split(" ").map { it.toInt() }

        if(from >= 0){
            treeNodes[it].left = treeNodes[from]
        }
        if(to >= 0){
            treeNodes[it].right = treeNodes[to]
        }
    }

    val count = readLine().toLong()

    val root = treeNodes[1]

    fun dfs(node: TreeNode, k: Long){

        if(node.left == null && node.right == null){
            println(node.value)
            return
        }else if(node.left != null && node.right != null){

            if(k%2 == 1L){ // 홀수 -> 왼쪽으로 떨어짐
                dfs(node.left!!, 1+k/2)
            }else{
                dfs(node.right!!, k/2)
            }
        }else{
            node.left?.let { dfs(it, k) }
            node.right?.let { dfs(it, k) }
        }
    }

    dfs(root, count)
}

data class TreeNode(
    val value:Int,
    var left: TreeNode? = null,
    var right: TreeNode? = null,
){

}