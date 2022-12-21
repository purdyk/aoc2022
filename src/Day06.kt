fun main() {
    fun part1(input: String): String {
        return (input.windowed(4).indexOfFirst {
            it.toSet().size == 4
        } + 4).toString()
    }

    fun part2(input: String): String {
        return (input.windowed(14).indexOfFirst {
            it.toSet().size == 14
        } + 14).toString()
    }

    val day = "06"

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test")

    println("Test: ")
    println(part1(testInput))
    println(part2(testInput))

    check(part1(testInput) == "7")
    check(part2(testInput) == "19")

    val input = readInput("Day${day}")

    println("\nProblem: ")
    println(part1(input))
    println(part2(input))
}
