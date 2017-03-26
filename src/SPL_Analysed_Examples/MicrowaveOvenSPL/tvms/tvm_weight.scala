package SPL_Analysed_Examples.MicrowaveOvenSPL.tvms

import MicrowaveOvenSPL.oven._
import dsl._
import scala.reflect.runtime.universe._

/**
  * Created by Tërnava on 2/28/2017.
  */

object tvm_weight {

  val WeightSensor: OPT_VP        = OPT_VP(asset(typeOf[WeightSensor].typeSymbol))
  val BooleanWeight: Variant  = Variant(asset(typeOf[BooleanWeight.type].termSymbol))
  val AnalogWeight: Variant   = Variant(asset(typeOf[AnalogWeight.type].termSymbol))

  val Door                      = Variant(asset(typeOf[DoorStatus].typeSymbol))
  val Open: Variant             = Variant(asset(typeOf[Open.type].termSymbol))
  val Close: Variant            = Variant(asset(typeOf[Close.type].termSymbol))

  import module._

  val newVPDoor: tech_VP = Door.toTechnicalVP

  module("Weight.scala") {
    WeightSensor is ALT with_variants (BooleanWeight, AnalogWeight, Door) use
      INHERITANCE with_binding RUN_TIME and_evolution CLOSE
  }

  module("Door.scala") {
    newVPDoor is ALT with_variants(Open, Close) use
      INHERITANCE with_binding RUN_TIME and_evolution CLOSE
  }

}
