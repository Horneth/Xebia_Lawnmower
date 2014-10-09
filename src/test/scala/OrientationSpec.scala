import com.lawnmower.Orientation
import org.specs2.mutable._

/**
 * Created by thibault on 10/9/14.
 */
class OrientationSpec extends Specification {

  //Cardinal Points
  val north = Orientation.parse("N")
  val south = Orientation.parse("S")
  val east = Orientation.parse("E")
  val west = Orientation.parse("W")

  "Orientation" should {
    "parse cardinal points" in {
      val n = Orientation.get(north)
      n.vector must beEqualTo(Array(0,1).toVector)
      n.name must beEqualTo("North")

      val s = Orientation.get(south)
      s.vector must beEqualTo(Array(0,-1).toVector)
      s.name must beEqualTo("South")

      val e = Orientation.get(east)
      e.vector must beEqualTo(Array(1,0).toVector)
      e.name must beEqualTo("East")

      val w = Orientation.get(west)
      w.vector must beEqualTo(Array(-1,0).toVector)
      w.name must beEqualTo("West")
    }
    
    "loop through cardinal points clockwise" in {
      Orientation.get(Orientation.next(north)) must beEqualTo(Orientation.get(east))
      Orientation.get(Orientation.next(east)) must beEqualTo(Orientation.get(south))
      Orientation.get(Orientation.next(south)) must beEqualTo(Orientation.get(west))
      Orientation.get(Orientation.next(west)) must beEqualTo(Orientation.get(north))
    }

    "loop through cardinal points counter-clockwise" in {
      Orientation.get(Orientation.prev(north)) must beEqualTo(Orientation.get(west))
      Orientation.get(Orientation.prev(west)) must beEqualTo(Orientation.get(south))
      Orientation.get(Orientation.prev(south)) must beEqualTo(Orientation.get(east))
      Orientation.get(Orientation.prev(east)) must beEqualTo(Orientation.get(north))
    }
  }

}
