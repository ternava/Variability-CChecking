package SPL_Analysed_Examples.MicrowaveOvenSPL.tvms

import MicrowaveOvenSPL.oven._
import dsl._
import scala.reflect.runtime.universe._

/**
  * Created by Tërnava on 2/28/2017.
  */

object tvm_rotate {

  val Rotate: VP = VP(asset(typeOf[Rotate.type].termSymbol))

  import module._
  module("Rotate.scala") {
    Rotate is_(OPT)
  }

}
