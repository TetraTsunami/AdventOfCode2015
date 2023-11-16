package adventOfCode2015
import util.*
import java.math.BigInteger
import java.security.MessageDigest

@Suppress("unused")
class Day04(input: String) : Day(input) {
    override fun solve() {
        val inp = lines[0]
        var i = 0
        while (true) {
            val out = hash(inp + i.toString())
            if (!part1Solved && out.subSequence(0, 5) == "00000") {
                a(i)
            }
            if (!part2Solved && out.subSequence(0, 6) == "000000") {
                a(i)
            }
            if (part2Solved) {
                return
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
