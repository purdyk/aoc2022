fun main() {
    fun part1(input: List<String>): Int {
        val groups = input.fold(mutableListOf(mutableListOf<Int>())) { acc, it ->
            if (it.isEmpty()) {
                acc.add(mutableListOf())
            } else {
                acc.last().add(it.toInt())
            }
            acc
        }

        val totaled = groups.map { it.sum() to it }

        val topIdx = totaled.maxBy { it.first }.let { max -> totaled.indexOfFirst { it.first == max.first } }

        return totaled[topIdx].first
    }

    fun part2(input: List<String>): Int {
        val groups = input.fold(mutableListOf(mutableListOf<Int>())) { acc, it ->
            if (it.isEmpty()) {
                acc.add(mutableListOf())
            } else {
                acc.last().add(it.toInt())
            }
            acc
        }

        val totaled = groups.map { it.sum() to it }

        return totaled.sortedByDescending { it.first }.take(3).sumOf { it.first }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test").split("\n")

    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    println("Test: ")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day01").split("\n")

    println("\nProblem: ")
    println(part1(input))
    println(part2(input))
}
