fun main() {
    val lOffset = 'a'.code - 1

    val uOffset = 'A'.code - 1

    fun prio(c: Char): Int {
        return if (c.isUpperCase()) {
            (c.code - uOffset) + 26
        } else {
            c.code - lOffset
        }
    }

    fun part1(input: List<String>): String {
        val sacks = input.map { sack ->
            (sack.length / 2).let { mid ->
                sack.subSequence(0, mid) to sack.subSequence(mid, sack.length)
            }
        }
        val common = sacks.map { sack ->
            sack.first.first { it in sack.second }
        }

//        println(common)
        val ints = common.map { prio(it) }

//        println(ints)

        return ints.sum().toString()
    }

    fun part2(input: List<String>): String {
        val groups = input.chunked(3)

        val common = groups.map { sacks ->
            sacks.reduce { acc, sack ->
                acc.filter { it in sack }
            }[0]
        }

        println(common)

        return common.map { prio(it) }.sum().toString()
    }

    val day = "03"

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test").split("\n")

    check(part1(testInput) == "157")
    check(part2(testInput) == "70")

    println("Test: ")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day${day}").split("\n")

    println("\nProblem: ")
    println(part1(input))
    println(part2(input))
}
