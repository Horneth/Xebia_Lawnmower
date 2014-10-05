package com.lawnmower

import scala.io._

/**
 * Created by thibault on 10/3/14.
 */
object Main {

  def main(args: Array[String]): Unit = {

    try {
      val file_name = if(args.length > 0) args(0) else "resources/input.txt"
      val it = Source.fromFile(file_name).getLines()

      val grid_size = it.next().trim.split(' ').map(_.toInt).toVector //Parse the top right coordinates
      it foreach { line =>
        val orientation = Orientation.parse(line.trim.split(' ').last) //Parse the original orientation
        val position = line.trim.split(' ').init.map(_.toInt).toVector //Create the initial position vector
        val m = Mower(position, grid_size, orientation)
        m.move(it.next().trim.toCharArray)
      }
    } catch {
      case e: NumberFormatException => println("Wrong coordinates format: " + e.getMessage)
      case e: MatchError => println("Wrong file format: " + e.getMessage)
      case e: Throwable => println(e.getMessage)
    }
  }
}
