import common.readInput

fun main(args: Array<String>) {

    var score = 0
    var curly = 0
    var garbage = false
    var garbageCount = 0
    val reader = readInput()
    loop@ while (true) {
        when (reader.readChar()) {
            '<' -> if (garbage) garbageCount++ else garbage = true
            '>' -> garbage = false
            '!' -> reader.read()
            '{' -> if (!garbage) curly++ else garbageCount++
            '}' -> if (!garbage) score += curly-- else garbageCount++
            ',' -> if (garbage) garbageCount++
            '\n' -> break@loop
            else -> garbageCount++
        }
    }
    println(score)
    println(garbageCount)
}
