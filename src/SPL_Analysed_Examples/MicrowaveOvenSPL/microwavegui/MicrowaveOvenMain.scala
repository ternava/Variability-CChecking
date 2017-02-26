package MicrowaveOvenSPL.microwavegui

import scala.swing._
import scala.swing.event._
import java.text.SimpleDateFormat
import java.util.Calendar._

import MicrowaveOvenSPL.microwavegui.Colors._
import MicrowaveOvenSPL.microwavegui.Components._
import MicrowaveOvenSPL.configure.{ConfigureMicrowaveOven, In}
import MicrowaveOvenSPL.microwavegui.MicrowaveFrame
import MicrowaveOvenSPL.oven._


object MicrowaveOven extends SimpleSwingApplication {

  //will get the configuration of MicrowaveOven
  val c = new ConfigureMicrowaveOven

  var doorstatus: DoorStatus = Open
  var item = c.weightsensor.itemOn

  def top = new MicrowaveFrame {

    var timeClickedOC = 0
    var rmt: Int = 0
    var minuteplus: Int = 0
    var cookingTimeIsSet = false
    btnStop.enabled = false
    var tt = 0

    reactions += {

      case ButtonClicked(x) => x.text match {
        case "start" => {
          doorstatus match {
            case Open => item match {
              case true => {
                txtDisplay.text = showMessage("M02")
              }
              case false => {
                txtDisplay.text = showMessage("M03")
              }
            }
            // case the door is Closed
            case _ => item match {
              case true => {
                if (tfCookingTime.text == "00:00") {
                  txtDisplay.text = showMessage("M04"); rmt
                } else {
                  timer.start

                  //when feature "Light" is IN
                  if (c.light.featureIsInOrOut == In) {
                    btnLight.foreground = yellow
                  }

                  btnStop.enabled = true
                  btnOpenClose.enabled = false
                  rmt = tfCookingTime.text.toInt

                  //when "MinutePlus" Feature is IN
                  if (c.minpluss.featureIsInOrOut == In) {
                    Timer.remainedtime = rmt + minuteplus
                  } else {
                    Timer.remainedtime = rmt
                  }

                  tfCookingTime.text = Timer.showTime()
                  txtDisplay.text = showMessage("M05")
                }
              }
              case false => {
                txtDisplay.text = showMessage("M06")
              }
            }
          }
        }
        case "O/C" => OpenClose

        //when "MinutePlus" Feature is IN  
        case "minute+" => {
          if (c.minpluss.featureIsInOrOut == In) {
            minuteplus = 60
            Timer.remainedtime += minuteplus
            minuteplus = 0
          }
        }

        case "set item" => doorstatus match {
          case Open => {
            if (cbxItem.selected) {
              btndoor.text = "DOOR OPENED WITH ITEM"
              txtDisplay.text = showMessage("M09")
              item = true
            } else {
              btndoor.text = "DOOR OPENED WITHOUT ITEM"
              txtDisplay.text = showMessage("M10")
              item = false
            }
          }
          case _ => {
            if (!cbxItem.selected) {
              cbxItem.selected = true
              btndoor.text = "DOOR CLOSED WITH ITEM"
              txtDisplay.text = showMessage("M11")
              item = true
            } else {
              cbxItem.selected = false
              btndoor.text = "DOOR CLOSED WITHOUT ITEM"
              txtDisplay.text = showMessage("M12")
              item = false
            }
          }
        }
        case "stop" => {
          btnOpenClose.enabled = true
          btnStop.enabled = false
          timer.stop
          tt = Timer.tmRemained()
          Timer.remainedtime = tt
          tfCookingTime.text = tt.toString()
          txtDisplay.text = showMessage("M07")
        }
      }
      // case _ => println("Nothing")
    }

    timer.tmr.addActionListener(Swing.ActionListener(e => {
      val t = Timer.tmRemained()

      tfRemainedTime.text = Timer.showTime()

      if (t == 0) {
        whenTimeStops()
      }
    }))

    def whenTimeStops() = {
      tfCookingTime.text = "00:00"
      txtDisplay.text = showMessage("M08")
      btnStart.enabled = true
      println("Time has finished")
      btnOpenClose.enabled = true
      timer.stop

      //"Light" Feature
      if (c.light.featureIsInOrOut == In) {
        btnLight.foreground = hiri
      }
      //"Beeper" Feature
      if (c.beeper.featureIsInOrOut == In) {
        BOn.sound
      }
    }

    def showMessage(msg: String): String = {
      c.display.msgToDisplay(c.language.displayMessage(msg)).mkString("\n")
    }
    def OpenClose {
      if (timeClickedOC % 2 != 0) {
        doorstatus = Close
        if (c.light.featureIsInOrOut == In) {
          btnLight.foreground = hiri
        }
        btndoor.text = "DOOR CLOSED"
        btndoor.background = plum
        timeClickedOC -= 1
      } else {
        doorstatus = Open
        if (c.light.featureIsInOrOut == In) {
          btnLight.foreground = yellow
        }
        btndoor.text = "DOOR OPENED"
        btndoor.background = lightbr
        txtDisplay.text = showMessage("M01")
        timeClickedOC += 1
      }
    }
  }

}
