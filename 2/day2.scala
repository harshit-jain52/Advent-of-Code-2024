//> using toolkit 0.7.0

// Part 1
def isSafe(report: List[Int]): Boolean = {
    if (report == report.sorted) {
        report.sliding(2).forall {
            case List(a, b) => (1 to 3).contains(b - a)
            case _          => true
        }
    } else if (report == report.sorted.reverse) {
        report.sliding(2).forall {
            case List(a, b) => (1 to 3).contains(a - b)
            case _          => true
        }
    } else {
        false
    }
}

// Part 2
def isSafe2(report: List[Int]): Boolean = {
    isSafe(report) || (1 to report.length).exists { i =>
        isSafe(report.take(i - 1) ::: report.takeRight(report.length - i))
    }
}

@main
def day2(): Unit = {
    val path: os.Path = os.pwd / "input.txt"
    val lineStream: geny.Generator[String] = os.read.lines.stream(path)
    var safeCount: Int = 0
    var safeCount2: Int = 0
    for (line <- lineStream) {
        val report: List[Int] = line.split(" ").toList.map(_.toInt)
        safeCount += (isSafe(report) match {
            case true => 1
            case false => 0
        })
        safeCount2 += (isSafe2(report) match {
            case true => 1
            case false => 0
        })
    }
    println(safeCount)
    println(safeCount2)
}