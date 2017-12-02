import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val stream = Thread.currentThread().contextClassLoader.getResourceAsStream("input.txt")!!
    val reader = BufferedReader(InputStreamReader(stream))
    var sum = 0
    reader.forEachLine {
        val data = it.split("\t").map { it.toInt() }
        sum += data.max()!! - data.min()!!
    }
    println(sum)
}