package SPL_Analysed_Examples.MicrowaveOvenSPL.tvms

import MicrowaveOvenSPL.oven._
import dsl._
import scala.reflect.runtime.universe._

/**
  * Created by Tërnava on 2/28/2017.
  */

object tvm_weight {

  val WeightSensor            = VP(asset(typeOf[WeightSensor].typeSymbol))
  val BooleanWeight: Variant  = Variant(asset(typeOf[BooleanWeight.type].termSymbol))
  val AnalogWeight: Variant   = Variant(asset(typeOf[AnalogWeight.type].termSymbol))

  import module._
  module("Weight.scala") {
    WeightSensor is ALT with_variants (BooleanWeight, AnalogWeight) use
      INHERITANCE with_binding RUN_TIME and_evolution CLOSE
  }

}
