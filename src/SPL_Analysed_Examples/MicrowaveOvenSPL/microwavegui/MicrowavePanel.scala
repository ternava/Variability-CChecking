package MicrowaveOvenSPL.microwavegui

import scala.swing._
import scala.swing.event._
import Components._
import Fonts._
import Colors._
import MicrowaveOvenSPL.configure.In

import GridBagPanel.Fill._

class MicrowavePanel extends GridBagPanel {

  private def constraints(x: Int, y: Int, gridwidth: Int = 1, gridheight: Int = 1,
    weightx: Double = 0.0, weighty: Double = 0.0,
    fill: Value = Horizontal): Constraints = {
    val cnstr = new Constraints
    cnstr.gridx = x
    cnstr.gridy = y
    cnstr.gridwidth = gridwidth
    cnstr.gridheight = gridheight
    cnstr.weightx = weightx
    cnstr.weighty = weighty
    cnstr.fill = fill
    cnstr
  }

  add(new Label("MWV") {
    font = thefont
    foreground = violet
  },
    constraints(1, 0, gridwidth = 4, gridheight = 1, weightx = 1.0, weighty = 1.0))
  add(btnOpenClose,
    constraints(4, 0))
  add(btndoor,
    constraints(0, 0, gridheight = 8, fill = Both))
  add(scrollpane,
    constraints(1, 1, gridwidth = 5, weightx = 1.0))
  add(new Label("Set Time: "),
    constraints(2, 3, gridwidth = 2))
  add(tfCookingTime,
    constraints(4, 3))
  add(new Label("Remained: "),
    constraints(2, 2, gridwidth = 2))
  add(tfRemainedTime,
    constraints(4, 2))
  add(btnStart,
    constraints(1, 4, gridwidth = 1))
  add(btnStop,
    constraints(1, 5, gridwidth = 1))
    
 //when "MinutePlus" feature is IN
 if(MicrowaveOven.c.minpluss.featureIsInOrOut == In) {
  add(btnMinutePlus,
    constraints(1, 6, gridwidth = 1))
  }
  
  add(cbxItem,
    constraints(1, 7, gridwidth = 1))
    
  //when "Light" feature is IN
  if(MicrowaveOven.c.light.featureIsInOrOut == In) {
    add(btnLight,
        constraints(1, 2, gridwidth = 1, gridheight = 2, fill = Vertical))
  }
  
      add(btnRecipe, constraints(3, 7))
      add(btnPowerLevel, constraints(4, 7))
      //add(txaTurntable, constraints(3, 7))
  	  //add(imgTurntable, constraints(3, 7, gridwidth = 1, gridheight = 2, fill = Both))
      add(button("1"), constraints(2, 4))
      add(button("2"), constraints(3, 4))
      add(button("3"), constraints(4, 4))
      add(button("4"), constraints(2, 5))
      add(button("5"), constraints(3, 5))
      add(button("6"), constraints(4, 5))
      add(button("7"), constraints(2, 6))
      add(button("8"), constraints(3, 6))
      add(button("9"), constraints(4, 6))
      add(button("0"), constraints(2, 7))
      
  private def button(x: String): Button = new Button() {
    text = x
  }

}