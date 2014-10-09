package com.lawnmower

import scala.io._
import scala.util.{Failure, Success, Try}

/**
 * Created by thibault on 10/3/14.
 */
object Main {

  def main(args: Array[String]): Unit = {
    val file_name = if(args.length > 0) args(0) else "resources/input.txt"
    run(Source.fromFile(file_name).mkString) match {
      case Success(mowers) => mowers foreach println
      case Failure(f) => println(f.getMessage)
    }
  }

  def run(s: String) = {
    Try(process(s.lines))
  }

  def process(it: Iterator[String]) = {
    val grid_size = it.next().trim.split(' ').map(_.toInt).toVector //Parse the top right coordinates
    it map { line =>
      val orientation = Orientation.parse(line.trim.split(' ').last) //Parse the original orientation
    val position = line.trim.split(' ').init.map(_.toInt).toVector //Create the initial position vector
    val m = Mower(position, grid_size, orientation)
      m.move(it.next().trim.toCharArray)
      m
    }
  }
}
