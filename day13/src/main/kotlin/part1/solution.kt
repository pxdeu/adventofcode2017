package part1

import common.readInput

val input = """0: 3
1: 2
4: 4
6: 4"""

/*

Scanner is at 0 every 2 * (depth - 1) steps

d = 3
0 0 <--
1 1
2 2
3 1
4 0 <--
5 1
6 2
7 1
8 0 <--

d = 4
0 0 <--
1 1
2 2
3 3
4 2
5 1
6 0 <--
7 1
8 2
9 3
10 2
11 1
12 0 <--

 */

fun main(args: Array<String>) {

    val firewall = mutableMapOf<Int, Int>()
    var lastIndex = 0
    readInput().forEachLine {
        val (index, depth) = it.split(":")
        lastIndex = index.trim().toInt()
        firewall[lastIndex] = depth.trim().toInt()
    }

    var score = 0
    for (position in 0..lastIndex) {
        for (entry in firewall.entries) {
            val depth = entry.value
            if (entry.key == position && (position % (2 * (depth - 1)) == 0)) {
                score += position * depth
            }
        }
    }
    println(score)
}

