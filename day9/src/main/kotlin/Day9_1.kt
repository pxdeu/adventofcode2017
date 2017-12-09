import common.readInput
import java.io.Reader

fun main(args: Array<String>) {

    var score = 0
    var curly = 0
    var garbage = false
    val reader = readInput()
    loop@ while (true) {
        when (reader.readChar()) {
            '<' -> garbage = true
            '>' -> garbage = false
            '!' -> reader.read()
            '{' -> if (!garbage) curly++
            '}' -> if (!garbage) score += curly--
            '\n' -> break@loop
        }
    }
    println(score)
}

fun Reader.readChar(): Char {
    return this.read().toChar()
}
