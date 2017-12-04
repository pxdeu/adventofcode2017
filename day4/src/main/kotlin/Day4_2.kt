import common.readInput

fun main(args: Array<String>) {
    var count = 0
    readInput().forEachLine {
        val split = it.split(" ")
        if (split.toSet().size == split.size) {
            count += 1
        }
    }
    println(count)
}