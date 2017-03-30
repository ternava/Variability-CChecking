package SPL_Analysed_Examples.ArcadeGameMakerSPL.brickles

import SPL_Analysed_Examples.ArcadeGameMakerSPL.bricklesdefinitions.BricklesGame

import swing._
import event._

object BricklesGUI extends SimpleSwingApplication {

  val brickle = new BricklesGame()

  def top = new MainFrame {
    title = brickle.title
    contents = panel
    menuBar = menuBarSample
  }

  val panel = new Panel {
    override def paint(g: Graphics2D) {
      panelX.draw(g, bluish)
      brickle.brickleFirstRow.foreach(x => x.draw(g, orange))
      brickle.brickleSecondRow.foreach(x => x.draw(g, orange))
      brickle.paddle.draw(g, magenta)
      brickle.ball.draw(g, black)
      brickle.leftWall.draw(g, black)
      brickle.rightWall.draw(g, black)
      brickle.upWall.draw(g, black)
      g setColor black
      g.drawString(displayScores, panelSize._1 - 150, panelSize._2 - 30)
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
    brickle.movePaddle
    brickle.moveBall
    panel.repaint
  }))

}