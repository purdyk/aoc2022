sealed class Node(val name: String) {
    class File(name:String, val length: Int): Node(name)
    class Dir(name: String, val parent: Dir?, val children: MutableList<Node> = mutableListOf()): Node(name) {
        fun select(from: String): Dir {
            return if (from == "..") {
                parent!!
            } else {
                children.first { it.name == from } as Dir
            }
        }
    }
}

fun Node.size(): Int {
    return when (this) {
        is Node.File -> this.length
        is Node.Dir -> this.children.sumOf { it.size() }
    }
}

fun cmd(line: String, parent: Node.Dir): Node.Dir {
    return when (line[2]) {
        'c' -> parent.select(line.split(" ")[2])
        'd' -> parent
        else -> parent
    }
}

fun entry(line: String, parent: Node.Dir): Node {
    val (a,b) = line.split(" ")
    return if (a == "dir") {
         Node.Dir(b, parent)
    } else {
        Node.File(b, a.toInt())
    }
}

fun parse(input: List<String>): Node {
    val root = Node.Dir("/", null)

    var cur = root

    input.drop(1).forEach {
        when (it[0]) {
            '$' -> cur = cmd(it, cur)
            else -> cur.children.add(entry(it, cur))
        }
    }

    return root
}

fun print(node: Node, prefix: String = "") {
    print("$prefix${node.name}\n")
    (node as? Node.Dir)?.also {
        it.children.forEach { print(it, "$prefix ") }
    }
}

fun Node.walkDirectories(): Sequence<Node.Dir> {
    val subDirs = ((this as? Node.Dir)?.children ?: emptyList<Node>())
        .filterIsInstance<Node.Dir>()
        .iterator()

    return sequence {
        while (subDirs.hasNext()) {
            val dir = subDirs.next()
            yield(dir)
            yieldAll(dir.walkDirectories())
        }
    }
}

fun main() {
    fun part1(input: List<String>): String {
        val root = parse(input)
        val smalls = root.walkDirectories().filter { it.size() <= 100000 }.toList()

        return smalls.sumOf { it.size() }.toString()
    }

    fun part2(input: List<String>): String {
        val total = 70000000
        val needed = 30000000
        val root = parse(input)

        val used = root.size()
        val free = total - used
        val toFree = needed - free

        val bigs = root.walkDirectories().filter { it.size() >= toFree }.toList()
        val selected = bigs.minBy { it.size() }
        return selected.size().toString()
    }

    val day = "07"

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test").split("\n")

    println("Test: ")
    println(part1(testInput))
    println(part2(testInput))

    check(part1(testInput) == "95437")
    check(part2(testInput) == "24933642")

    val input = readInput("Day${day}").split("\n")

    println("\nProblem: ")
    println(part1(input))
    println(part2(input))
}
