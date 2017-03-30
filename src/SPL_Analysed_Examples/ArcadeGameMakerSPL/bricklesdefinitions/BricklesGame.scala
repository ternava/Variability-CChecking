package SPL_Analysed_Examples.ArcadeGameMakerSPL.bricklesdefinitions

import java.lang.Math._

import SPL_Analysed_Examples.ArcadeGameMakerSPL.gamedefinitions._

class BricklesGame extends Game {

  val title = "Brickles Game"
  val widthBrickle = 60
  val heighBrickle = 30
  val firstRowY = 20
  val secondRowY = 55

  import scala.collection.mutable._
  val brickleFirstRow = new ListBuffer[Brick]
  val brickleSecondRow = new ListBuffer[Brick]

  initiateBricks()

  val ball = new Puck(Dimensions(x = 300, y = 200, w = 14, h = 14))
  val leftWall = new LeftWall(Dimensions(x = 0, y = 0, w = 1, h = panelX.dimPX.h))
  val rightWall = new RightWall(Dimensions(x = panelX.dimPX.w, y = 0, w = 1, h = panelX.dimPX.h))
  val upWall = new Ceiling(Dimensions(x = 0, y = 0, w = panelX.dimPX.w, h = 1))
  val paddle = new Paddle(Dimensions(x = panelX.dimPX.w / 2 - 40, y = panelX.dimPX.h - 100,
    w = widthBrickle + 20, h = heighBrickle))

  def movePaddle = {
    paddle.move
    paddle.speedXP = if(rightPressed) 10 else if(leftPressed) -10 else 0
    if ((paddle isOverlapping panelX)) {
      if ((paddle isOverlapping leftWall))
        paddle.dimP.x = 0
      else if ((paddle isOverlapping rightWall))
        paddle.dimP.x = panelX.dimPX.w - paddle.dimP.w
    }
  }

  def moveBall = {
    ball.move
    for (brFirst <- brickleFirstRow) {
      for (brSecond <- brickleSecondRow) {
        if (!(ball isOverlapping panelX)) {
          score -= 1
          ball.initiateSprite(300, 200)
        } else if (ball isOverlapping paddle) {
          ball.reverseY
        } else if (ball isOverlapping brFirst) {
          ball.reverseY
          brFirst.disappearSprite(brFirst)
          score += 1
        } else if (ball isOverlapping brSecond) {
          ball.reverseY
          brFirst.disappearSprite(brSecond)
          score += 1
        }
        if ((ball isOverlapping panelX) &&
          ((ball isOverlapping leftWall) ||
            (ball isOverlapping rightWall))) {
          ball.reverseX
        } else if (ball isOverlapping upWall) {
          ball.reverseY
        }
      }
    }
  }

  def initiateBricks() = {
    for (i <- 10 to 550 by 65) {
      brickleFirstRow += new Brick(Dimensions(x = i, y = firstRowY, w = widthBrickle, h = heighBrickle))
      brickleSecondRow += new Brick(Dimensions(x = i, y = secondRowY, w = widthBrickle, h = heighBrickle))
    }
  }

}