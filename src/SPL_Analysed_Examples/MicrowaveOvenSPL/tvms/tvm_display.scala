package SPL_Analysed_Examples.MicrowaveOvenSPL.tvms

import MicrowaveOvenSPL.oven._
import dsl._
import scala.reflect.runtime.universe._

/**
  * Created by Tërnava on 2/28/2017.
  */

object tvm_display {

  val Display                       = VP(asset(typeOf[DisplayUnit].typeSymbol))
  val OneLineDisplay1: Variant       = Variant(asset(typeOf[OneLineDisplay.type].termSymbol))
  val MultiLineDisplay: Variant     = Variant(asset(typeOf[MultiLineDisplay.type].termSymbol))

  import module._
  module("Display.scala") {
    Display is ALT with_variants (OneLineDisplay1, MultiLineDisplay) use
      INHERITANCE with_binding RUN_TIME and_evolution CLOSE
  }


}
