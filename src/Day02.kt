fun main() {
    fun part1(input: List<String>): String {
        return ""
    }

    fun part2(input: List<String>): String {
        return ""
    }

    val day = "02"

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test").split("\n")

    check(part1(testInput) == "")
    check(part2(testInput) == "")

    println("Test: ")

    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day${day}").split("\n")

    println("\nProblem: ")
    println(part1(input))
    println(part2(input))
}
