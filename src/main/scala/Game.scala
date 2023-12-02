case class Game(
                 id: Int,
                 sequences: Iterable[Sequence]
               )


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

}

