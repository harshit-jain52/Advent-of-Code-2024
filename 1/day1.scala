//> using toolkit 0.7.0

// Part 1
def distance(left: List[Int], right: List[Int]): Int = {
    val sortedLeft = left.sorted
    val sortedRight = right.sorted
    var distance = 0
    for(i <- 0 until sortedLeft.length) {
        distance += Math.abs(sortedLeft(i) - sortedRight(i))
    }
    distance
}

// Part 2
def similarityScore(left: List[Int], right: List[Int]): Int = {
    var similarityScore = 0
    for(i <- 0 until left.length) {
        similarityScore += left(i) * right.count(_ == left(i))
    }
    similarityScore
}

@main
def day1(): Unit = {
    val path: os.Path = os.pwd / "input.txt"
    val lineStream: geny.Generator[String] = os.read.lines.stream(path)
    var leftList: List[Int] = List()
    var rightList: List[Int] = List()
    for (line <- lineStream) {
        val Array(l, r) = line.split("   ").map(_.toInt)
        leftList = leftList :+ l
        rightList = rightList :+ r
    }

    println(distance(leftList, rightList))
    println(similarityScore(leftList, rightList))
}