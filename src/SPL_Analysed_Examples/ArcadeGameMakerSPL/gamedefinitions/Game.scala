package SPL_Analysed_Examples.ArcadeGameMakerSPL.gamedefinitions

import scala.swing._

trait Game {
    val menuBarSample = new MenuBar {
    contents += new Menu("Options") {
      contents ++= Seq(MenuItems.start,
        MenuItems.pause,
        MenuItems.exit)
    }
  }
}
