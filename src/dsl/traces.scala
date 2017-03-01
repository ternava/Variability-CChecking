package dsl

import specification_level.feature

import scala.reflect.runtime.universe._
import scala.collection.mutable.{ArrayBuffer, Map}

/**
  * Created by Tërnava on 3/1/2017.
  */

object traces {

  val links: Map[feature, Symbol] = Map()
  val lst1: ArrayBuffer[String] = new ArrayBuffer()
  val mapping_links: Map[Int, Int] = Map()

  def apply(vp: VP): FeatureBuilder = new FeatureBuilder(vp)
  def apply(v: Variant): FeatureBuilder2 = new FeatureBuilder2(v)

  implicit def feature2FeatureBuilder(vp: VP) = new FeatureBuilder(vp)
  implicit def feature2FeatureBuilder(v: Variant) = new FeatureBuilder2(v)


  def pairsmap(f: Map[String, Int], vs: Map[String, Int]): Unit = {
    for((k,v) <- links) {
      println((k.name,v))
      if(f.contains(k.name) & vs.contains(v.toString.split(" ").last)) {
        println("Value is: ("  + f(k.name), vs(v.toString.split(" ").last))
        mapping_links += ((f(k.name), vs(v.toString.split(" ").last)))
      }
    }
  }

}

class FeatureBuilder(vp: VP) {
  def implements(f: feature) = {
    traces.links(f) = vp.var_asset_elem
    traces.lst1 += vp.var_asset_elem.toString.split(" ").last
    vp
  }
}
class FeatureBuilder2(v: Variant) {
  def implements(f: feature) = {
    traces.links(f) = v.var_asset_elem
    traces.lst1 += v.var_asset_elem.toString.split(" ").last
    v
  }
}