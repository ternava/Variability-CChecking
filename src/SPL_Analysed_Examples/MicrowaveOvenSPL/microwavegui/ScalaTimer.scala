package MicrowaveOvenSPL.microwavegui

class ScalaTimer(val delay: Int) {
  val tmr: javax.swing.Timer = new javax.swing.Timer(delay, null)
  def start() = tmr.start()
  def stop() = tmr.stop()
}