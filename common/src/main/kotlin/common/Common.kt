package common

import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.io.Reader

fun readInput(resourceName: String = "input.txt"): Reader {
    val stream = Thread.currentThread().contextClassLoader.getResourceAsStream(resourceName)
    if (stream != null) {
        return BufferedReader(InputStreamReader(stream))
    }

    throw FileNotFoundException("$resourceName does not exist")
}

fun <T> List<T>.append(list: List<T>): List<T> = this.toMutableList() + list

fun <T> List<T>.append(vararg data: T): List<T> = this.toMutableList() + data

fun <T> replaceSubList(list: List<T>, position: Int, subList: List<T>): List<T> {
    val out = list.toMutableList()

    for (i in 0 until subList.size) {
        out[(position + i) % out.size] = subList[i]
    }

    return out
}

fun String.replace(index: Int, value: Char): String =
        this.take(index) + value + this.drop(index + 1)

fun <T> reversedSubList(source: List<T>, index: Int, length: Int): List<T> =
        (0 until length).map { source[(it + index) % source.size] }.toList().reversed()