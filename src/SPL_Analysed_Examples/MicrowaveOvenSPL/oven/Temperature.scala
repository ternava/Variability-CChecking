package MicrowaveOvenSPL.oven

import dsl_variability_documenting._

object Variability_ModuleE {
    import scala.reflect.runtime.universe._
    import scala.collection.mutable.ArrayBuffer
    val asset = new Asset
   /*=================================================================================*/
    val assetTemperature        = asset.tag(typeOf[Temperature].typeSymbol)
    val assetHigh               = asset.tag(typeOf[High.type].termSymbol)
    val assetMedium             = asset.tag(typeOf[Medium.type].termSymbol)
    val assetLow                = asset.tag(typeOf[Low.type].termSymbol)
    val assetHeatingElement     = asset.tag(typeOf[HeatingElement].typeSymbol)
    val assetOneLevelHeating    = asset.tag(typeOf[OneLevelHeating.type].termSymbol)
    val assetMultiLevelHeating  = asset.tag(typeOf[MultiLevelHeating.type].termSymbol)

    val vp_temperature          = VariationPoint(assetTemperature)
    val vp_heatingelement       = VariationPoint(assetHeatingElement)
    val high                    = Variant(assetHigh)
    val medium                  = Variant(assetMedium)
    val low                     = Variant(assetLow)
    val onelevelheating         = Variant(assetOneLevelHeating)
    val multilevelheating       = Variant(assetMultiLevelHeating)
   /*=================================================================================*/
   /*=================================================================================*/
    val variability_module_e = {
      vp_temperature      isA     ArrayBuffer(high, medium, low)
      vp_heatingelement   isA     ArrayBuffer(onelevelheating, multilevelheating)
      vp_temperature      hasA    medium

      vp_heatingelement requires vp_temperature
    }
   /*=================================================================================*/
}

/* Inner - technical Variation Point with variants!
 * - not shown in the specification level. */
sealed trait Temperature
case object High extends Temperature
case object Medium extends Temperature
case object Low extends Temperature

/* Variation Point with two Variants coming from
 * the specification level - Features in Feature Model*/

// Variation Point
sealed trait HeatingElement

// One variant
case object OneLevelHeating extends HeatingElement

// Another variant
case object MultiLevelHeating extends HeatingElement

/* The configuration class for variants! */
class TemperatureFeature {

  /* The default feature of Temperature */
  private var temperature: Temperature = High

  private def chosenTemperature(temp: Temperature) = temperature = temp
 
  /* The default feature of Heating */
  private var heatingelement: HeatingElement = OneLevelHeating

  def chooseHeatingElement(heat: HeatingElement) = heatingelement = heat

  def heat(heatlevel: Temperature) = heatingelement match {
    case OneLevelHeating => temperature
    case MultiLevelHeating => chosenTemperature(heatlevel); temperature
  }

}

