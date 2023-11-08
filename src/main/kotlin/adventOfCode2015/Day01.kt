package adventOfCode2015
import util.*

@Suppress("unused")
class Day01(input: String) : Day(input) {
    var floor = 0
    override fun solve(): Result {
        var basement = -1
        for ((i, dir) in input.withIndex()) {
            when (dir) {
                '(' -> floor++
                ')' -> floor--
            }
            if (basement == -1 && floor == -1) basement = i + 1
        }
        return Result(floor, basement)
    }
}
