import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

case class ExampleTest() extends AnyFunSpec with Matchers {

    val input = Utils.readLinesFromRsc("example.txt")

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
                blueDraw = 3,
                redDraw = 4
            )
        }
    }

    describe("Given a game line") {
        it("Should parse") {
            Game.parseGame("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green") shouldEqual Game(
                1,
                Seq(
                    Sequence(
                        blueDraw = 3,
                        redDraw = 4
                    ),
                    Sequence(
                        redDraw = 1,
                        greenDraw = 2,
                        blueDraw = 6
                    ),
                    Sequence(
                        greenDraw = 2
                    ),
                )
            )
        }
    }

    describe("Given the full input") {
        it("Should yield 8") {
            val parsedGames = Game.parseInput(input)
            Game.sumPossibleGames(parsedGames, Configuration(maxRed = 12, maxGreen = 13, maxBlue = 14)) shouldEqual 8
        }
    }

}
