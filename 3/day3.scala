//> using toolkit 0.7.0

@main
def day3(): Unit = {
    val path: os.Path = os.pwd / "input.txt"
    val content: String = os.read(path)    
    val mulIns = raw"mul\((\d{1,3}),(\d{1,3})\)".r

    // Part 1
    var result: Int = 0
    for (m <- mulIns.findAllMatchIn(content)) {
        result += m.group(1).toInt * m.group(2).toInt
    }
    println(result)

    // Part 2
    val doIns = raw"do\(\)".r
    val dontIns = raw"don't\(\)".r
    val allIns = raw"${mulIns.regex}|${doIns.regex}|${dontIns.regex}".r

    var result2: Int = 0
    var flag: Boolean = true
    val iter = allIns.findAllMatchIn(content)

    while(iter.hasNext){
        val m = iter.next
        if (m.group(0) == "do()") {
            flag = true
        } else if (m.group(0) == "don't()") {
            flag = false
        } else if (flag) {
            result2 += m.group(1).toInt * m.group(2).toInt
        }
    }
    println(result2)
}