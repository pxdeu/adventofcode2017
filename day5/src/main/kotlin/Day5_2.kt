import common.readInput

fun main(args: Array<String>) {
    val jumps = readInput().readLines().map { it.toInt() }.toIntArray()
    var index = 0
    var count = 0
    while (index < jumps.size) {
        index += if (jumps[index] >= 3) {
            jumps[index]--
        } else {
            jumps[index]++
        }
        count += 1
    }
    println(count)
}