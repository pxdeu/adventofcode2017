package part1

fun main(args: Array<String>) {

    val step = 370
    val spin = mutableListOf(0)
    var position = 0

    for (i in 0..2017) {
        position = (position + step) % spin.size
        spin.add(++position, i + 1)
    }

    println(spin[spin.indexOf(2017)+1])
}