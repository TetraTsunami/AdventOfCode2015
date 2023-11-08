package adventOfCode2015
import util.*
import java.math.BigInteger
import java.security.MessageDigest

@Suppress("unused")
class Day04(input: String) : Day(input) {
    override fun solve(): Result {
        val inp = lines[0]
        var i = 0
        var out1 = -1
        var out2 = -1
        while (true) {
            val out = hash(inp + i.toString())
            if (out1 == -1 && out.subSequence(0, 5) == "00000") {
                out1 = i
            }
            if (out2 == -1 && out.subSequence(0, 6) == "000000") {
                out2 = i
            }
            if (out1 != -1 && out2 != -1) {
                return Result(out1, out2)
            }
            i++
        }
    }

    fun hash(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        // digests are long, so we make it into a BigInteger
        // signum because md.digest doesn't return in two's complement
        val bigInt = BigInteger(1, md.digest(input.toByteArray()))
        // %x = int -> hexadecimal
        // % 0 32 x = 32 digits long
        return String.format("%032x", bigInt)
    }
}
