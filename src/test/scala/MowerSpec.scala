import com.lawnmower.{Orientation, Mower}
import org.specs2.mock.Mockito
import org.specs2.mutable._

class MowerSpec extends Specification with Mockito {

  //Cardinal Points
  val north = Orientation.parse("N")
  val south = Orientation.parse("S")
  val east = Orientation.parse("E")
  val west = Orientation.parse("W")

  //Commands
  val left = Array('G')
  val right = Array('D')
  val ahead = Array('A')

  //Grid
  val grid = Array(1,1).toVector

  "Mower" should {

    "Turn to the left" in {
      val m = Mower(any[Vector[Int]], any[Vector[Int]], north)
      m.o must beEqualTo(north)
      m.move(left)
      m.o must beEqualTo(west)
      m.move(left)
      m.o must beEqualTo(south)
      m.move(left)
      m.o must beEqualTo(east)
      m.move(left)
      m.o must beEqualTo(north)
    }

    "Turn to the right" in {
      val m = Mower(any[Vector[Int]], any[Vector[Int]], north)
      m.o must beEqualTo(north)
      m.move(right)
      m.o must beEqualTo(east)
      m.move(right)
      m.o must beEqualTo(south)
      m.move(right)
      m.o must beEqualTo(west)
      m.move(right)
      m.o must beEqualTo(north)
    }

    "Move north" in {
      val pos = Array(0, 0).toVector
      val m = Mower(pos, grid, north)
      m.o must beEqualTo(north)
      m.pos must beEqualTo(pos)

      m.move(ahead)

      m.pos must beEqualTo(Array(0, 1).toVector)
      m.o must beEqualTo(north)
    }

    "Move east" in {
      val pos = Array(0, 0).toVector
      val m = Mower(pos, grid, east)
      m.o must beEqualTo(east)
      m.pos must beEqualTo(pos)

      m.move(ahead)

      m.pos must beEqualTo(Array(1, 0).toVector)
      m.o must beEqualTo(east)
    }

    "Move south" in {
      val pos = Array(0, 1).toVector
      val m = Mower(pos, grid, south)
      m.o must beEqualTo(south)
      m.pos must beEqualTo(pos)

      m.move(ahead)

      m.pos must beEqualTo(Array(0, 0).toVector)
      m.o must beEqualTo(south)
    }

    "Move west" in {
      val pos = Array(1, 0).toVector
      val m = Mower(pos, grid, west)
      m.o must beEqualTo(west)
      m.pos must beEqualTo(pos)

      m.move(ahead)

      m.pos must beEqualTo(Array(0, 0).toVector)
      m.o must beEqualTo(west)
    }
    
    "Stay in the grid boundaries" in {
      val origin = Array(0, 0).toVector
      val top_left = Array(0, 1).toVector
      val bottom_left = Mower(origin, grid, west)
      val top_right = Mower(grid, grid, north)

      bottom_left.move(ahead) //move west (impossible)

      bottom_left.pos must beEqualTo(origin)
      bottom_left.o must beEqualTo(west)

      bottom_left.move(left) //turn south
      bottom_left.move(ahead) //move south (impossible)
      bottom_left.pos must beEqualTo(origin)
      bottom_left.o must beEqualTo(south)

      top_right.move(ahead) //move north (impossible)

      top_right.pos must beEqualTo(grid)
      top_right.o must beEqualTo(north)

      top_right.move(right) //turn east
      top_right.move(ahead) //move east (impossible)
      top_right.pos must beEqualTo(grid)
      top_right.o must beEqualTo(east)
      
    }

  }

}