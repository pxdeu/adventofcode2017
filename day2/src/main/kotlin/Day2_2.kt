import common.readInput

fun main(args: Array<String>) {
    var sum = 0
    readInput().forEachLine {
        val data = it.split("\t").map { it.toInt() }
        for (datum in data) {
            val divisor = data.toMutableSet().filter { it != datum }.find { datum % it == 0 }
            if (divisor != null) {
                sum += datum / divisor
            }
        }
    }
    println(sum)
}