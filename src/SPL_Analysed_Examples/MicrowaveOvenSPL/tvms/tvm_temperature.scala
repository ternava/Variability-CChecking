package SPL_Analysed_Examples.MicrowaveOvenSPL.tvms

import MicrowaveOvenSPL.oven.{HeatingElement, Temperature}
import dsl._
import scala.reflect.runtime.universe._

/**
  * Created by Tërnava on 2/28/2017.
  */

object tvm_temperature {

  val Temperature                        = Variant(asset(typeOf[Temperature].typeSymbol))
  val High: Variant                      = Variant(asset(typeOf[High.type].termSymbol))
  val Medium: Variant                    = Variant(asset(typeOf[Medium.type].termSymbol))
  val Low: Variant                       = Variant(asset(typeOf[Low.type].termSymbol))

  val HeatingElement                     = VP(asset(typeOf[HeatingElement].typeSymbol))
  val OneLevelHeating: Variant           = Variant(asset(typeOf[OneLevelHeating.type].termSymbol))
  val MultiLevelHeating: Variant         = Variant(asset(typeOf[MultiLevelHeating.type].termSymbol))

  import module._

  val newVPTemperature: tech_VP = Temperature.toTechnicalVP

  module("Temperature.scala") {

    HeatingElement is ALT with_variants
      (OneLevelHeating, MultiLevelHeating, Temperature) use
      INHERITANCE with_binding
      RUN_TIME and_evolution OPEN



    newVPTemperature is ALT with_variants
      (High, Medium, Low) use
      INHERITANCE with_binding
      RUN_TIME and_evolution CLOSE
  }

}
