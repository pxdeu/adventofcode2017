package part1

import common.readInput
import common.replace

fun main(args: Array<String>) {

    var programs = "abcdefghijklmnop"
    val input = readInput().readText().trim().split(",").map { it.trim() }

    input.forEach {
        programs = when (it[0]) {
            's' -> spin(programs, it.drop(1).toInt())
            'x' -> {
                val (i1, i2) = it.drop(1).split("/")
                exchange(programs, i1.toInt(), i2.toInt())
            }
            'p' -> partner(programs, it[1], it[3])
            else -> throw RuntimeException("Unknown command '${it[0]}")
        }
    }
    println(programs)
}

fun spin(programs: String, times: Int): String {
    return programs.takeLast(times) + programs.dropLast(times)
}

fun exchange(programs: String, x: Int, y: Int): String {
    var out = programs
    out = out.replace(x, programs[y])
    return out.replace(y, programs[x])
}

fun partner(programs: String, x: Char, y: Char): String = exchange(programs, programs.indexOf(x), programs.indexOf(y))