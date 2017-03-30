package SPL_Analysed_Examples.ArcadeGameMakerSPL.gameboard

import SPL_Analysed_Examples.ArcadeGameMakerSPL.gamedefinitions._

import swing._
import event._

trait GameBoard extends Game

object GameBoardImpl extends GameBoard with ScoreBoard {

  val timer = new ScalaTimer(50)
  val eHandler = new EventHandler

  var upPressed = false
  var downPressed = false
  var rightPressed = false
  var leftPressed = false
  
  val panelSize = (600, 400)

  val panelX = new PanelX(Dimensions(x = 0, y = 0, w = panelSize._1, h = panelSize._2))

  def displayScores = {
    if (score < 0) {
      timer.stop(); "You lost!"
    } else "Scores: " + score
  }
  
}