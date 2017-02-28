package SPL_Analysed_Examples.MicrowaveOvenSPL.tvms

import MicrowaveOvenSPL.oven.Beeper
import dsl._
import scala.reflect.runtime.universe._

/**
  * Created by Tërnava on 2/28/2017.
  */

object tvm_beeper {

  val Beeper             = VP(asset(typeOf[Beeper].typeSymbol))
  val BOn: Variant       = Variant(asset(typeOf[BOn.type].termSymbol))
  val BOff: Variant      = Variant(asset(typeOf[BOff.type].termSymbol))

  import module._
  module("Beeper.scala") {
    Beeper is MUL with_variants(BOn, BOff) use
      INHERITANCE with_binding RUN_TIME and_evolution CLOSE
  }

}
