package part1

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

    val collected = mutableSetOf<Int>()
    collect(0, values, collected)

    println(collected.size)
}

fun collect(index: Int, values: Map<Int, Set<Int>>, collected: MutableSet<Int>) {
    collected.add(index)
    val receivers = values[index] ?: throw RuntimeException("Unknown receiver '$index'")
    for (receiver in receivers) {
        if (!collected.contains(receiver)) {
            collect(receiver, values, collected)
        }
    }
}