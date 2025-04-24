import java.math.BigInteger

val br = System.`in`.bufferedReader()

fun main() {
    br.readLine().toInt()
    val from = br.readLine().split(" ")
        .map { it.toBigInteger() }
        .reduce { acc, i -> acc.multiply( i) }

    br.readLine().toInt()
    val to = br.readLine().split(" ")
        .map { it.toBigInteger() }
        .reduce { acc, i -> acc.multiply( i) }
    
    val gcd = from.gcd(to)
    val criteria = BigInteger.valueOf(1000000000L)

    if(gcd >= criteria){
        val str = gcd.toString()
        print(str.substring(str.length-9,str.length))
    }else{
        print(gcd.toString())
    }

}



/**
 * 3
 * 2 3 5
 * 2
 * 4 5
 */