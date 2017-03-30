package SPL_Analysed_Examples.ArcadeGameMakerSPL.bowlingdefinitions

import gameboard._
import gameboard.GameBoardImpl._
import java.lang.Math._
import java.awt.Graphics2D
import gamedefinitions._

class BowlingGame extends Game {

  val title = "Bowling Game"
  val ballRadius = 20
  var rowLength = 190

  import scala.collection.mutable._
  val pins = new ListBuffer[Pin]
  val score = new ListBuffer[Int] //not finished...
  var rolls = new ListBuffer[Int] //not finished...

  val playingBall = new BowlingBall(Dimensions(x = 570, y = 145, w = ballRadius, h = ballRadius))
  val upWall = new Ceiling(Dimensions(x = 0, y = 80, w = panelX.dimPX.w, h = 1))
  val downWall = new Floor(Dimensions(x = 0, y = 230, w = panelX.dimPX.w, h = 1))

  initiatePins()

  def moveBall = {
    playingBall.move
    for (pin <- pins) {
      if ((playingBall isOverlapping upWall) ||
          (playingBall isOverlapping downWall) ||
          (playingBall.dimB.x < 10)) {
           playingBall.initiateSprite(570, 145)
        if (playingBall.nrInitiations % 2 == 0){
            score += rolls.sum
            rolls --= rolls.filter(_ % 1 == 0)
            println(score)
            
            initiatePins()
        }            
      } else if (playingBall isOverlapping pin) {
                 disappearPin(pin)
      }
    }
  }
  def initiatePins() = {
    for (j <- 0 to 3) {
      for (i <- 100 + j * (ballRadius / 2 + 5) to rowLength - j * (ballRadius - 5) by 30) {
        pins += new Pin(Dimensions(x = 20 + j * 25, y = i, w = ballRadius, h = ballRadius))
      }
    }
  }
  
  def disappearPin(pin: Pin) {
    rolls += 1
    
    pin.dimPin.x = 0
    pin.dimPin.y = 0
    pin.dimPin.w = 0
    pin.dimPin.h = 0
  }
  
}