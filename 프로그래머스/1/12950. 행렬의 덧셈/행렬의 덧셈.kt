class Solution {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        return arr1+arr2
    }
    operator fun Array<IntArray>.plus(arr: Array<IntArray>): Array<IntArray> {
        for(y in indices){
            for(x in this[0].indices) {
                this[y][x] += arr[y][x]
            }
        }
        return this
    }
}
