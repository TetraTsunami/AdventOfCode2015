import java.util.*
import java.io.File

fun main(args: Array<String>) {
    // If there are command line arguments, run the specified days, all days, or the highest implemented day.
    if (args.isNotEmpty()) {
        when (args[0]) {
            "a" -> {
                for (day in 1..25) {
                    runDay(day)
                }
            }
            "r" -> runLatest()
            else -> runDay(args[0].toInt())
        }
        return
    }

    // If there aren't command line arguments, prompt the user for input.
    val scanner = Scanner(System.`in`)
    print("Pick a day, \"a\" for all, or \"r\" for highest implemented: ")
    val input = scanner.nextLine()
    if (input == "a") {
        for (day in 1..25) {
            runDay(day)
        }
    } else if (input == "r" || input == "") {
        runLatest()
    } else if (input.toInt() in 1..25) {
        runDay(input.toInt())
    }
}

/**
 * Run a specific day.
 */
fun runDay(day: Int) {
    try {
        val dayString = day.toString().padStart(2, '0')
        val dayClass = Class.forName("adventOfCode2015.Day$dayString")
        val dayConstructor = dayClass.getConstructor(String::class.java)

        val dayData = File("src/main/resources/Day$dayString.txt").readText()
        val dayInstance = dayConstructor.newInstance(dayData)
        println("Day $day")
        dayInstance.javaClass.getMethod("run").invoke(dayInstance)
    } catch (e: ClassNotFoundException) {
        println("Day $day not implemented yet.")
    }
}

/**
 * Run the highest implemented day.
 */
fun runLatest() {
    // would love to know if there's a better way to do this
    for (day in 2..26) {
        try {
            val dayString = day.toString().padStart(2, '0')
            Class.forName("adventOfCode2015.Day$dayString")
        } catch (e: ClassNotFoundException) {
            runDay(day - 1)
            break
        }
    }
}
