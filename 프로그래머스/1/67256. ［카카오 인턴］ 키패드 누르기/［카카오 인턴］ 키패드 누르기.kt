import kotlin.math.abs

fun main() {

    var numbers = arrayOf(1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5).toIntArray()
    var hand= "right"

    val result = Solution().solution(numbers, hand)
    println(result)
}

class Solution {
    val numMap = HashMap<String,NumberNode>()
    fun solution(numbers: IntArray, hand: String): String {
        var answer = ""

        var handArr = arrayOf(arrayOf("1","2","3"),arrayOf("4","5","6"),arrayOf("7","8","9"),arrayOf("*","0","#"))

        for(y in 0 ..handArr.size-1){
            for(x in 0..handArr[y].size-1){
                numMap.put(handArr[y][x],NumberNode(x,y))
            }
        }

        var left = "*"
        var right = "#"

        val result = StringBuilder()
        for(number in numbers){
            val inputted = getHand(number, left, right, hand)
            if(inputted == "L"){
                left = number.toString()
            }else{
                right = number.toString()
            }
            result.append(inputted)
        }

        return result.toString()
    }

    fun getHand(num: Int, left: String, right: String, hand: String): String {
        return when(num){
            1,4,7 -> "L"
            3,6,9 -> "R"
            else -> getHandWithSameDistance(num, left, right, hand)
        }
    }

    fun getHandWithSameDistance(num: Int, left: String, right: String, hand: String): String{

        val numNode = numMap[num.toString()] !!
        val leftNode = numMap[left] !!
        val rightNode = numMap[right] !!

        val leftDiff = numNode - leftNode
        val rightDiff = numNode - rightNode

        if(leftDiff == rightDiff){
            if(hand == "right"){
                return "R"
            }else{
                return "L"
            }
        }else if(leftDiff < rightDiff){
            return "L"
        }else{
            return "R"
        }
    }
}

data class NumberNode(
    val x: Int,
    val y: Int
){
    operator fun minus(other: NumberNode): Int{
        val xDiff = abs(this.x-other.x)
        val yDiff = abs(this.y-other.y)
        return xDiff + yDiff
    }
}