package com.lawnmower

/**
 * Created by thibault on 10/3/14.
 */

case class CardinalPoint(vector: Vector[Int], name: String)

object Orientation {
  val card_points = Array(CardinalPoint(Array(0,1).toVector, "North"),
    CardinalPoint(Array(1,0).toVector, "East"),
    CardinalPoint(Array(0,-1).toVector, "South"),
    CardinalPoint(Array(-1,0).toVector, "West")
  )

  def get(i: Int) = card_points(i)
  def prev(idx: Int) = if(idx-1 >= 0) idx-1 else card_points.length-1 //Turn 90deg left
  def next(idx: Int) = (idx+1)%card_points.length //Turn 90deg right

  def parse(s: String) = s match {
    case "N" => 0
    case "E" => 1
    case "S" => 2
    case "W" => 3
    case u => throw new MatchError(s"Unknown cardinal point: $u")
  }
}