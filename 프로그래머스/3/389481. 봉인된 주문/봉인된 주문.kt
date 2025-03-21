class Solution {
    fun solution(n: Long, bans: Array<String>): String {
        var cur = n
        val banNums = bans.map { it.toBase26() }.sorted()
        for (banNum in banNums) {
            if(cur >= banNum){
                cur++
            }
        }
        return cur.ofBase26()
    }

    private fun Long.ofBase26():String{
        val builder = StringBuilder()
        var num = this
        while (num-- >0){
            builder.append('a'+(num % 26).toInt())
            num /= 26
        }
        return builder.toString().reversed()
    }

    private fun String.toBase26():Long{
        var acc = 0L
        this.toCharArray().forEach {
            acc *= 26
            acc += it-'a'+1
        }
        return acc
    }
}