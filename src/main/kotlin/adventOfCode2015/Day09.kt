package adventOfCode2015
import util.*

@Suppress("unused")
class Day09(input: String) : Day(input) {
    override fun solve(): Result {
        val distances = mutableMapOf<List<String>, Int>()
        for (line in lines) {
            val parts = line.split(" ")
            distances[listOf(parts[0], parts[2])] = parts[4].toInt()
        }
        // get all unique places
        val placeNames = mutableSetOf<String>()
        for (location in distances) placeNames.addAll(location.key.toList())
        val fastestRoutes = mutableListOf<Pair<List<String>, Int>>()
        for (place in placeNames) {
            val route = fastestRoute(place, distances)
            fastestRoutes.addAll(route)
        }

        return Result(fastestRoutes.minBy { it.second }, fastestRoutes.maxBy { it.second })
    }

    fun fastestRoute(start: String, distances: Map<List<String>, Int>, distance: Int = 0, past: MutableList<String> = mutableListOf()): List<Pair<List<String>, Int>> {
        val routes = mutableListOf<Pair<List<String>, Int>>()
        val pastRoute = past.toMutableList()
        pastRoute.add(start)
        for (location in distances) {
            if (location.key.contains(start)) {
                // we're going to the other side
                val newDist = location.value + distance
                val otherSide = location.key.first { it != start }
                // remove paths back here
                val newDistances = distances.filter { !it.key.contains(start) }
                routes.addAll(fastestRoute(otherSide, newDistances, newDist, pastRoute))
            }
        }
        if (routes.isEmpty()) {
            return listOf(Pair(pastRoute, distance))
        }
        return routes
    }
}
