package SPL_Analysed_Examples.ArcadeGameMakerSPL.gameboard

import scala.swing.event._
import GameBoardImpl._

trait EventHandlerInterface

class EventHandler extends EventHandlerInterface {
  def keyTpdPressed(kp: KeyPressed) = {
    if (kp.key == Key.Up) upPressed = true
    if (kp.key == Key.Down) downPressed = true
    if (kp.key == Key.Right) rightPressed = true
    if (kp.key == Key.Left) leftPressed = true
    if (kp.key == Key.Escape) sys.exit()
    if (kp.key == Key.S) timer.start()
    if (kp.key == Key.P || kp.key == Key.Space) timer.stop()
  }
  def keyTpdReleased(kr: KeyReleased) = {
    if (kr.key == Key.Up) upPressed = false
    if (kr.key == Key.Down) downPressed = false
    if (kr.key == Key.Right) rightPressed = false
    if (kr.key == Key.Left) leftPressed = false
  }
}