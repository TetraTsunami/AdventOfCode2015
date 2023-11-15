// Heavily inspired by SizableShrimp's structure :)
package util

abstract class Day(protected var input: String) {
    protected val lines: List<String>

    init {
        lines = parseInput()
    }

    @Suppress("unused")
            /**
             * Run the day's puzzle, parsing input if neccessary and printing the result.
             */
    fun run() {
        println(solve())
    }

    /**
     * Solve the day's puzzle.
     */
    abstract fun solve(): Result

    /**
     * Used in constructor to parse input. Override this if it's helpful to parse differently.
     */
    fun parseInput(): List<String> {
        val lines = input.lines()
        if (lines.last().isEmpty()) return lines.dropLast(1)
        return lines
    }

    /**
     * Represents the solution to part 1 and part 2 of the day's puzzle.
     */
    data class Result(val part1: Any? = null, val part2: Any? = null) {
        override fun toString(): String {
            return "\tPart 1: $part1\n\tPart 2: $part2"
        }
    }

}
