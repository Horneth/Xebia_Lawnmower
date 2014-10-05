package com.lawnmower


/**
 * Created by thibault on 10/3/14.
 * Mower Class
 */

/**
 * Mower class
 * @param pos: position vector
 * @param corner: top right corner coordinates of the grid
 * @param o: orientation represented by its index in the cardinal points array
 */
case class Mower(var pos: Vector[Int], corner: Vector[Int], var o: Int) {

  //process a list of commands
  def move(commands: Array[Char]) = {
    commands foreach {
      case 'D' => o = Orientation.next(o) //turn right
      case 'G' => o = Orientation.prev(o) //turn left
      case 'A' => //move ahead
        val newPos = pos zip Orientation.get(o).vector map { p => p._1 + p._2}  //sum pos with the orientation vector
        if (isInGrid(newPos)) pos = newPos
      case c => throw new MatchError(s"Unknown command: $c")
    }
    println(this)
  }

  //Check that all coordinates are within the grid boundaries
  def isInGrid(v: Vector[Int]) = v zip corner forall { p => p._1 >= 0 && p._1 <= p._2}

  override def toString = s"""Position = $pos
         |Orientation = ${Orientation.get(o).name}
         |""".stripMargin

}
