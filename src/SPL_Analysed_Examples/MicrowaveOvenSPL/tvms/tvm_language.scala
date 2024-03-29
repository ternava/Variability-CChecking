package SPL_Analysed_Examples.MicrowaveOvenSPL.tvms

import MicrowaveOvenSPL.oven.Language
import dsl._
import scala.reflect.runtime.universe._

/**
  * Created by Tërnava on 2/28/2017.
  */
object tvm_language {

  val Language: VP    = VP(asset(typeOf[Language].typeSymbol))
  val English: Variant            = Variant(asset(typeOf[English.type].termSymbol))
  val French: Variant             = Variant(asset(typeOf[French.type].termSymbol))
  val Italian: Variant            = Variant(asset(typeOf[Italian.type].termSymbol))
//  val Albanian: Variant           = Variant(asset(typeOf[Albanian.type].termSymbol))
  val German: Variant             = Variant(asset(typeOf[German.type].termSymbol))
  val Spanish: Variant            = Variant(asset(typeOf[Spanish.type].termSymbol))

  import module._
  module("Language.scala") {
    Language is ALT with_variants (English, French, Italian, Spanish, German) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN
  }

}
