import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

case class ExampleTest() extends AnyFunSpec with Matchers {

    val input = scala.io.Source.fromInputStream(getClass.getResourceAsStream("example.txt")).mkString

    describe("Given a game part") {
        it("Should yield 1") {
            Game.getGameId("Game 1") shouldBe 1
        }

        it("Should yield 10") {
            Game.getGameId("Game 10") shouldBe 10
        }
    }

    describe("Given a draw content") {
        it("Should yield a Draw(red, 4)") {
            Draw.parseDraw("4 red") shouldEqual Draw(Cube(Color.Red), 4)
        }

        it("Should yield a Draw(green, 12)") {
            Draw.parseDraw("12 green") shouldEqual Draw(Cube(Color.Green), 12)
        }
    }

    describe("Given a sequence") {
        it("Should parse (3 blue, 4 red)") {
            Sequence.parseSequence("3 blue, 4 red") shouldEqual Sequence(
                Seq(
                    Draw(Cube(Color.Blue), 3),
                    Draw(Cube(Color.Red), 4),
                )
            )
        }
    }

    describe("Given a game") {
        it("Should parse") {
            Game.parseGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green") shouldEqual Game(
                1,
                Seq(
                    Sequence(
                        Seq(
                            Draw(Cube(Color.Blue), 3),
                            Draw(Cube(Color.Red), 4),
                        )
                    ),
                    Sequence(
                        Seq(
                            Draw(Cube(Color.Red), 1),
                            Draw(Cube(Color.Green), 2),
                            Draw(Cube(Color.Blue), 6),
                        )
                    ),
                    Sequence(
                        Seq(
                            Draw(Cube(Color.Green), 2),
                        )
                    ),
                )
            )
        }
    }

}
