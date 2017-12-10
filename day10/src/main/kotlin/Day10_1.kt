import common.readInput

fun main(args: Array<String>) {

    var numbers = (0..255).toList()

    val lengths = readInput().readText().trim().split(",").map { it.toInt() } //listOf(3, 4, 1, 5)

    var position = 0
    var skip = 0
    for (length in lengths) {
        val subList = reversedSubList(numbers, position, length)
        numbers = replaceSubList(numbers, position, subList)
        position += length + skip
        skip += 1
    }
    println(numbers[0] * numbers[1])
}

fun <T> replaceSubList(numbers: List<T>, position: Int, subList: List<T>): List<T> {
    val out = numbers.toMutableList()

    for (i in 0 until subList.size) {
        out[(position + i) % out.size] = subList[i]
    }

    return out
}

fun <T> reversedSubList(source: List<T>, index: Int, length: Int): List<T> =
        (0 until length).map { source[(it + index) % source.size] }.toList().reversed()