package part2

import common.append
import common.readInput
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit

/**
 * Did this one on a train with no internet and couldn't learn about kotlin concurrency in time.
 * I went with a "Java-Workaround" instead.
 * This solution is not thread-safe, for example one program can deadlock when the other terminates normally.
 * It's just thread-safe enough for the puzzle.
 */
fun main(args: Array<String>) {

    val lines = readInput().readLines() //input.reader().readLines()

    val context = Context()
    val p0 = Program(0, lines, context)
    val p1 = Program(1, lines, context)

    p0.start()
    p1.start()

    p0.join()
    p1.join()
}

class Context {

    val queues = listOf(LinkedBlockingQueue<Long>(), LinkedBlockingQueue<Long>())
    private var blocked = mutableListOf(false, false)

    @Synchronized fun setBlock(id: Int, block: Boolean): Boolean {
        blocked[id] = block
        if (blocked.all { it } && queues.all { it.isEmpty() }) {
            return false
        }
        return true
    }
}

class Program(private val id: Int, private val lines: List<String>, private val context: Context) : Thread("Program $id") {

    private val registers = mutableMapOf("p" to id.toLong())
    private var sendCount = 0

    private fun other(): Int = (id + 1) % 2

    override fun run() {
        var pointer = 0L
        var jumped = false
        loop@ while (true) {
            if (pointer < 0 || pointer > lines.size - 1) {
                println("Program '$id' ended: $pointer")
                break
            }
            jumped = false
            val (operation, register, argument) = lines[pointer.toInt()].split(" ").append("#")
            when (operation.trim()) {
                "set" -> registers[register] = resolve(argument)
                "add" -> registers[register] = resolve(register) + resolve(argument)
                "mul" -> registers[register] = resolve(register) * resolve(argument)
                "mod" -> registers[register] = resolve(register) % resolve(argument)
                "snd" -> {
                    context.queues[other()].add(resolve(register))
                    sendCount += 1
                }
                "rcv" -> {
                    var value: Long? = null
                    while (value == null) {
                        value = context.queues[id].poll(5, TimeUnit.SECONDS)
                        if (!context.setBlock(id, true)) {
                            println("Deadlock detected in p '$id'")
                            break@loop
                        }
                    }
                    registers[register] = value
                    context.setBlock(id, false)
                }
                "jgz" -> if (resolve(register) > 0) {
                    pointer += resolve(argument)
                    jumped = true
                }
            }
            if (!jumped) {
                pointer++
            }
        }
        println("Program '$id' sent '$sendCount' messages")
    }

    private fun resolve(argument: String): Long {
        if (argument.matches(Regex("-?\\d+"))) {
            return argument.toLong()
        }

        return registers[argument] ?: 0
    }
}