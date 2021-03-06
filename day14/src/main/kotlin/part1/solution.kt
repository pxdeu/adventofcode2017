package part1

import common.append
import common.replaceSubList
import common.reversedSubList

fun main(args: Array<String>) {

    val input = "xlqgujun"

    val used = (0 until 128)
            .map { knotHash("$input-$it") }
            .sumBy {
                it.joinToString("") { it.toString(2).padStart(8, '0') }
                        .filter { it == '1' }.length
            }
    println(used)
}

// Functions from day 10

fun knotHash(value: String): List<Int> {
    var numbers = (0..255).toList()

    val lengths = value.trim().map { it.toInt() }.append(17, 31, 73, 47, 23)

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
    return dense(numbers)
}

fun dense(source: List<Int>, blockSize: Int = 16): List<Int> {
    require(source.size % blockSize == 0) { "Illegal list size '${source.size}' and block size '$blockSize'" }

    return List(source.size / blockSize) {
        val index = it * blockSize
        source.subList(index + 1, index + blockSize).fold(source[index], { acc, i -> acc.xor(i) })
    }
}