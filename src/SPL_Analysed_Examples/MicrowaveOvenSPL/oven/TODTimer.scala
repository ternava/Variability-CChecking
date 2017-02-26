package MicrowaveOvenSPL.oven

import java.util.Calendar._
import java.text.SimpleDateFormat

import MicrowaveOvenSPL.configure.OptionalFeatures

object Time {
  private val timeFormat = new SimpleDateFormat("hh:mm:ss")
  def current = timeFormat.format(getInstance().getTime)
}

object TODTimer {
  def apply(interval: Int, repeat: Boolean = true)(op: () => Unit) {
    val timeOut = new javax.swing.AbstractAction() {
      def actionPerformed(e: java.awt.event.ActionEvent) = op()
    }
    val tmr = new javax.swing.Timer(interval, timeOut)
    tmr.setRepeats(repeat)
    tmr.start()
  }
}

class CurrentTime extends OptionalFeatures {

  var lasttime = Time.current
  def tick() {
    val newtime = Time.current
    if (newtime != lasttime) {
      lasttime = newtime
    }
  }
  //Timer(1000) { () => tick(); println(lastTime) }

}
