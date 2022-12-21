
fun <T> List<List<T>>.column(y: Int): List<T> {
    return (0 .. this.lastIndex).map { this[it][y] }
}

fun main() {
    fun part1(input: List<List<Int>>): String {
        val max = input.lastIndex

        var visible = (max * 4)

        (1 until max).forEach { x ->
            (1 until max).forEach { y ->
                val dirs = sequence {
                    // right
                    yield(input[x].drop(y + 1))
                    // left
                    yield(input[x].take(y))
                    // up
                    yield(input.column(y).take(x))
                    // down
                    yield(input.column(y).drop(x + 1))
                }

                val thisTree = input[x][y]
                if (dirs.any { it.all { t -> t < thisTree } }) {
                    visible += 1
                } else {
//                    print("Not visible: $x, $y == $thisTree\n")
                }
            }
        }

        return visible.toString()
    }

    fun part2(input: List<List<Int>>): String {
        val max = input.lastIndex

        val scores = (0 .. max).flatMap { x ->
            (0 .. max).map { y ->
                val dirs = sequence {
                    // right
                    yield(input[x].drop(y + 1))
                    // left
                    yield(input[x].take(y).reversed())
                    // up
                    yield(input.column(y).take(x).reversed())
                    // down
                    yield(input.column(y).drop(x + 1))
                }

                val thisTree = input[x][y]

                dirs.map { (it.indexOfFirst { t -> t >= thisTree }.takeIf { it > -1 } ?: it.lastIndex) + 1 }
                    .reduce { acc, i -> acc * i }
            }
        }

//        print(scores)

        return scores.max().toString()
    }

    val day = "08"

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test").split("\n").map { it.map { it.toString().toInt() } }

    println("Test: ")
    println(part1(testInput))
    println(part2(testInput))

    check(part1(testInput) == "21")
    check(part2(testInput) == "8")

    val input = readInput("Day${day}").split("\n").map { it.map { it.toString().toInt() } }

    println("\nProblem: ")
    println(part1(input))
    println(part2(input))
}
