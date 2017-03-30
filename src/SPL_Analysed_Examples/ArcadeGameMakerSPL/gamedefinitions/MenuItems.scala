package SPL_Analysed_Examples.ArcadeGameMakerSPL.gamedefinitions

import swing._
import event._
import gameboard.GameBoardImpl._

object MenuItems {
  val start = new MenuItem("New Game") {
    mnemonic = Key.S
    action = new Action("New Game") {
      def apply() {
        score = 0
        timer.start()
      }
    }
  }
  val pause = new MenuItem("Pause") {
    mnemonic = Key.P
    action = new Action("Pause") {
      def apply() {
        timer.stop()
      }
    }
  }
  val exit = new MenuItem("Exit") {
    mnemonic = Key.Escape
    action = new Action("Exit") {
      def apply() {
        sys.exit()
      }
    }
  }
}