package part1

import common.readInput
import kotlin.math.abs

fun main(args: Array<String>) {

    val particles = mutableListOf<Particle>()
    val pattern = Regex("p=<(-?\\d+,-?\\d+,-?\\d+)>, v=<(-?\\d+,-?\\d+,-?\\d+)>, a=<(-?\\d+,-?\\d+,-?\\d+)>")
    readInput().readLines().forEach {
        val result = pattern.matchEntire(it) ?: throw RuntimeException("Malformed line: $it")
        val (position, velocity, acceleration) = result.destructured
        particles.add(Particle(position.toTriple(), velocity.toTriple(), acceleration.toTriple()))
    }

    var min = particles[0]
    var old = particles[1]
    while (min != old) {
        repeat(1_000) {
            particles.forEach { it.step() }
            old = min
            min = particles.minBy { it.distance } ?: throw RuntimeException("No minimum found")
        }
    }
    println(particles.indexOf(min))
}

class Particle(var position: Triple<Long, Long, Long>, var velocity: Triple<Long, Long, Long>, val acceleration: Triple<Long, Long, Long>) {

    val distance: Long
        get() = abs(position.first) + abs(position.second) + abs(position.third)

    fun step() {
        velocity += acceleration
        position += velocity
    }
}

private operator fun Triple<Long, Long, Long>.plus(other: Triple<Long, Long, Long>): Triple<Long, Long, Long> =
        Triple(first + other.first, second + other.second, third + other.third)

fun String.toTriple(): Triple<Long, Long, Long> {
    val (x, y, z) = this.split(",").map { it.trim().toLong() }
    return Triple(x, y, z)
}