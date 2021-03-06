import common.append
import common.readInput

fun main(args: Array<String>) {

    var numbers = (0..255).toList()

    val lengths = readInput().readText().trim().map { it.toInt() }.append(listOf(17, 31, 73, 47, 23))

    var position = 0
    var skip = 0
    repeat(64) {
        for (length in lengths) {
            val subList = reversedSubList(numbers, position, length)
            numbers = replaceSubList(numbers, position, subList)
            position += length + skip
            skip += 1
        }
    }
    println(dense(numbers).joinToString("") { it.toString(16) })
}

fun dense(source: List<Int>, blockSize: Int = 16): List<Int> {
    require(source.size % blockSize == 0) { "Illegal list size '${source.size}' and block size '$blockSize'" }

    return List(source.size / blockSize) {
        val index = it * blockSize
        source.subList(index + 1, index + blockSize).fold(source[index], { acc, i -> acc.xor(i) })
    }
}
