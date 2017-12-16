package part2

import common.readInput
import common.replace


fun main(args: Array<String>) {

    var programs = "abcdefghijklmnop"
    val input = readInput().readText().trim().split(",").map { it.trim() }

    val sequence = linkedSetOf<String>()
    while (sequence.add(programs)) {
        programs = dance(programs, input)
    }
    println(programs)
    println(sequence.size)

    println(sequence.toList()[1_000_000_000 % sequence.size])
}

fun dance(programs: String, input: List<String>): String {
    var out = programs
    input.forEach {
        out = when (it[0]) {
            's' -> spin(out, it.drop(1).toInt())
            'x' -> {
                val (i1, i2) = it.drop(1).split("/")
                exchange(out, i1.toInt(), i2.toInt())
            }
            'p' -> partner(out, it[1], it[3])
            else -> throw RuntimeException("Unknown command '${it[0]}")
        }
    }
    return out
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