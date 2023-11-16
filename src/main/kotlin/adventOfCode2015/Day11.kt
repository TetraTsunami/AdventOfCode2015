package adventOfCode2015
import util.*
import kotlin.text.StringBuilder

@Suppress("unused")
class Day11(input: String) : Day(input) {
    override fun solve(): Result {
        var string = input
        var res1: String? = null
        while (true) {
            string = increment(string)
            if (meetsRequirements(string) && res1 == null) res1 = string
            else if (meetsRequirements(string)) return Result(res1, string)
        }
    }

    fun increment(pass: String): String {
        val lowercase = util.Constants.lowercase
        val password = StringBuilder(pass)
        for ((i, char) in password.withIndex().reversed()) {
            if (char == 'z') {
                password[i] = 'a'
            } else {
                password[i] = lowercase[lowercase.indexOf(char) + 1]
                break
            }
        }
        return password.toString()
    }

    fun meetsRequirements(pass: String): Boolean {
        val lowercase = util.Constants.lowercase
        if (pass.length != 8) return false
        if (!pass.matches(Regex("[a-z]*"))) return false
        if (pass.matches(Regex(".*[iol].*"))) return false
        if (!pass.matches(Regex(".*(.)\\1.*(.)\\2.*"))) return false
        for (i in 2..<pass.length) {
            val startIndex = lowercase.indexOf(pass[i-2])
            if (startIndex >= 24) continue
            if (pass[i-1] == lowercase[startIndex + 1] &&
                pass[i] == lowercase[startIndex + 2]) return true
        }
        return false
    }
}
