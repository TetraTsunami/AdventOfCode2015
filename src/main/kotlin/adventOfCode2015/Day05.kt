package adventOfCode2015
import util.*

@Suppress("unused")
class Day05(input: String) : Day(input) {
    override fun solve() {
        var total = 0
        for (line in lines) {
            if (line.matches(Regex(".*(ab|cd|pq|xy).*"))) {
                continue
            }
            if (line.matches(Regex(".*[aeiou].*[aeiou].*[aeiou].*"))) {
                if (line.matches(Regex(".*(.)\\1.*"))) {
                    total++
                }
            }
        }
        a(total)
        var total2 = 0
        for (line in lines) {
            if (line.matches(Regex(".*(..).*\\1.*"))) {
                if (line.matches(Regex(".*(.).\\1.*"))) {
                    total2++
                }
            }
        }
        a(total2)
    }
}
