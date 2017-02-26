package MicrowaveOvenSPL.configure

import MicrowaveOvenSPL.oven._

class ConfigureMicrowaveOven {

  val display = new DisplayFeature
  val language = new LanguageFeature
  val weightsensor = new WeightSensorFeature
  val temperature = new TemperatureFeature
  val light = new Light
  val beeper = new Beeper
  val minpluss = new MinutePlus
  val currentime = new CurrentTime

  /*configuration of Microwave Oven */

  /* feature "OneLineDisplay" is by default */
  display chosen MultiLineDisplay

  /* feature "English" is by default */
  language chosen Albanian

  /* feature "BooleanWeight" is by default */
  weightsensor weight false
  //weightsensor chosen AnalogWeight
  //weightsensor weight 1.2  

  /*  feature "OneLevelHeating" 
   *  with feature "High" is by default 
   *  ----
   *  NOTE: I haven't tested these!
   *  */

  /* optional feature, it can be "In" or "Out" */
  light featureGoes Out

  /* optional feature, it can be "In" or "Out" */
  beeper featureGoes In

  /* optional feature, it can be "In" or "Out" */
  minpluss featureGoes In

  /* optional feature, which require 
   * feature "MultiLineDisplay" and 
   * and vv isn't required */
  currentime featureGoes Out

}