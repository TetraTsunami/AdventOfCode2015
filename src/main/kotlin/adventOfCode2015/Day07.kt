package adventOfCode2015
import util.*

@Suppress("unused")
class Day07(input: String) : Day(input) {
    private val circuits = mutableMapOf<String, Gate>()
    private val relies = mutableListOf<Pair<String, String>>()
    private enum class GateType {SET, NOT, AND, OR, LSHIFT, RSHIFT }
    private data class Gate (val type: GateType, val arg1: Arg?, val arg2: Arg? = null) {
        private var signal: Int? = null
        private var needsArg2: Boolean = false
        init {
            assert(arg1 != null)
            when (type) {
                GateType.AND, GateType.OR, GateType.LSHIFT, GateType.RSHIFT -> {
                    needsArg2 = true
                }
                else -> {}
            }
        }
        fun get(): Int? {
            if (arg1 == null && (!needsArg2 || arg2 == null)) return null
            if (signal != null) return signal
            signal = when (type) {
                GateType.SET -> arg1?.get()
                GateType.NOT -> 65535 - arg1!!.get()!!
                GateType.AND -> arg1?.get()?.and(arg2?.get()!!)
                GateType.OR -> arg1?.get()?.or(arg2?.get()!!)
                GateType.LSHIFT -> arg1?.get()?.shl(arg2?.get()!!)
                GateType.RSHIFT -> arg1?.get()?.shr(arg2?.get()!!)
            }
            return signal
        }

        override fun toString(): String {
            return get().toString()
        }
    }
    private data class Arg(private var value: String, private val board: Map<String, Gate>) {
        fun get(): Int? {
            if (!canGet()) return null
            return when (this.value.toIntOrNull()) {
                null -> board[value]!!.get()
                else -> value.toInt()
            }
        }
        fun canGet(): Boolean {
            return when (this.value.toIntOrNull()) {
                null -> board[value]!!.get() != null
                else -> true
            }
        }
    }
    override fun solve(): Result {
        for (line in lines) {
            val command = line.split(" ")
            val name = command.last()
            var gate: Gate? = null
            when (command.size) {
                3 -> {
                    relies.add(Pair(name, command[0]))
                    gate = Gate(GateType.SET, Arg(command[0], circuits))
                }
                4 -> {
                    relies.add(Pair(name, command[1]))
                    gate = Gate(GateType.NOT, Arg(command[1], circuits))
                }
                5 -> {
                    relies.add(Pair(name, command[0]))
                    relies.add(Pair(name, command[2]))
                    val arg1 = Arg(command[0], circuits)
                    val arg2 = Arg(command[2], circuits)
                    gate = when (command[1]) {
                        "AND" -> Gate(GateType.AND, arg1, arg2)
                        "OR" -> Gate(GateType.OR, arg1, arg2)
                        "LSHIFT" -> Gate(GateType.LSHIFT, arg1, arg2)
                        "RSHIFT" -> Gate(GateType.RSHIFT, arg1, arg2)
                        else -> {
                            println("Error: invalid command ${command[1]}")
                            continue
                        }
                    }
                }
                else -> {
                    println("Error: invalid command length ${command.size}")
                }
            }
            if (gate != null) circuits[name] = gate
        }
        // board populated, now evaluate
        while (true) {
            for (circuit in circuits) {
                if (circuit.value.get() != null) {
                    for (rely in relies) {
                        if (rely.first == circuit.key) {
                            circuits[rely.second]?.get()
                            relies.remove(rely)
                            break
                        }
                    }
                }
            }
            if (relies.size == 0) break
        }
        // ROUND 2!!!!!

        val secretSecond = circuits["a"]?.get()
        circuits.clear()
        relies.clear()
        for (line in lines) {
            val command = line.split(" ")
            val name = command.last()
            var gate: Gate? = null
            when (command.size) {
                3 -> {
                    relies.add(Pair(name, command[0]))
                    gate = Gate(GateType.SET, Arg(command[0], circuits))
                }
                4 -> {
                    relies.add(Pair(name, command[1]))
                    gate = Gate(GateType.NOT, Arg(command[1], circuits))
                }
                5 -> {
                    relies.add(Pair(name, command[0]))
                    relies.add(Pair(name, command[2]))
                    val arg1 = Arg(command[0], circuits)
                    val arg2 = Arg(command[2], circuits)
                    gate = when (command[1]) {
                        "AND" -> Gate(GateType.AND, arg1, arg2)
                        "OR" -> Gate(GateType.OR, arg1, arg2)
                        "LSHIFT" -> Gate(GateType.LSHIFT, arg1, arg2)
                        "RSHIFT" -> Gate(GateType.RSHIFT, arg1, arg2)
                        else -> {
                            println("Error: invalid command ${command[1]}")
                            continue
                        }
                    }
                }
                else -> {
                    println("Error: invalid command length ${command.size}")
                }
            }
            if (gate != null) circuits[name] = gate
        }
        circuits["b"] = Gate(GateType.SET, Arg(secretSecond.toString(), circuits))
        while (true) {
            for (circuit in circuits) {
                if (circuit.value.get() != null) {
                    for (rely in relies) {
                        if (rely.first == circuit.key) {
                            circuits[rely.second]?.get()
                            relies.remove(rely)
                            break
                        }
                    }
                }
            }
            if (relies.size == 0) break
        }
        // board populated, now evaluate
        return Result(secretSecond, circuits["a"]?.get())
    }
}
