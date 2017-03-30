package SPL_Analysed_Examples.ArcadeGameMakerSPL.bowling

import scala.swing._
import event._
import gamedefinitions.Colors._
import gameboard.GameBoardImpl._
import bowlingdefinitions.BowlingGame

object BowlingGUI extends SimpleSwingApplication {

  val bowling = new BowlingGame()

  def top = new MainFrame() {
    title = bowling.title
    contents = panel
    menuBar = menuBarSample
  }

  val panel = new Panel {
    override def paint(g: Graphics2D) {
      panelX.draw(g, bluish)
      //g.rotate(Math.toRadians(45))
      bowling.pins.foreach(x => x.draw(g, orange))
      //g.rotate(Math.toRadians(-45))
      bowling.playingBall.draw(g, black)
      bowling.upWall.draw(g, white)
      bowling.downWall.draw(g, white)
      g setColor black
      g.drawString("" + bowling.score, panelSize._1 - 150, panelSize._2 - 30)
    }
    listenTo(keys, mouse.moves, mouse.clicks)
    reactions += {
      case me: MouseEntered => requestFocus
      case kp: KeyPressed => eHandler.keyTpdPressed(kp)
      case kr: KeyReleased => eHandler.keyTpdReleased(kr)
    }
    preferredSize = new Dimension(panelSize._1, panelSize._2)
  }

  timer.tmr.addActionListener(Swing.ActionListener(e => {
    bowling.moveBall
    panel.repaint
  }))

}