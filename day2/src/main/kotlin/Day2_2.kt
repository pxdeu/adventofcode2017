import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val stream = Thread.currentThread().contextClassLoader.getResourceAsStream("input.txt")!!
    val reader = BufferedReader(InputStreamReader(stream))
    var sum = 0
    reader.forEachLine {
        val data = it.split("\t").map { it.toInt() }
        for (datum in data) {
            val divisor = data.toMutableSet().filter { it != datum }.find { datum % it == 0 }
            if (divisor != null) {
                sum += datum / divisor
            }
        }
    }
    println(sum)
}