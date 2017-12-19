import common.readInput

val input = """     |
     |  +--+
     A  |  C
 F---|----E|--+
     |  |  |  D
     +B-+  +--+
"""
val lines = readInput().readLines() //input.reader().readLines()

fun main(args: Array<String>) {

    var letters = ""

    var position = Pair(lines[0].indexOf("|"), 0)
    var direction = Pair(0, 1)
    var steps = 0

    while (validPosition(position)) {

        val current = at(position)
        if (current == '+') {
            direction = nextDirection(position, direction)
        } else if (current in 'A'..'Z') {
            letters += current
        }

        position += direction
        steps += 1
    }
    println(letters)
    println(steps)
}

operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> = Pair(first + other.first, second + other.second)
fun Pair<Int, Int>.reverse(): Pair<Int, Int> = Pair(first * -1, second * -1)

fun at(position: Pair<Int, Int>): Char = lines[position.second][position.first]

fun validPosition(position: Pair<Int, Int>): Boolean =
        (position.second in 0 until lines.size
                && position.first in 0 until lines[position.second].length
                && at(position) != ' ')

fun nextDirection(position: Pair<Int, Int>, direction: Pair<Int, Int>): Pair<Int, Int> =
        listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
                .filter { it != direction && it != direction.reverse() }
                .find { validPosition(position + it) } ?:
                throw RuntimeException("Found no direction to move at $position")
