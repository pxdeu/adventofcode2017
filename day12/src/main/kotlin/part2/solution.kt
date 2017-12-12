package part2

import common.readInput

fun main(args: Array<String>) {

    val values = mutableMapOf<Int, Set<Int>>()
    var index = 0
    readInput().forEachLine {
        val data = it.split("<->")
        if (data.size != 2) {
            throw RuntimeException("Invalid input '$it'")
        }
        values[index++] = data[1].split(",").map { it.trim().toInt() }.toSet()
    }

    var groupCount = 0
    val indices = (0 until index).toMutableList()
    while (indices.isNotEmpty()) {
        val collected = mutableSetOf<Int>()
        collect(indices[0], values, collected)
        indices.removeAll(collected)
        groupCount += 1
    }

    println(groupCount)
}

fun collect(index: Int, values: Map<Int, Set<Int>>, collected: MutableSet<Int>) {
    collected.add(index)
    val receivers = values[index] ?: throw RuntimeException("Unknown receiver '$index'")
    receivers
            .filterNot { collected.contains(it) }
            .forEach { collect(it, values, collected) }
}