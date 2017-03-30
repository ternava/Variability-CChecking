package SPL_Analysed_Examples.ArcadeGameMakerSPL.pong

import swing._
import event._
import gamedefinitions.Colors._
import gameboard.GameBoardImpl._
import pongdefinitions.PongGame

object PongGUI extends SimpleSwingApplication {
  
  val pong = new PongGame()
  
  def top = new MainFrame {
    title = pong.title
    contents = panel
    menuBar = menuBarSample
  }

  val panel = new Panel {    
    override def paint(g: Graphics2D) {
      panelX.draw(g, bluish)
      pong.upWall.draw(g, black)
      pong.downWall.draw(g, black)
      pong.paddleWall.draw(g, blue)    
      pong.paddle.draw(g, orange)     
      pong.ballP.draw(g, yellow)
      g setColor black
      //g.drawString("Is Moving: " + pong.ballP.isMoving, panelSize._1 - 150, panelSize._2 - 50)
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
    pong.movePaddle
    pong.moveBall
    panel.repaint
  }))

}