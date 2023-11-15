package adventOfCode2015
import util.*
import java.util.Scanner

@Suppress("unused")
class Day10(input: String) : Day(input) {
    override fun solve(): Result {
        var string = input.dropLast(2) // drop /r/n
        // for (i in 0..<40) {
        //     println(string)
        //     val list = string.toList()
        //     // count number of times each element occurs
        //     val count = list.groupingBy { it }.eachCount()
        //     string = ""
        //     for (c in count) {
        //         string += "${c.value}${c.key}"
        //     }
        // }
        for (i in 0..<50) {
            // println(string)
            val newString = StringBuilder("")
            val scanner = Scanner(string)
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
            string = newString.toString()
        }
        return Result(string.length)
    }
}
