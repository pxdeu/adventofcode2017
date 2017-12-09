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
