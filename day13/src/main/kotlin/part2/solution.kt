package part2

import common.readInput

val input = """0: 3
1: 2
4: 4
6: 4"""

fun main(args: Array<String>) {

    val firewall = mutableMapOf<Int, Int>()
    var lastIndex = 0
    readInput().forEachLine {
        val (index, depth) = it.split(":")
        lastIndex = index.trim().toInt()
        firewall[lastIndex] = depth.trim().toInt()
    }

    var delay = 0
    var caught = true
    while (caught) {
        caught = false
        label@for (position in 0..lastIndex) {
            for (entry in firewall.entries) {
                val depth = entry.value
                if (entry.key == position && ((position + delay) % (2 * (depth - 1)) == 0)) {
                    caught = true
                    break@label
                }
            }
        }
        delay += 1
    }
    println(delay - 1)
}
