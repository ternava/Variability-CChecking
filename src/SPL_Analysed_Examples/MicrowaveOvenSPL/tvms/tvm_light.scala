package SPL_Analysed_Examples.MicrowaveOvenSPL.tvms

import MicrowaveOvenSPL.oven._
import dsl._
import scala.reflect.runtime.universe._

/**
  * Created by Tërnava on 2/28/2017.
  */

object tvm_light {

  val Light                  = VP(asset(typeOf[Light].typeSymbol))
  val LightOn: Variant       = Variant(asset(typeOf[LOn.type].termSymbol))
  val LightOff: Variant      = Variant(asset(typeOf[LOff.type].termSymbol))

  import module._
  module("Light.scala") {
    Light is ALT with_variants (LightOn, LightOff) use
      INHERITANCE with_binding RUN_TIME and_evolution CLOSE
  }

}
