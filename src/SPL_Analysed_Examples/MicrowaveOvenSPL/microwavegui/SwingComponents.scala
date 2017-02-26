package MicrowaveOvenSPL.microwavegui

import scala.swing._
import scala.swing.event._
import java.text.SimpleDateFormat
import java.util.Calendar._
import Colors._
import Fonts._
import javax.swing.ImageIcon

object Components {

  val btndoor = button("DOOR")
  btndoor.preferredSize = new Dimension(300, 200)
  btndoor.background = plum

  val txtDisplay = new TextArea {
    columns = 20; rows = 2
    editable = false
    border = bluishborder
    //lineWrap = true
    //wordWrap = true
  }
  val scrollpane = new ScrollPane(txtDisplay)
  scrollpane.preferredSize = new Dimension (40, 50)
  val btnStart = button("start")
  val btnStop = button("stop")
  val btnMinutePlus = button("minute+")
  val btnItem = button("Item") //make as a checkBox to show when the item is IN or OUT!
  val btnOpenClose = button("O/C") //can be built separately each button
  val btnLight = new RoundedButton
  val btnPowerLevel = button("p")
  val btnRecipe = button("r")
  val cbxItem = new CheckBox("set item") {
    selected = false
  }
  val txaTurntable = new RotateTurntable

  object tfCookingTime extends TextField {
    text = "00:00"
    columns = 5
    font = tafont
    border = bluishborder
  }
  object tfRemainedTime extends TextField {
    text = "00:00"
    columns = 5
    font = tafont
    border = bluishborder
    editable = false
    background = lightbr
  }
  
  val imgTurntable = new Label {
     icon = new ImageIcon("/Turntable.tif")
     preferredSize = new Dimension(30, 30)
  }

  private def button(x: String): Button = new Button() {
    text = x
  }

}

class RoundedButton extends Button {
  contentAreaFilled = false

  override def paint(g: Graphics2D) {
    g.fillOval(0, 0, 20, 20)
    g setColor black
    g.drawOval(0, 0, 20, 20)
  }
}

class RotateTurntable extends Button {
  contentAreaFilled = false
  
  override def paint(g: Graphics2D) {
    g setColor yellow
    g.fillOval(0, 0, 20, 20)
    g setColor black
    drawRotate(g, 50, 50, 45, "H")
  }
  def drawRotate(g2: Graphics2D, x: Double, y: Double, angle: Int, text: String) {
    for (i <- 1 to 8) {
	    g2.translate(x, y)
	    g2.rotate(Math.toRadians(angle * i))
	    g2.drawString(text, 0, 0)
	    g2.rotate(-Math.toRadians(angle * i))
	    g2.translate(-x, -y)
    }
  }
}