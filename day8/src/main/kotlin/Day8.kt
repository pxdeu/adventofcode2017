import common.readInput

val input = """b inc 5 if a > 1
a inc 1 if b < 5
c dec -10 if a >= 1
c inc -20 if c == 10"""

fun main(args: Array<String>) {

    val registers = mutableMapOf<String, Int>()
    var absoluteMax = Int.MIN_VALUE
    var currentMax: Int? = absoluteMax
    //input.reader().forEachLine {
    readInput().forEachLine {
        val command = it.split(" ")
        if (command.size != 7) {
            throw RuntimeException("Input '$it' did not match expected format")
        }

        val register = command[0]
        val operation = command[1]
        val amount = command[2].toInt()
        val condition = command[4]
        val operator = command[5]
        val argument = command[6].toInt()

        val conditionValue = registers[condition] ?: 0
        if (when (operator) {
            "!=" -> conditionValue != argument
            "==" -> conditionValue == argument
            "<" -> conditionValue < argument
            ">" -> conditionValue > argument
            ">=" -> conditionValue >= argument
            "<=" -> conditionValue <= argument
            else -> throw RuntimeException("Unknown condition operator '$operator'")
        }) {
            registers[register] = when (operation) {
                "dec" -> (registers[register] ?: 0) - amount
                "inc" -> (registers[register] ?: 0) + amount
                else -> throw RuntimeException("Unknown operation '$operation'")
            }
        }

        currentMax = registers.values.max()
        if (currentMax != null && currentMax!! > absoluteMax) {
            absoluteMax = currentMax!!
        }
    }

    println(currentMax)
    println(absoluteMax)
}
