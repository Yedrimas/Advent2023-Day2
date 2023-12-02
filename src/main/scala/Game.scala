case class Game(
                 id: Int,
                 sequences: Iterable[Sequence]
               ) {
    def isPossible(configuration: Configuration): Boolean = {
        sequences.forall(_.isPossible(configuration))
    }

    def getMinimumConfiguration: Configuration = {
        Configuration.reduceToMinimumConfiguration(
            sequences
              .map(_.getThisConfiguration)
        )
    }
}


case object Game {

    def getGameId(gamePart: String): Int = {
        val extractReg = "^Game ([0-9]+)$".r

        gamePart match {
            case extractReg(content) => content.toInt
            case _ => throw new Exception(s"Match failed for $gamePart")
        }
    }

    def parseGame(line: String): Game = {
        val parts = line.split(":")

        val gameIdPard = parts.head
        val gameId = getGameId(gameIdPard)

        val gameContentPart = parts(1)
        val sequences = Sequence.parseSequences(gameContentPart)

        Game(gameId, sequences)
    }

    def parseInput(lines: Iterable[String]): Iterable[Game] = {
        lines
          .map(parseGame)
    }

    def sumPossibleGames(games: Iterable[Game], configuration: Configuration): Int = {
        games
          .filter(_.isPossible(configuration))
          .map(_.id)
          .reduceOption(_ + _)
          .getOrElse(0)
    }

    def sumMinimumConfigurations(games: Iterable[Game]): Int = {
        // Alter minimum to avoid multiplying by 0
        def minIs1(i : Int) = Math.max(i, 1)

        games.map(_.getMinimumConfiguration)
          .map(conf => minIs1(conf.maxRed) * minIs1(conf.maxGreen) * minIs1(conf.maxBlue))
          .sum
    }

}

