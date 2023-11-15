package adventOfCode2015
import util.*
import java.util.Scanner

@Suppress("unused")
class Day08(input: String) : Day(input) {
    override fun solve(): Result {
        var totalCode = 0
        var totalPrint = 0
        var totalMeta = 0
        for (line in lines) {
            var lineCode = 2 //quotes on each side
            var linePrint = 0
            var lineMeta = 4 //new escaped quotes on each side
            val scanner = Scanner(line.substring(1, line.length - 1))
            scanner.useDelimiter("")
            while (scanner.hasNext()) {
                if (scanner.next().equals("\\")) {
                    lineCode++
                    lineMeta++
                    if (scanner.next().equals("x")) {
                        lineCode++ // \x27 = 1 print
                        scanner.next()
                        lineCode++
                        scanner.next()
                    } else {
                        lineMeta++
                    }
                    lineCode++
                    linePrint++
                } else {
                    lineCode++
                    linePrint++
                }
            }
            totalCode += lineCode
            totalPrint += linePrint
            totalMeta += lineMeta + lineCode
        }
        return Result(totalCode - totalPrint, totalMeta - totalCode)
    }
}
