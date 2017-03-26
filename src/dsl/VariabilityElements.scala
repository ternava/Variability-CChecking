package dsl

import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import scala.reflect.runtime.universe._
/**
  * Created by Tërnava on 6/5/2016.
  */

object VariabilityModel {

  /* The power-set: i.e., Variation Points with their respective variants.
  * (VPa, Technique_r) = {Vx, Vy, Vz, ...}; (VPb, Technique_s) = {VPm, VPn, VPo, ...} */
  import scala.collection.mutable.Map
  //val power_set: Map[(String, VariationPoint), (Map[Mechanism, (Logic, BindingTime, Evolve)], Seq[Variant])] = Map()
  val power_set: Map[VariationPoint, (Map[Mechanism, (Logic, BindingTime, Evolve)], Seq[Variant])] = Map()
  val power_set_2: ListBuffer[(VariationPoint, Mechanism, Logic, BindingTime, Evolve, Seq[Variant])] = new ListBuffer()
  val power_set_3: ListBuffer[(String, Mechanism, Logic, BindingTime, Evolve, ArrayBuffer[String])] = new ListBuffer()



  def add(//module: String,
          vp: VariationPoint,
          m: Mechanism,
          l: Logic,
          bt: BindingTime,
          e: Evolve,
          vs: Seq[Variant]): Unit = {

    //power_set += ((module, vp) -> (Map(m -> (l, bt, e)), vs))
    power_set += (vp -> (Map(m -> (l, bt, e)), vs))
    power_set_2 += ((vp, m, l, bt, e, vs))
    //power_set_3 += ((x_vp, m, l, bt, e, x_variant))
  }

  def get = power_set_2.toList


  def printAsMaps: Unit = {
    power_set foreach {
      case (key, value) =>  value match {
        case (x, y) =>
        // --- println(key + " : " + x + " -> " + y)
      }
    }
  }

  def printAsTuples: Unit = {
    power_set_2 foreach(println(_))
  }

}

/* One Variant or Variation Point can be represented in
different asset elements in code! */
case class Variant(val var_asset_elem: Symbol)

abstract class VariationPoint
trait Optional_vp extends VariationPoint
trait Mandatory_vp extends VariationPoint

case class VP(val var_asset_elem: Symbol) extends VariationPoint with Mandatory_vp

case class OPT_VP(val asset_elem: Symbol) extends VariationPoint with Optional_vp

case class nested_VP(val var_elem: Symbol) extends Mandatory_vp with Optional_vp

case class tech_VP(val var_elem: Symbol) extends Mandatory_vp with Optional_vp





