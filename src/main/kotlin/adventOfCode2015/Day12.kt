package adventOfCode2015
import util.*

@Suppress("unused")
class Day12(input: String) : Day(input) {
    override fun solve() {
        val numbers = Regex("-?[0-9]+").findAll(input)
        var totalPt1 = 0
        for (number in numbers) {
            totalPt1 += number.value.toInt()
        }
        a(totalPt1)

        val builder = StringBuilder(input)
        val reds = Regex("\"red\"").findAll(input)
        for (match in reds) {
            val left = findLeftBrace(input, match.range.first)
            if (left == -1) continue // we're in a list. ignore!
            val right = findRightBrace(input, match.range.last)
            builder.replace(left, right, " ".repeat(right - left))
        }
        input = builder.toString()
        val numbers2 = Regex("-?[0-9]+").findAll(input)
        var totalPt2 = 0
        for (number in numbers2) {
            totalPt2 += number.value.toInt()
        }
        a(totalPt2)
    }

    fun findLeftBrace(inp: String, start: Int): Int {
        // find index of nearest { on left
        var depth = 0
        for (i in start downTo 0) {
            val char = inp[i]
            if (char == '}' || char == ']') depth++
            else if (char == '[' && depth == 0) return -1 // we're in a list. ignore!
            else if (char == '{' && depth == 0) return i
            else if (char == '{' || char == '[') depth--
        }
        return -1
    }

    fun findRightBrace(inp: String, start: Int): Int {
        // find index of matching } on right, ignoring all { and } in between
        var depth = 0
        for (i in start..inp.length) {
            val char = inp[i]
            if (char == '{' || char == '[') depth++
            else if (char == ']' && depth == 0) return -1
            else if (char == '}' && depth == 0) return i
            else if (char == '}' || char == ']') depth--
        }
        return -1
    }
}
