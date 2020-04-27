package math.dsl_old

/**
  * Created by ternava on 6/5/2016.
  */

//import javaT.{JSmth, MountainBike}

//import math.geom2d._
//import math.geom2d.line._


import math.geom2d.AffineTransform2D

import scala.reflect.runtime.universe._

//import javaT.JSmth._
import java.math._

object Main {

  val vp_AffineTransoform2D: VariationPoint = VariationPoint(asset(typeOf[AffineTransform2D].typeSymbol))

 // val vp_StraightCurve2D: VariationPoint = VariationPoint(asset(typeOf[AbstractLine2D].typeSymbol))



 // val bicycle: VariationPoint = VariationPoint(asset(typeOf[JSmth].typeSymbol))
 // val mountainbike: Variant = Variant(asset(typeOf[MountainBike].typeSymbol))
  //val gear: Variant = Variant(asset(typeOf[JSmth].member(TermName("setGear"))))


   /* val Language: VariationPoint    = VariationPoint(asset(typeOf[Language].typeSymbol))
    val English: Variant            = Variant(asset(typeOf[English.type].termSymbol))
    val French: Variant             = Variant(asset(typeOf[French.type].termSymbol))
    val Italian: Variant            = Variant(asset(typeOf[Italian.type].termSymbol))
    val Albanian: Variant           = Variant(asset(typeOf[Albanian.type].termSymbol))
    val German: Variant             = Variant(asset(typeOf[German.type].termSymbol))
    val Spanish: Variant            = Variant(asset(typeOf[Spanish.type].termSymbol))

    val Door                      = VariationPoint(asset(typeOf[DoorStatus].typeSymbol))
    val Open: Variant             = Variant(asset(typeOf[Open.type].termSymbol))
    val Close: Variant            = Variant(asset(typeOf[Close.type].termSymbol))

    val Temperature                        = VariationPoint(asset(typeOf[Temperature].typeSymbol))
    val High: Variant                      = Variant(asset(typeOf[High.type].termSymbol))
    val Medium: Variant                    = Variant(asset(typeOf[Medium.type].termSymbol))
    val Low: Variant                       = Variant(asset(typeOf[Low.type].termSymbol))
    val HeatingElement                     = VariationPoint(asset(typeOf[HeatingElement].typeSymbol))
    val OneLevelHeating: Variant           = Variant(asset(typeOf[OneLevelHeating.type].termSymbol))
    val MultiLevelHeating: Variant         = Variant(asset(typeOf[MultiLevelHeating.type].termSymbol))

    val Beeper    = VariationPoint(asset(typeOf[Beeper].typeSymbol))
    val BOn: Variant       = Variant(asset(typeOf[BOn.type].termSymbol))
    val BOff: Variant      = Variant(asset(typeOf[BOff.type].termSymbol))

    val Display                       = VariationPoint(asset(typeOf[DisplayUnit].typeSymbol))
    val OneLineDisplay: Variant       = Variant(asset(typeOf[OneLineDisplay.type].termSymbol))
    val MultiLineDisplay: Variant     = Variant(asset(typeOf[MultiLineDisplay.type].termSymbol))

    val Light                  = VariationPoint(asset(typeOf[Light].typeSymbol))
    val LightOn: Variant       = Variant(asset(typeOf[LOn.type].termSymbol))
    val LightOff: Variant      = Variant(asset(typeOf[LOff.type].termSymbol))

    val Rotate: VariationPoint = VariationPoint(asset(typeOf[Rotate.type].termSymbol))

    val WeightSensor            = VariationPoint(asset(typeOf[WeightSensor].typeSymbol))
    val BooleanWeight: Variant  = Variant(asset(typeOf[BooleanWeight.type].termSymbol))
    val AnalogWeight: Variant   = Variant(asset(typeOf[AnalogWeight.type].termSymbol))

    //val Une = VariationPoint("Une")
*/

    def main(args: Array[String]): Unit = {

      println(vp_AffineTransoform2D)

      //todo: technical variability should be added! - if there is any.
      //todo: nested variability should be added! - if there is any.
      //todo: optional variability should be added! - if there is any.

      import module._
      //module("JSmth.java") {
      //bicycle is ALT with_variants(mountainbike, gear) use INHERITANCE with_binding RUN_TIME and_evolution OPEN

      //}


      /* module("Language.scala") {
        Language is ALT with_variants
          (English, French, Albanian,
              Italian, German, Spanish) use
          INHERITANCE with_binding
              RUN_TIME and_evolution OPEN
      }


      module("Beeper.scala") {
          Beeper is ALT with_variants (BOn, BOff) use
            INHERITANCE with_binding RUN_TIME and_evolution CLOSE
      }

      module("Door.scala") {
          Door is ALT with_variants(Open, Close) use
          INHERITANCE with_binding RUN_TIME and_evolution CLOSE
      }

      module("Display.scala") {
          Display is ALT with_variants (OneLineDisplay, MultiLineDisplay) use
            INHERITANCE with_binding RUN_TIME and_evolution CLOSE
      }


      module("Light.scala") {
        Light is ALT with_variants (LightOn, LightOff) use
            INHERITANCE with_binding RUN_TIME and_evolution CLOSE
      }
      import module._

      module("Rotate.scala") {
        Rotate is_ OPT
      }

      import module._

      module("Temperature.scala") {

        HeatingElement is ALT with_variants
          (OneLevelHeating, MultiLevelHeating) use
              INHERITANCE with_binding
                  RUN_TIME and_evolution OPEN

        Temperature is ALT with_variants
          (High, Medium, Low) use
              INHERITANCE with_binding
                  RUN_TIME and_evolution CLOSE
      }

      module("Weight.scala") {
          WeightSensor is ALT with_variants (BooleanWeight, AnalogWeight) use
            INHERITANCE with_binding RUN_TIME and_evolution CLOSE
      }

      import dsl.features_spec.traces
      import dsl.features_spec.Features._
      import traces._

      traces {

        Language implements f_DisplayLanguage
        English implements f_DefaultLanguage
        Seq(Temperature, HeatingElement) implements
          f_HeatingLevel

      }


*/

    }
}