package adventOfCode2015
import util.*

@Suppress("unused")
class Day14(input: String) : Day(input) {
    override fun solve() {
        val howLong = 2503
        data class Reindeer(val name: String, val speed: Int, val flyTime: Int, val restTime: Int) {
            var points = 0
            fun distAfter(seconds: Int): Int {
                var i = 0
                var dist = 0
                var sinceSwitch = 0
                var isFlying = true
                while (i < seconds) {
                    i++
                    sinceSwitch++
                    if (isFlying) dist += speed
                    if (isFlying && sinceSwitch >= flyTime) {
                        isFlying = false
                        sinceSwitch = 0
                    }
                    if (!isFlying && sinceSwitch >= restTime) {
                        isFlying = true
                        sinceSwitch = 0
                    }
                }
                return dist
            }
        }
        val reindeers = mutableListOf<Reindeer>()
        for (line in lines) {
            val words = line.split(" ")
            reindeers.add(Reindeer(words[0], words[3].toInt(), words[6].toInt(), words[13].toInt()))
        }
        a(reindeers.maxOfOrNull { it.distAfter(2503) })

        var i = 0
        while (i < howLong) {
            i++
            reindeers.maxBy { it.distAfter(i) }.points++
        }
        a(reindeers.maxOfOrNull { it.points })
    }
}
