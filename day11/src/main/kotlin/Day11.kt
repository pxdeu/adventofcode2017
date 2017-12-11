import common.readInput
import kotlin.math.abs
import kotlin.math.max

// super awesome tutorial on hexgrids: https://www.redblobgames.com/grids/hexagons/

fun main(args: Array<String>) {
    val input = readInput().readText().trim().split(",")

    val origin = Cube(0, 0, 0)
    val directions = mapOf("n" to Cube(0, 1, -1),
            "s" to Cube(0, -1, 1),
            "ne" to Cube(1, 0, -1),
            "sw" to Cube(-1, 0, 1),
            "se" to Cube(1, -1, 0),
            "nw" to Cube(-1, 1, 0))

    var maximum = Int.MIN_VALUE
    var position = origin.copy()
    var distance = 0
    for (s in input) {
        position += directions[s] ?: throw RuntimeException("unknown direction '$s'")
        distance = origin.distance(position)
        maximum = max(maximum, distance)
    }
    println(distance)
    println(maximum)
}

data class Cube(val x: Int, val y: Int, val z: Int) {

    fun distance(other: Cube): Int = (abs(x - other.x) + abs(y - other.y) + abs(z - other.z)) / 2

    operator fun plus(other: Cube): Cube = Cube(x + other.x, y + other.y, z + other.z)
}