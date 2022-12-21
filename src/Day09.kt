data class Pos(val x: Int = 0, val y: Int = 0) {
    fun touches(other: Pos): Boolean {
        return kotlin.math.abs(this.x - other.x) < 2 &&
                kotlin.math.abs(this.y - other.y) < 2
    }
}

fun move(move: String, thing: Pos): List<Pos> {
    val (dir, cnt) = move.split(" ")

    return (0 until cnt.toInt()).runningFold(thing) { acc, _ ->
        when (dir) {
            "U" -> acc.copy(y = acc.y - 1)
            "D" -> acc.copy(y = acc.y + 1)
            "L" -> acc.copy(x = acc.x - 1)
            "R" -> acc.copy(x = acc.x + 1)
            else -> acc
        }
    }.drop(1)
}

fun Pos.chase(other: Pos): Pos {
    return if (!other.touches(this)) {
        Pos(x = this.x + (other.x - this.x).coerceIn(-1, 1),
        y = this.y + (other.y - this.y).coerceIn(-1, 1))
    } else this
}

fun printBoard(board: Set<Pos>) {
    val minX = board.minOf { it.x }
    val maxX = board.maxOf { it.x }
    val minY = board.minOf { it.y }
    val maxY = board.maxOf { it.y }

    println((minY .. maxY).joinToString("\n") { y ->
        (minX .. maxX).joinToString("") { x ->
            if (Pos(x, y) in board) { "#" } else { "."}
        }
    })
}

fun main() {
    fun part1(input: List<String>): String {
        var tail = Pos()

        val headPath = input.fold(listOf(Pos())) { acc, move ->
            acc + move(move, acc.last())
        }

        val tailPositions = mutableSetOf(tail)

        headPath.zipWithNext { prev, cur ->
            tail = tail.chase(cur)
            tailPositions.add(tail)
        }

//        printBoard(tailPositions)

        return tailPositions.size.toString()
    }

    fun part2(input: List<String>): String {

        val headPath = input.fold(listOf(Pos())) { acc, move ->
            acc + move(move, acc.last())
        }

        var tail = List(9) { Pos() }

        val tailPositions = mutableSetOf(tail.last())

        headPath.zipWithNext { hprev, hcur ->
            tail = tail.runningFold(hprev to hcur) { (prev, cur), me ->
                me to me.chase(cur)
            }.map { it.second }.drop(1)

//            printBoard(tail.toSet())
//            println("===")

            tailPositions.add(tail.last())
        }

//        printBoard(tailPositions)

        return tailPositions.size.toString()
    }

    val day = "09"

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test").split("\n")

    println("Test: ")
    println(part1(testInput))
    println(part2(testInput))

    check(part1(testInput) == "88")
    check(part2(testInput) == "36")

    val input = readInput("Day${day}").split("\n")

    println("\nProblem: ")
    println(part1(input))
    println(part2(input))
}
