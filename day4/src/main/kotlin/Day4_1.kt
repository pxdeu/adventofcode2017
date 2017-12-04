import common.readInput

fun main(args: Array<String>) {
    var count = 0
    readInput().forEachLine {
        val words = it.split(" ")
        if (words.toSet().size == words.size && words.none { word -> words.filter { it != word }.any { isAnagram(word, it) } }) {
            count += 1
        }
    }
    println(count)
}

fun isAnagram(original: String, copy: String): Boolean = original.toList().sorted() == copy.toList().sorted()