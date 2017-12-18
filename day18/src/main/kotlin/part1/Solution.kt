package part1

import common.append
import common.readInput

val input="""set a 1
add a 2
mul a a
mod a 5
snd a
set a 0
rcv a
jgz a -1
set a 1
jgz a -2"""

val registers = mutableMapOf<String, Long>()

fun main(args: Array<String>) {

    var pointer = 0L
    val lines = readInput().readLines() //input.reader().readLines()

    var played = 0L
    var jumped = false

    loop@ while (true) {
        if (pointer < 0 || pointer > lines.size - 1) {
            break
        }
        jumped = false
        val (operation, register, argument) = lines[pointer.toInt()].split(" ").append("#")
        when (operation.trim()) {
            "set" -> registers[register] = resolve(argument)
            "add" -> registers[register] = resolve(register) + resolve(argument)
            "mul" -> registers[register] = resolve(register) * resolve(argument)
            "mod" -> registers[register] = resolve(register) % resolve(argument)
            "snd" -> if (resolve(register) != 0L) played = resolve(register)
            "rcv" -> if (resolve(register) > 0) {
                println(played)
                break@loop
            }
            "jgz" -> if (resolve(register) > 0) {
                pointer += resolve(argument)
                jumped = true
            }
        }
        if (!jumped) {
            pointer++
        }
    }
}

fun resolve(argument: String): Long {
    if (argument.matches(Regex("-?\\d+"))) {
        return argument.toLong()
    }

    return registers[argument] ?: 0
}