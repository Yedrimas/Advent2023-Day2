import Color.Color

case class Cube(
                 color: Color
               )

case object Cube {
    def parseCube(drawPart: String): Cube = {
        drawPart.trim match {
            case Draw.DRAW_PARTS_REG(_, color) => Cube(Color.withName(color))
            case _ => throw new Exception(s"Could not parse cube from $drawPart")
        }
    }
}

