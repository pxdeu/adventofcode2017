package part2

import common.append
import common.replace
import common.replaceSubList
import common.reversedSubList

fun main(args: Array<String>) {

    val input = "xlqgujun"

    val grid = (0 until 128)
            .map {
                knotHash("$input-$it").joinToString("") { it.toString(2).padStart(8, '0') }
            }.toMutableList()

    var count = 0
    for (y in 0 until 128) {
        for (x in 0 until 128) {
            if (grid[y][x] == '1') {
                walkRegion(grid, y, x)
                count += 1
            }
        }
        println(grid[y])
    }
    println(count)
}

fun walkRegion(grid: MutableList<String>, y: Int, x: Int) {
    if (x in 0..127 && y in 0..127 && grid[y][x] == '1') {
        grid[y] = grid[y].replace(x, 'x')
        walkRegion(grid, y, x - 1)
        walkRegion(grid, y, x + 1)
        walkRegion(grid, y - 1, x)
        walkRegion(grid, y + 1, x)
    }
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