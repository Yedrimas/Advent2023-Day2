import Color.Color

case class Sequence(
                     redDraw: Int = 0,
                     greenDraw: Int = 0,
                     blueDraw: Int = 0,
                     draws: Iterable[Draw] = Nil
                   ) {

    override def equals(obj: Any): Boolean = {
        obj match {
            case otherSeq: Sequence => otherSeq.redDraw == redDraw && otherSeq.greenDraw == greenDraw && otherSeq.blueDraw == blueDraw
            case _ => super.equals(obj)
        }
    }

    def isPossible(c: Configuration): Boolean = {
        isPossible(c.maxRed, c.maxGreen, c.maxBlue)
    }

    def isPossible(maxRed: Int, maxGreen: Int, maxBlue: Int): Boolean = {
        redDraw <= maxRed && greenDraw <= maxGreen && blueDraw <= maxBlue
    }

    def getThisConfiguration: Configuration = {
        Configuration(
            maxRed = redDraw, maxGreen = greenDraw, maxBlue = blueDraw
        )
    }
}

object Sequence {

    private def getFromDrawOr0(color: Color, draws: Iterable[Draw]): Int = {
        val maybeDraw = draws.find(_.cube.color == color)
        if (maybeDraw.isDefined)
            maybeDraw.get.number
        else
            0
    }
    def parseSequence(sequence: String) : Sequence = {
        val drawSeq = Draw.parseDraws(sequence)
        Sequence(
            redDraw = getFromDrawOr0(Color.Red, drawSeq),
            greenDraw = getFromDrawOr0(Color.Green, drawSeq),
            blueDraw = getFromDrawOr0(Color.Blue, drawSeq),
            draws = drawSeq // Preserve for future usage ?
        )
    }

    def parseSequences(gameContentPart: String): Iterable[Sequence] = {
        gameContentPart
          .split(";")
          .map(parseSequence)
    }
}