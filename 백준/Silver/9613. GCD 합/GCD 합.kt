import java.math.BigInteger

val br = System.`in`.bufferedReader()

fun main() {

   val tc = br.readLine().toInt()

    repeat(tc){

        val input = br.readLine().split(" ").map { it.toInt() }
        val n = input[0]
        val arr = input.drop(1)

        var result = 0L
        for(i in 0..<n){
            for(j in i+1 ..<n){
                val from = BigInteger.valueOf(arr[i].toLong())
                val to = BigInteger.valueOf(arr[j].toLong())

                val gcd = from.gcd(to)
                result += gcd.toLong()
            }
        }

        println(result)
    }
}

/**
 * 3
 * 4 10 20 30 40
 * 3 7 5 12
 * 3 125 15 25
 */
