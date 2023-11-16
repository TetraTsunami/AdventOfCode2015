package adventOfCode2015
import util.*
import org.apache.commons.collections4.iterators.PermutationIterator

@Suppress("unused")
class Day13(input: String) : Day(input) {
    override fun solve() {
        val moodMap = mutableMapOf<Pair<String, String>, Int>()
        for (line in lines) {
            val words = line.split(" ")
            val p1 = words[0]
            val p2 = words[10].dropLast(1)
            val key = Pair(p1, p2)

            val amt = words[3].toInt()
            when (words[2] == "gain") {
                true -> moodMap[key] = amt
                false -> moodMap[key] = -amt
            }
        }

        // Part 1
        val people1 = moodMap.keys.map { it.first }.toSet()
        val permutations1 = PermutationIterator(people1)

        var max1 = 0
        for (perm in permutations1) {
            var total = 0
            for (i in 0..<perm.size) {
                val person1 = perm[i]
                val person2 = perm[(i + 1) % perm.size]
                total += moodMap[Pair(person1, person2)]!!
                total += moodMap[Pair(person2, person1)]!!
            }
            if (total > max1) max1 = total
        }
        a(max1)

        // Part 2
        val people2 = people1.plus("Me")
        val permutations2 = PermutationIterator(people2)
        var max2 = 0
        for (perm in permutations2) {
            var total = 0
            for (i in 0..<perm.size) {
                if (perm[i] == "Me" || perm[(i + 1) % perm.size] == "Me") continue
                val p1 = perm[i]
                val p2 = perm[(i + 1) % perm.size]
                total += moodMap[Pair(p1, p2)]!!
                total += moodMap[Pair(p2, p1)]!!
            }
            if (total > max2) max2 = total
        }
        a(max2)
    }
}
