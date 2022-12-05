data class Command(val move: Int, val from: Int, val to: Int)

fun main() {

    fun makeStacks(input: List<String>): MutableList<MutableList<Char>> {
        val mid = input.indexOfFirst { it.isEmpty() }
        val crates = input.take(mid - 1).reversed().map { it.chunked(4).map { it.find { it.isUpperCase() } }}
        val stacks = MutableList(crates[0].size) { mutableListOf<Char>() }
        crates.forEach { row ->
            row.forEachIndexed { index, c ->
                c?.also { stacks[index].add(it) }
            }
        }
        return stacks
    }

    fun makeCommands(input: List<String>): List<Command> {
        val mid = input.indexOfFirst { it.isEmpty() }
        val ints = input.drop(mid+1).map { it.split(" ").mapNotNull { it.toIntOrNull() } }
        return ints.map { Command(it[0], it[1]-1 , it[2]-1 ) }
    }

    fun execute9000(command: Command, stacks: MutableList<MutableList<Char>>) {
        repeat(command.move) {
            stacks[command.to].add(stacks[command.from].removeLast())
        }
    }

    fun part1(input: List<String>): String {
        val stacks = makeStacks(input)
        makeCommands(input).forEach {
            execute9000(it, stacks)
        }

        return stacks.map { it.last() }.joinToString("")
    }

    fun execute9001(command: Command, stacks: MutableList<MutableList<Char>>) {
        val temp = mutableListOf<Char>()
        repeat(command.move) {
            temp.add(stacks[command.from].removeLast())
        }
        stacks[command.to].addAll(temp.reversed())
    }


    fun part2(input: List<String>): String {
        val stacks = makeStacks(input)
        makeCommands(input).forEach {
            execute9001(it, stacks)
        }

        return stacks.map { it.last() }.joinToString("")
    }

    val day = "05"

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test").split("\n")

    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    println("Test: ")
    println(part1(testInput))
    println(part2(testInput))

    val input = readInput("Day${day}").split("\n")

    println("\nProblem: ")
    println(part1(input))
    println(part2(input))
}
