import Color.Color

case class Sequence(
                     redDraw: Int = 0,
                     greenDraw: Int = 0,
                     blueDraw: Int = 0,
                     draws: Iterable[Draw] = Nil
                   )

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