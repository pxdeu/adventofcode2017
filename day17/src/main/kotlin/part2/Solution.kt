package part2

fun main(args: Array<String>) {

    val step = 370
    var position = 0
    var next = Int.MIN_VALUE

    for (i in 1..50_000_001) {
        position = (position + step) % i
        if (position++ == 0) {
            next = i
        }
    }

    println(next)
}