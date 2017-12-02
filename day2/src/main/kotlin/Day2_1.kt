import common.readInput

fun main(args: Array<String>) {
    var sum = 0
    readInput().forEachLine {
        val data = it.split("\t").map { it.toInt() }
        sum += data.max()!! - data.min()!!
    }
    println(sum)
}