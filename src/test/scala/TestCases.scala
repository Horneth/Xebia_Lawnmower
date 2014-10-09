import com.lawnmower.{Mower, Orientation, Main}
import org.specs2.mutable._

/**
 * Created by thibault on 10/9/14.
 */
class TestCases extends Specification {

  "Lawnmower" should {

    "work for Test Case #1" in {
      val commands =
        """5 5
      1 2 N
      GAGAGAGAA
      3 3 E
      AADAADADDA""".stripMargin

      val tryrun = Main.run(commands)

      tryrun should beSuccessfulTry.withValue({mowers: Iterator[Mower] =>
        val m1 = mowers.next()
        m1.pos(0) must beEqualTo(1)
        m1.pos(1) must beEqualTo(3)
        m1.o must beEqualTo(Orientation.parse("N"))

        val m2 = mowers.next()
        m2.pos(0) must beEqualTo(5)
        m2.pos(1) must beEqualTo(1)
        m2.o must beEqualTo(Orientation.parse("E"))

        mowers.isEmpty must beTrue
      })

    }

    "fail with wrong input format" in {
      val commands =
        """5 A
      1 2 N
      GAGAGAGAA
      3 3 E
      AADAADADDA""".stripMargin

      val tryrun = Main.run(commands)

      tryrun should beFailedTry
    }
  }

}
