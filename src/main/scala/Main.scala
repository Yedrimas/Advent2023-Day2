case object Main {
    def main(args: Array[String]): Unit = {

        // Part 1
        println(
            Game.sumPossibleGames(
                Game.parseInput(Utils.readLinesFromRsc("part1.txt")), Configuration(maxRed = 12, maxGreen = 13, maxBlue = 14)
            )
        )
    }
}
