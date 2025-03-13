import java.util.*

fun main() {

    val n = readln().toInt()
//    val size = (2.0).pow(5.0).toInt()
    var bits =  BooleanArray(21)

    val result = StringBuilder()
    repeat(n){
        val input = readln().split(" ")
        val op = input[0]
        val num = input.getOrElse(1){"0"}.toInt()

        if(op == "add"){
            bits[num] = true
        }else if(op == "remove"){
            bits[num] = false
        }else if(op == "check"){
            when {
                bits[num] -> result.append("1\n")
                else -> result.append("0\n")
            }
        }else if(op == "toggle"){
            when{
                bits[num] -> bits[num] = false
                else -> bits[num] = true
            }
        }else if(op =="all"){
            Arrays.fill(bits,true)
        }else if(op == "empty"){
            Arrays.fill(bits, false)
        }
    }

    println(result)
}
