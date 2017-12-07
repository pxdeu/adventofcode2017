import common.readInput

fun main(args: Array<String>) {

    val regex = "(\\w+)\\s\\((\\d+)\\)".toRegex()

    val nodes = mutableListOf<Node1>()
    readInput().forEachLine {
        val data = it.split("->")
        val match = regex.matchEntire(data[0].trim()) ?: throw RuntimeException("input does not match expected format")
        if (match.groups.size != 3) {
            throw RuntimeException("input did not contain name and weight")
        }
        val (_, name, weight) = match.groups.map { it!!.value }
        val children = if (data.size > 1) {
            data[1].split(",").map { it.trim() }
        } else {
            listOf()
        }
        nodes.add(Node1(name, weight, children))
    }
    val allChildren = nodes.flatMap { it.children }.toSet()
    println(nodes.find { !allChildren.contains(it.name) })
}

data class Node1(val name: String, val weight: String, val children: List<String>)