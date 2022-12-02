enum class Throw {
    Rock,
    Paper,
    Scissor
}

val Throw.worth: Int
    get() =
        when (this) {
            Throw.Rock -> 1
            Throw.Paper -> 2
            Throw.Scissor -> 3
        }

val Throw.beats: Throw
    get() =
        when (this) {
            Throw.Rock -> Throw.Scissor
            Throw.Paper -> Throw.Rock
            Throw.Scissor -> Throw.Paper
        }

fun outcome(theirs: Throw, mine: Throw): Int {
    return when {
        theirs == mine -> 3
        mine.beats == theirs -> 6
        else -> 0
    }
}

fun main() {

    val his = mapOf(
        "A" to Throw.Rock,
        "B" to Throw.Paper,
        "C" to Throw.Scissor
    )

    val ours = mapOf(
        "X" to Throw.Rock,
        "Y" to Throw.Paper,
        "Z" to Throw.Scissor
    )

    fun part1(input: List<String>): String {
        val rounds = input.map {
            val (t, m) = it.split(" ")
            val theirs = his[t]!!
            val mine = ours[m]!!
            mine.worth to outcome(theirs, mine)
        }

        return rounds.sumOf { it.first + it.second }.toString()
    }

    fun ours2(theirs: Throw, outcome: String): Throw {
        return when (outcome) {
            "X" -> theirs.beats
            "Y" -> theirs
            else -> Throw.values().first { it.beats == theirs }
        }
    }

    fun part2(input: List<String>): String {
        val rounds = input.map {
            val (t, m) = it.split(" ")
            val theirs = his[t]!!
            val mine = ours2(theirs, m)
            mine.worth to outcome(theirs, mine)
        }

        return rounds.sumOf { it.first + it.second }.toString()
    }

    val day = "02"

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test").split("\n")

    check(part1(testInput) == "15")
    check(part2(testInput) == "12")

    println("Test: ")

    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day${day}").split("\n")

    println("\nProblem: ")
    println(part1(input))
    println(part2(input))
}