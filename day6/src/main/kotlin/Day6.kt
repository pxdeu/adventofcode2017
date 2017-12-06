import common.readInput

fun main(args: Array<String>) {
    val banks = readInput().readText().trim().split("\t").map { it.toInt() }.toMutableList() //mutableListOf(0, 2, 7, 0)
    val seen = mutableSetOf<List<Int>>()

    while (seen.add(banks.toList())) {
        var index = banks.indexOf(banks.max())
        var content = banks.set(index, 0)

        while (content > 0) {
            val offset = ++index % banks.size
            banks[offset] += 1
            content--
        }

    }
    println(seen.size)
    println(seen.size - seen.indexOf(banks))
}
