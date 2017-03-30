package SPL_Analysed_Examples.ArcadeGameMakerSPL.gamedefinitions

import Colors._
import java.lang.Math._
import gameboard.GameBoardImpl._

abstract class MovableSprite(val dimM: Dimensions, val shapeM: Shape,
  val isMovableM: Boolean = true)
  extends Sprite(dimM, shapeM, isMovableM) with SpeedControl {
  def isMoving = timer.timerS
  val velocity = 20
  var xSpeed: Int = velocity
  var ySpeed: Int = -velocity
  var speedXP = 0
  var speedYP = 0
  def reverseX() = xSpeed *= -1
  def reverseY() = ySpeed *= -1
  def move: Unit
}

class Paddle(val dimP: Dimensions, val shapeP: Shape = Rectangle)
  extends MovableSprite(dimP, shapeP) {
  def move() {
    dimP.y += speedYP
    dimP.x += speedXP
  }
}

class Puck(val dimP: Dimensions, val shapeP: Shape = Ellipse)
  extends MovableSprite(dimP, shapeP) {
  def move = {
    dimP.x += xSpeed
    dimP.y += ySpeed
  }
  def initiateSprite(x: Int, y: Int) = {
    dimP.x = x
    dimP.y = y
    xSpeed = (if (random < 0.5) -1 else 1) * ((4 * random()).asInstanceOf[Int] + 1)
    ySpeed = (if (random < 0.5) -1 else 1) * velocity
  }
}

class BowlingBall(val dimB: Dimensions)
  extends Puck(dimB) {
  var nrInitiations = 0
  override def move = {
    if (dimB.x > panelSize._1 - 30) {
      dimB.x = panelSize._1 - 30
    } else {
      dimB.x -= 2 * xSpeed
      dimB.y += ySpeed / 15 
    }
  }
  override def initiateSprite(x: Int, y: Int) = {
    nrInitiations += 1
    dimB.x = x
    dimB.y = y
    xSpeed = (if (random < 0.001) -1 else 1) * ((10 * random()).asInstanceOf[Int] + 1)
    ySpeed = (if (random < 0.5) -1 else 1) * velocity
  }
}

class Pin(val dimPin: Dimensions, override val shape: Shape = Rectangle)
  extends Puck(dimPin){
  override def move = {
    
  }
}