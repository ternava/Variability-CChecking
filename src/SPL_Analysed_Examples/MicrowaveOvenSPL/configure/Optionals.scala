package MicrowaveOvenSPL.configure

sealed trait Feature
case object In extends Feature
case object Out extends Feature

trait OptionalFeatures {

  //by default the Optional Feature is Out 
  private var feature: Feature = Out

  def featureGoes(inout: Feature) = feature = inout

  def featureIsInOrOut = feature

}