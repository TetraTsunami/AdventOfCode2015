package adventOfCode2015
import util.*

@Suppress("unused")
class Day02(input: String) : Day(input) {
    val example = listOf("2x3x4")
    override fun solve() {
        var totalPaper = 0
        var totalRibbon = 0
        for (line in lines) {
            val dims = line.split("x").map {it.toInt()}
            val s1 = dims[0] * dims[1]
            val s2 = dims[1] * dims[2]
            val s3 = dims[0] * dims[2]
            val all = listOf(s1,s2,s3)
            totalPaper += 2 * all.sum()
            totalPaper += all.min()
            totalRibbon += when(all.min()) {
                s1 -> 2 * (dims[0] + dims[1])
                s2 -> 2 * (dims[1] + dims[2])
                s3 -> 2 * (dims[2] + dims[0])
                else -> 0
            }
            totalRibbon += dims.reduce {acc, i -> acc * i}
        }
        a(totalPaper, totalRibbon)
    }
}
