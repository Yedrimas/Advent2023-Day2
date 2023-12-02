
case class Sequence(
                     sequences: Iterable[Draw]
                   )

object Sequence {

    def parseSequence(sequence: String) : Sequence = {
        Sequence(Draw.parseDraws(sequence))
    }

    def parseSequences(gameContentPart: String): Iterable[Sequence] = {
        gameContentPart
          .split(";")
          .map(parseSequence)
    }
}