import common.readInput

val testNodes = """pbga (66)
xhth (57)
ebii (61)
havc (66)
ktlj (57)
fwft (72) -> ktlj, cntj, xhth
qoyq (66)
padx (45) -> pbga, havc, qoyq
tknk (41) -> ugml, padx, fwft
jptl (61)
ugml (68) -> gyxo, ebii, jptl
gyxo (61)
cntj (57)"""

fun main(args: Array<String>) {

    val regex = "(\\w+)\\s\\((\\d+)\\)".toRegex()

    val nodes = mutableListOf<Node2>()
    //testNodes.reader().forEachLine {
    readInput().forEachLine {
        val data = it.split("->")
        val match = regex.matchEntire(data[0].trim()) ?: throw RuntimeException("input '$data[0]' does not match expected format")
        if (match.groups.size != 3) {
            throw RuntimeException("input did not contain name and weight")
        }
        val (_, name, weight) = match.groups.map { it!!.value }
        val children = if (data.size > 1) {
            data[1].split(",").map { it.trim() }
        } else {
            listOf()
        }
        nodes.add(Node2(name, weight.toInt(), children))
    }

    val index = nodes.groupBy { it.name }

    val allChildren = nodes.flatMap { it.childrenNames }.toSet()
    val rootNode = nodes.find { !allChildren.contains(it.name) }!!

    connectNodes(rootNode, index)
    walkNodes(rootNode)
}

fun walkNodes(node: Node2, printMismatch: Boolean = true): Int {

    var childSum = 0
    val weights = mutableListOf<Int>()
    for (child in node.children) {
        val childWeight = walkNodes(child, printMismatch)
        weights.add(childWeight)
        childSum += childWeight
    }
    if (printMismatch && weights.toSet().size > 1) {
        println("\n- ${node.name}: ${node.weight} -")
        for (child in node.children) {
            print("${child.name} (${child.weight}): ${walkNodes(child, false)} / ")
        }
        println("\n--")
    }
    // At this point you can read the first(!) output, and calculate the difference by hand...
    // It's not the solution I wanted, but it's the solution I came up with.
    // I have lots of ideas for optimizations, but it's too late in the evening so I'm not going to do it for now.
    
    return childSum + node.weight
}

fun connectNodes(node: Node2, index: Map<String, List<Node2>>) {

    for (childName in node.childrenNames) {
        val child = index[childName]!![0]
        node.children.add(child)
        connectNodes(child, index)
    }
}

data class Node2(val name: String, val weight: Int, val childrenNames: List<String>, val children: MutableList<Node2> = mutableListOf())
