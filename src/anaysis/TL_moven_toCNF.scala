package anaysis

/**
  * Created by Tërnava on 2/28/2017.
  */
import scala.collection.mutable._

object traces {

  val traceLinks: Map[Int, Int] = Map()

  def apply(vp: Int): FeatureBuilder = new FeatureBuilder(vp)
  implicit def feature2FeatureBuilder(vp: Int) = new FeatureBuilder(vp)

}

class FeatureBuilder(vp: Int) {
  def implements(f: Int) = {
    traces.traceLinks(vp) = f
    vp
  }
}

object TL_moven_toCNF {

  val feature: Map[String, Int] = Map()
  val vp: Map[String, Int] = Map()

  def copyMap(from: Map[String, Int], to: Map[String, Int]) = {
    for((k,v) <- from) {
      to += ((k,v))
    }
  }




  def main(args: Array[String]): Unit = {


    FM_moven_toCNF.mainFM(args(0))
    TVM_moven_toCNF.mainTVM()

    copyMap(FM_moven_toCNF.map, feature)
    copyMap(TVM_moven_toCNF.map, vp)

    // debugging stuff...
    println(feature)
    println(vp)

    println("One value from the MAP: " + feature("f_BooleanWeight"))
    println("Another value from the MAP: " + vp("DoorStatus"))

    import traces._
    traces {
      vp("DoorStatus") implements feature("f_DoorSensor")
    }

    println("Trace Links: " + traceLinks)

  }

}
