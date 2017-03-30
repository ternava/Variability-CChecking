package SPL_Analysed_Examples.ArcadeGameMakerSPL.gamedefinitions

import javax.swing.Timer

class ScalaTimer(val delay: Int) {
	var timerS: Boolean = false
    val tmr: Timer = new Timer(delay, null)
    def start() = {
	  timerS = true
	  tmr.start()
	}
    def stop() = {
      timerS = false
      tmr.stop()
    }
}