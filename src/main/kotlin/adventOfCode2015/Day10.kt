package adventOfCode2015
import util.*
import java.util.Scanner

@Suppress("unused")
class Day10(input: String) : Day(input) {
    override fun solve(): Result {
        var string = input
        for (i in 0..<40) {
            string = convolute(string)
        }
        val res1 = string.length
        for (i in 0..<10) {
            string = convolute(string)
        }
        return Result(res1, string.length)
    }

    fun convolute(inp: String): String {
        // println(string)
        val newString = StringBuilder("")
        val scanner = Scanner(inp)
        scanner.useDelimiter("")
        var currentType = scanner.next()
        var j = 1
        while (scanner.hasNext()) {
            when (val next = scanner.next()) {
                currentType -> j++
                else -> {
                    newString.append(j)
                    newString.append(currentType)
                    currentType = next
                    j = 1
                }
            }
        }
        newString.append(j)
        newString.append(currentType)
       return newString.toString()
    }
}
