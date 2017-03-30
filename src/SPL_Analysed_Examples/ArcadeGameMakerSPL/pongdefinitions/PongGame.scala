package SPL_Analysed_Examples.ArcadeGameMakerSPL.pongdefinitions

import scala.swing._
import java.lang.Math._
import gameboard.GameBoardImpl._
import gamedefinitions._

class PongGame extends Game {

  val title = "Pong Game"
  val hPaddle1 = panelSize._2 - 10
  val hPaddle2 = 80

  val paddleWall = new LeftWall(Dimensions(x = 5, y = 2, w = 20, h = hPaddle1))
  val paddle = new Paddle(Dimensions(x = panelX.dimPX.w - 45, y = 160, w = 20, h = hPaddle2))
  val ballP = new Puck(Dimensions(x = 300, y = 200, w = 14, h = 14))
  val upWall = new Ceiling(Dimensions(x = 0, y = 0, w = panelX.dimPX.w, h = 1))
  val downWall = new Floor(Dimensions(x = 0, y = panelX.dimPX.h, w = panelX.dimPX.w, h = 1))

  ballP.xSpeed = ballP.velocity
  ballP.ySpeed = -ballP.velocity / 2

  def movePaddle = {
    paddle.move
    paddle.speedYP = if (upPressed) -10 else if (downPressed) 10 else 0
    if (paddle isOverlapping upWall)
        paddle.dimP.y = 0
    else if (paddle isOverlapping downWall)
             paddle.dimP.y = panelX.dimPX.h - paddle.dimP.h
  }

  def moveBall = {
    ballP.move
    if (!(ballP isOverlapping panelX)) {
          score -= 15
          initBall()
    } else if ((ballP isOverlapping paddle)) {
                score += 15
                ballP.reverseX
    } else if ((ballP isOverlapping paddleWall)) {
                ballP.reverseX
    }
    if ((ballP isOverlapping panelX) &&
       ((ballP isOverlapping upWall) ||
       ((ballP isOverlapping downWall)))) {
         ballP.reverseY
    }
  }

  def initBall() {
    ballP.dimP.x = 300
    ballP.dimP.y = 200
    ballP.xSpeed = (if (random < 0.5) -1 else 1) * ballP.velocity
    ballP.ySpeed = (if (random < 0.5) -1 else 1) * ((ballP.velocity * random()).asInstanceOf[Int] + 1)
  }

}