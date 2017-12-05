import common.readInput

fun main(args: Array<String>) {
    val jumps = readInput().readLines().map { it.toInt() }.toIntArray()
    var index = 0
    var count = 0
    while (index < jumps.size) {
        val old = index
        index += jumps[index]
        jumps[old]++
        count += 1
    }
    println(count)
}