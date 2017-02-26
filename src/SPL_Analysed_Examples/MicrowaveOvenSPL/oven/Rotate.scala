package MicrowaveOvenSPL.oven

import scala.swing._
import java.awt.Graphics2D._
import javax.swing.ImageIcon

object Rotate extends SimpleSwingApplication {

  val imgTurntable = new Label {
    text = "I am a label"
    icon = new ImageIcon("MicrowaveOvenSPL.MicrowaveOvenSPL.microwavegui.oven.images/Turntable.jpg")
    preferredSize = new Dimension(100, 100)
  }
  def top = new MainFrame {
    contents = imgTurntable

  }

  val p = new Panel {
    override def paint(g: Graphics2D) {
      //drawRotate(g, 50, 50, 45, "H")
      //g.drawImage(imgTurntable, 0, 0, this)
    }
    preferredSize = new Dimension(200, 200)
  }

  def drawRotate(g2d: Graphics2D, x: Double, y: Double, angle: Int, text: String) {
    for (i <- 1 to 8) {
      g2d.translate(x, y)
      g2d.rotate(Math.toRadians(angle * i))
      g2d.drawString(text, 0, 0);
      g2d.rotate(-Math.toRadians(angle * i));
      g2d.translate(-x, -y);
    }

  }

}