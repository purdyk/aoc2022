fun main() {

    fun IntRange.contains(other: IntRange): Boolean {
        return other.first >= this.first && other.last <= this.last
    }

    fun IntRange.touches(other: IntRange): Boolean {
        return this.first <= other.first && this.last >= other.first
    }

    fun part1(input: List<String>): String {
        val rangePairs = input.map { it.split(",").map { it.split("-").let { it[0].toInt()..it[1].toInt() } } }

        val contained = rangePairs.filter {
            it[0].contains(it[1]) || it[1].contains(it[0])
        }

        return contained.size.toString()
    }

    fun part2(input: List<String>): String {
        val rangePairs = input.map { it.split(",").map { it.split("-").let { it[0].toInt()..it[1].toInt() } } }

        val contained = rangePairs.filter {
            it[0].touches(it[1]) || it[1].touches(it[0])
        }

        return contained.size.toString()
    }

    val day = "04"

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test").split("\n")

    check(part1(testInput) == "2")
    check(part2(testInput) == "4")

    println("Test: ")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day${day}").split("\n")

    println("\nProblem: ")
    println(part1(input))
    println(part2(input))
}
