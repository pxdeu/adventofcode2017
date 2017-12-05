import common.readInput

fun main(args: Array<String>) {
    val jumps = readInput().readLines().map { it.toInt() }.toIntArray()
    var index = 0
    var count = 0
    while (index < jumps.size) {
        val old = index
        index += jumps[index]
        if (jumps[old] >= 3) {
            jumps[old]--
        } else {
            jumps[old]++
        }
        count += 1
    }
    println(count)
}