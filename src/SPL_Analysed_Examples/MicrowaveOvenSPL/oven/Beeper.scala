package MicrowaveOvenSPL.oven

import MicrowaveOvenSPL.configure.OptionalFeatures

/*import dsl._
import dsl.module._
import scala.reflect.runtime.universe._

object variability_a {

  val Beeper             = VariationPoint(asset(typeOf[Beeper].typeSymbol))
  val BOn: Variant       = Variant(asset(typeOf[BOn.type].termSymbol))
  val BOff: Variant      = Variant(asset(typeOf[BOff.type].termSymbol))

  module("Beeper.scala") {
    Beeper is ALT with_variants (BOn, BOff) use
      INHERITANCE with_binding RUN_TIME and_evolution CLOSE
  }

} */




sealed class Beeper extends OptionalFeatures
case object BOn extends Beeper {
  def sound {
    for (i <- 1 to 3) {
      Thread.sleep(1000)
      java.awt.Toolkit.getDefaultToolkit.beep()
      println("nothing")
    }
  }
}
case object BOff extends Beeper