package part2

import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {

    val duration = measureTimeMillis {

        var a: Long = 722
        var b: Long = 354

        var count = 0
        repeat(5_000_000) {
            do {
                a = (a * 16807) % 2147483647
            } while (a % 4 != 0L)
            do {
                b = (b * 48271) % 2147483647
            } while (b % 8 != 0L)

            val (first, second) = listOf(a, b).map { it.toString(2).padStart(32, '0').drop(16) }
            if (first == second) {
                count++
            }
        }
        println(count)
    }

    println("Took ${duration}ms")
}