import common.readInput

fun main(args: Array<String>) {
    val input = readInput().readText().trim()

    var sum = 0
    for (i in 0 until input.length) {
        val current = input[i].toString().toInt()
        val second = next(input, i)
        if (current == second) {
            sum += current
        }
    }
    println(sum)
}

fun next(input: String, index: Int): Int {

    val step = input.length / 2

    return input[(index + step) % input.length].toString().toInt()
}