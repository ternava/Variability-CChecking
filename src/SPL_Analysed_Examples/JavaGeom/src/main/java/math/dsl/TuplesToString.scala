package math.dsl

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Tërnava on 2/25/2017.
  */
object TuplesToList {


  def apply(lstTuples: List[(VariationPoint, Mechanism, Logic, BindingTime, Evolve, Seq[Variant])]):
      List[(String, String, String, String, String, ArrayBuffer[String], String)] = {

    val buff2: ArrayBuffer[(String, String, String, String, String, ArrayBuffer[String], String)] = ArrayBuffer()
    for (t <- lstTuples)  {
      t match {
        case (VP(a), b: Mechanism, c: Logic, d: BindingTime, e: Evolve, f : mutable.WrappedArray[Variant]) => {
          val buff3: ArrayBuffer[String] = ArrayBuffer()
          for (u <- f) u match {
            case Variant(x) => buff3 += x.fullName.split("\\.").last
          }
          buff2 += ((a.fullName.split("\\.").last, b.toString, c.toString, d.toString, e.toString, buff3, "MND"))
        }
        case (OPT_VP(a), b: Mechanism, c: Logic, d: BindingTime, e: Evolve, f : mutable.WrappedArray[Variant]) => {
          val buff3: ArrayBuffer[String] = ArrayBuffer()
          for (u <- f) u match {
            case Variant(x) => buff3 += x.fullName.split("\\.").last
          }
          buff2 += ((a.fullName.split("\\.").last, b.toString, c.toString, d.toString, e.toString, buff3, "OPT"))
        }
        case (nested_VP(a), b: Mechanism, c: Logic, d: BindingTime, e: Evolve, f : mutable.WrappedArray[Variant]) => {
          val buff3: ArrayBuffer[String] = ArrayBuffer()
          for (u <- f) u match {
            case Variant(x) => buff3 += x.fullName.split("\\.").last
          }
          buff2 += ((a.fullName.split("\\.").last, b.toString, c.toString, d.toString, e.toString, buff3, "NESTED"))
        }
        case (tech_VP(a), b: Mechanism, c: Logic, d: BindingTime, e: Evolve, f : mutable.WrappedArray[Variant]) => {
          val buff3: ArrayBuffer[String] = ArrayBuffer()
          for (u <- f) u match {
            case Variant(x) => buff3 += x.fullName.split("\\.").last
          }
          buff2 += ((a.fullName.split("\\.").last, b.toString, c.toString, d.toString, e.toString, buff3, "TECHNICAL"))
        }
        case (VP(a), null, c: Logic, null, null, null) => {
          val buff3: ArrayBuffer[String] = ArrayBuffer()
          buff2 += ((a.fullName.split("\\.").last, "", c.toString, "", "", buff3, ""))
        }

      }
    }
    // --- println("Bufferi 2 eshte: " + buff2.toList)
    buff2.toList
  }

}
