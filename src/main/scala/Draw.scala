import scala.util.matching.Regex

case class Draw(
                 cube: Cube,
                 number: Int
               )

case object Draw {
    val DRAW_PARTS_REG: Regex = "^([0-9]+) ([a-z]+)$".r

    def parseDrawNumber(drawString: String): Int = {
        drawString.trim match {
            case DRAW_PARTS_REG(num, _) => num.toInt
            case _ => throw new Exception(s"Could not match draw num $drawString")
        }
    }

    def parseDraw(drawContent: String): Draw = {
        Draw(Cube.parseCube(drawContent), parseDrawNumber(drawContent))
    }

    def parseDraws(gameContent: String): Iterable[Draw] = {
        gameContent
          .split(",")
          .map(parseDraw)
    }

}