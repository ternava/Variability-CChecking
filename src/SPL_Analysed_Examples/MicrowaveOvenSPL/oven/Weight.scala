package MicrowaveOvenSPL.oven

sealed trait WeightSensor

case object BooleanWeight extends WeightSensor

case object AnalogWeight extends WeightSensor

/*The configuration class*/
class WeightSensorFeature {

  //The default feature of Weight
  private var weightsensor: WeightSensor = BooleanWeight

  var itemOn: Boolean = false

  def chosen(wsensor: WeightSensor) = weightsensor = wsensor

  var doesitweight: Any = _

  def weight(itsweight: Any) = weightsensor match {
    case BooleanWeight => itsweight match {
      case _: Boolean =>
        doesitweight = itsweight; doesitweight; //itemOn = true
      case _ => println("unexpected weight type")
    }
    case AnalogWeight => itsweight match {
      case _: Double =>
        doesitweight = itsweight; doesitweight; //itemOn = true
      case _: Int =>
        doesitweight = itsweight; doesitweight; //itemOn = true
      case _ => println("unexpected weight type")
    }
  }

}