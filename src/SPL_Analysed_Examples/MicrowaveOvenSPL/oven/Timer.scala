package MicrowaveOvenSPL.oven

object Timer {

  var remainedtime: Int = 0
  def tmRemained() = {
    remainedtime -= 1; remainedtime
  }
  def showTime() = {
    var minutes = remainedtime / 60
    var seconds = remainedtime % 60

    minutes match {
      case x if x < 10 => seconds match {
        case y if y < 10 => "0" + minutes + ":" + "0" + seconds
        case _ => "0" + minutes + ":" + seconds
      }
      case _ => seconds match {
        case y if y < 10 => minutes + ":" + "0" + seconds
        case _ => minutes + ":" + seconds
      }
    }
  }

}