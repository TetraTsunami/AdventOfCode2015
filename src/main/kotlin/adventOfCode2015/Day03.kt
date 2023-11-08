package adventOfCode2015
import util.*

@Suppress("unused")
class Day03(input: String) : Day(input) {
    override fun solve(): Result {
        var x = 0
        var y = 0
        val pastCoords = mutableSetOf<Pair<Int, Int>>()
        pastCoords.add(Pair(0, 0))
        for (d in input) {
            when (d) {
                '^' -> y++
                '>' -> x++
                'v' -> y--
                '<' -> x--
            }
            pastCoords.add(Pair(x ,y))
        }
        x = 0
        y = 0
        var xr = 0
        var yr = 0
        val pastRoboCoords = mutableSetOf<Pair<Int, Int>>()
        pastRoboCoords.add(Pair(0, 0))
        for ((i, d) in input.withIndex()) {
            if (i % 2 == 0) {
                when (d) {
                    '^' -> y++
                    '>' -> x++
                    'v' -> y--
                    '<' -> x--
                }
                pastRoboCoords.add(Pair(x ,y))
            } else {
                when (d) {
                    '^' -> yr++
                    '>' -> xr++
                    'v' -> yr--
                    '<' -> xr--
                }
                pastRoboCoords.add(Pair(xr, yr))
            }
        }
        return Result(pastCoords.size, pastRoboCoords.size)
    }
}
