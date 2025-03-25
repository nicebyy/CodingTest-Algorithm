import java.util.LinkedList
import java.util.PriorityQueue

fun main() {

    val (
        n, treeCount, year
    ) = readln().split(" ").map { it.toInt() }

    Field.fieldSize = n
    val aidMap = Array(n) { IntArray(n) }
    val trees = Array(n) {
        Array(n) {
            Field(PriorityQueue(), 5)
        }
    }
    for (i in aidMap.indices) {
        aidMap[i] = readln().split(" ").map { it.toInt() }.toIntArray()
    }
    repeat(treeCount) {
        val (y, x, value) = readln().split(" ").map { it.toInt() }
        trees[y-1][x-1].treeQueue.add(value)
    } // end init

    repeat(year) {

        for (y in trees.indices) {
            for (x in trees[y].indices) {
                trees[y][x].addAids(x, y)
            }
        }

        while (Field.spreadTargets.isNotEmpty()){
            val (x, y) = Field.spreadTargets.poll()

            for(i in Field.dx.indices){
                val nextX = x+Field.dx[i]
                val nextY = y+Field.dy[i]

                if(Field.isRange(nextX, nextY)){
                    trees[nextY][nextX].treeQueue.add(1)
                }
            }
        }

        for (y in trees.indices) {
            for (x in trees[y].indices) {
                trees[y][x].chargeAids(aidMap[y][x])
            }
        }
    }

    var liveTreeSum = 0
    for (y in trees.indices) {
        for (x in trees[y].indices) {
            liveTreeSum += trees[y][x].treeQueue.size
        }
    }

    println(liveTreeSum)
}

data class Field(
    var treeQueue: PriorityQueue<Int>,
    var aids: Int,
) {

    fun addAids(x: Int, y: Int) {
        val processQueue: LinkedList<Int> = LinkedList()
        var nextAids = 0
        while (this.treeQueue.isNotEmpty()) {
            val curTree = this.treeQueue.poll()
            if (aids >= curTree) {
                aids -= curTree
                processQueue.add(curTree + 1)
            } else {
                nextAids += curTree / 2
            }
        }
        for (nextTree in processQueue) {
            if (nextTree % 5 == 0) {
                spreadTargets.add(TreeNode(x, y))
            }
            this.treeQueue.add(nextTree)
        }
        this.aids += nextAids
    }

    fun chargeAids(amount: Int){
        this.aids += amount
    }

    companion object {
        var fieldSize: Int = 0
        val dx = arrayOf(1, 1, 1, 0, 0, -1, -1, -1)
        val dy = arrayOf(-1, 0, 1, 1, -1, -1, 0, 1)
        val spreadTargets = LinkedList<TreeNode>()

        fun isRange(x: Int, y: Int) = x >= 0 && y >= 0 && x < fieldSize && y < fieldSize
    }
}

data class TreeNode(
    val x: Int,
    val y: Int,
)