package MicrowaveOvenSPL.oven

import MicrowaveOvenSPL.microwavegui.MicrowaveOven._
import MicrowaveOvenSPL.configure._

sealed trait DisplayUnit
case object OneLineDisplay extends DisplayUnit
case object MultiLineDisplay extends DisplayUnit

class DisplayFeature {

  // The default feature of DisplayUnit
  private var displayunit: DisplayUnit = OneLineDisplay
  def kindOfDisplay = displayunit

  def chosen(disp: DisplayUnit) = displayunit = disp

  def msgToDisplay(msg: String): List[String] = displayunit match {

    case OneLineDisplay => wordWrap(msg, 10000000)
    case MultiLineDisplay => {
      c.currentime.featureIsInOrOut match {
        case In => {
          TODTimer(1000) {
            () => c.currentime.tick()
          }
          wordWrap(c.currentime.lasttime + " - " + msg, 35)
        }
        case _ => wordWrap(msg, 35)
      }
    }
  }

  def wordWrap(s: String, n: Int) = s.split("\\s").foldLeft(List[String]()) {
    (lines, word) =>
      if (lines.isEmpty || lines.head.length + word.length + 1 > n)
        word :: lines
      else
        (lines.head + " " + word) :: lines.tail
  }
    .reverse

}