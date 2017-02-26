package MicrowaveOvenSPL.microwavegui

import java.awt.{ Color => aColor }
import java.awt.Font
import scala.swing.Swing
import java.awt.{Color => aColor}

object Colors {
  val bluish = new aColor(32, 178, 170)
  val brownLight = new aColor(140, 137, 132)
  val magenta = new aColor(255, 0, 255)
  val indigo = new aColor(75, 0, 130)
  val white = new aColor(255, 255, 255)
  val black = new aColor(0, 0, 0)
  val orange = new aColor(255, 102, 0)
  val yellow = new aColor(255, 255, 0)
  val hiri = new aColor(102, 102, 102)
  val blue = new aColor(0, 51, 102)
  val violet = new aColor(238, 130, 238)
  val plum = new aColor(221, 160, 221)
  val lightbr = new aColor(255, 255, 153)
  val pink = new aColor(216,191,216)
}

object Fonts {
  val thefont = new Font("Verdana", Font.BOLD, 14)
  val tafont = new Font("Verdana", Font.CENTER_BASELINE, 11)
  val bluishborder = Swing.LineBorder(Colors.bluish)
}