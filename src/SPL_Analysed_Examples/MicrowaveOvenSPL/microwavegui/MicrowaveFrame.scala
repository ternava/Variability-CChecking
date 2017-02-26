package MicrowaveOvenSPL.microwavegui

import scala.swing._
import event._
import Components._

class MicrowaveFrame extends MainFrame {

  title = "Microwave Oven"
  preferredSize = new Dimension(530, 250)

  //Ticks every second
  val timer = new ScalaTimer(1000)

  listenTo(btnStart, btnOpenClose, cbxItem, btnStop, btnMinutePlus)

  contents = new MicrowavePanel

  centerOnScreen()
  resizable = false

}