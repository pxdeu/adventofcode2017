package part1

fun main(args: Array<String>) {

    var a: Long = 722
    var b: Long = 354

    var count = 0
    repeat(40_000_000) {
        a = (a * 16807) % 2147483647
        b = (b * 48271) % 2147483647

        val (first, second) = listOf(a, b).map { it.toString(2).padStart(32, '0').drop(16) }
        if (first == second) {
            count++
        }
    }
    println(count)
}