package adventOfCode2015
import util.*

@Suppress("unused")
class Day06(input: String) : Day(input) {
    var lights = Array<Array<Boolean>>(1000) { Array<Boolean>(1000) { false } }
    var lightsAna = Array(1000) { Array(1000) { 0 } }

    override fun solve(): Result {
        for (line in lines) {
            val instructions = line.split(' ')
            when (instructions[0]) {
                "turn" -> {
                    val arg1 = instructions[2].split(',')
                    val arg2 = instructions[4].split(',')
                    when (instructions[1]) {
                        "on" -> {
                            changeLights(arg1, arg2, true)
                        }
                        "off" -> {
                            changeLights(arg1, arg2, false)
                        }
                    }
                }
                "toggle" -> {
                    val arg1 = instructions[1].split(',')
                    val arg2 = instructions[3].split(',')
                    toggleLights(arg1, arg2)
                }
            }
        }
        for (line in lines) {
            val instructions = line.split(' ')
            when (instructions[0]) {
                "turn" -> {
                    val arg1 = instructions[2].split(',')
                    val arg2 = instructions[4].split(',')
                    when (instructions[1]) {
                        "on" -> {
                            changeLight2(arg1, arg2, 1)
                        }
                        "off" -> {
                            changeLight2(arg1, arg2, -1)
                        }
                    }
                }
                "toggle" -> {
                    val arg1 = instructions[1].split(',')
                    val arg2 = instructions[3].split(',')
                    changeLight2(arg1, arg2, 2)
                }
            }
        }
        return Result(countLights(true), countLights2())
    }

    fun changeLights(coord1: List<String>, coord2: List<String>, state: Boolean) {
        for (x in coord1[0].toInt()..coord2[0].toInt()) {
            for (y in coord1[1].toInt()..coord2[1].toInt()) {
                lights[x][y] = state;
            }
        }
    }

    fun changeLight2(coord1: List<String>, coord2: List<String>, amount: Int) {
        for (x in coord1[0].toInt()..coord2[0].toInt()) {
            for (y in coord1[1].toInt()..coord2[1].toInt()) {
                lightsAna[x][y] = Math.max(lightsAna[x][y] + amount, 0)
            }
        }
    }

    fun toggleLights(coord1: List<String>, coord2: List<String>) {
        for (x in coord1[0].toInt()..coord2[0].toInt()) {
            for (y in coord1[1].toInt()..coord2[1].toInt()) {
                lights[x][y] = !lights[x][y];
            }
        }
    }

    fun countLights(state: Boolean): Int {
        var total = 0
        for (x in 0..999) {
            for (y in 0..999) {
                total += if (lights[x][y] == state) 1 else 0
            }
        }
        return total
    }
    fun countLights2(): Int {
        var total = 0
        for (x in 0..999) {
            for (y in 0..999) {
                total += lightsAna[x][y]
            }
        }
        return total
    }
}
