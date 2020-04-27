package math.dsl_old

import scala.collection.mutable.ArrayBuffer
import scala.reflect.runtime.universe._
/**
  * Created by ternava on 6/5/2016.
  */
object VariabilityModel {
  /* The power-set: i.e., Variation Points with their respective variants.
  * (VPa, Technique_r) = {Vx, Vy, Vz, ...}; (VPb, Technique_s) = {VPm, VPn, VPo, ...} */
  import scala.collection.mutable.Map
  //val power_set: Map[(String, VariationPoint), (Map[Mechanism, (Logic, BindingTime, Evolve)], Seq[Variant])] = Map()
  val power_set: Map[VariationPoint,
    (Map[Mechanism, (Logic, BindingTime, Evolve)], Seq[Variant])] = Map()
  def add(//module: String,
          vp: VariationPoint,
          m: Mechanism,
          l: Logic,
          bt: BindingTime,
          e: Evolve,
          vs: Seq[Variant]): Unit = {

    //power_set += ((module, vp) -> (Map(m -> (l, bt, e)), vs))
    power_set += (vp -> (Map(m -> (l, bt, e)), vs))

  }

  /* The set of Variation Points VP = {VPa, VPb, VPc, ...}
  or, it can be the set of "decisions".   */
 //val variation_points: Seq[VariationPoint] = Seq()

}
/* One Variant or Variation Point can be represented in
different asset elements in code! */
case class Variant(var_asset_elem: Symbol*)


case class VariationPoint(val var_asset_elem: Symbol*)

/*
//Variation Points can be implicit. In those cases we create a VP!?
class VariationPoint[T](val var_asset_elem: T)

object VariationPoint {
  /*def apply(var_asset_elem: String*): VariationPoint[Seq[String]] = new VariationPoint[Seq[String]](var_asset_elem)
  def apply(var_asset_elem: Symbol*): VariationPoint[Seq[Symbol]] = new VariationPoint[Seq[Symbol]](var_asset_elem)*/
  def apply(var_asset_elem: String): VariationPoint[String] = new VariationPoint[String](var_asset_elem)
  def apply(var_asset_elem: Symbol): VariationPoint[Symbol] = new VariationPoint[Symbol](var_asset_elem)
}

*/






