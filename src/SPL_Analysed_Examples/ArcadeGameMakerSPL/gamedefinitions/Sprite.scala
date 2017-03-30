package SPL_Analysed_Examples.ArcadeGameMakerSPL.gamedefinitions

import java.awt.Color
import java.awt.geom.Rectangle2D
import java.awt.geom.Ellipse2D
import java.awt.Graphics2D

sealed trait Shape
case object Rectangle extends Shape
case object Ellipse extends Shape

case class Dimensions(var x: Int, var y: Int, var w: Int, var h: Int)

abstract class Sprite(val dim: Dimensions, val shape: Shape, val isMovable: Boolean = false) {

  def draw(g: Graphics2D, color: Color): Unit = {
    g setColor color
    shape match {
      case Rectangle => g fillRect (dim.x, dim.y, dim.w, dim.h)
      case Ellipse => g fillOval (dim.x, dim.y, dim.w, dim.h)
    }
  }

  def getCollisionBox: Rectangle2D = new Rectangle2D.Double(dim.x, dim.y, dim.w, dim.h)
  def getCollisionOval: Ellipse2D = new Ellipse2D.Double(dim.x, dim.y, dim.w, dim.h)

  def isOverlapping(other: Sprite): Boolean = {
    val oBox = other.shape match {
      case Rectangle => other.getCollisionBox
      case Ellipse => other.getCollisionOval
    }
    val tBox = this.shape match {
      case Rectangle => this.getCollisionBox
      case Ellipse => this.getCollisionOval
    }

    if (tBox.getX > oBox.getX + oBox.getWidth) false
    else if (tBox.getX + tBox.getWidth < oBox.getX) false
    else if (tBox.getY > oBox.getY + oBox.getHeight) false
    else if (tBox.getY + tBox.getHeight < oBox.getY) false
    else true
  }
  
  def disappearSprite(sprite: Sprite) {
    sprite.dim.x = 0
    sprite.dim.y = 0
    sprite.dim.w= 0
    sprite.dim.h = 0
  }
  
}