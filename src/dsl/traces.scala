package dsl

import specification_level.feature

import scala.reflect.runtime.universe._
import scala.collection.mutable.{ArrayBuffer, Map}

/**
  * Created by Tërnava on 3/1/2017.
  */

object traces {

  val links: Map[feature, Symbol] = Map()
  val links2: Map[Symbol, feature] = Map()
  val lst1: ArrayBuffer[String] = new ArrayBuffer()
  val mapping_links: Map[Int, Int] = Map()

  def apply(vp: VP): FeatureBuilder = new FeatureBuilder(vp)
  def apply(vp: OPT_VP): FeatureBuilder3 = new FeatureBuilder3(vp)
  def apply(tvp: tech_VP): FeatureBuilder4 = new FeatureBuilder4(tvp)
  def apply(nvp: nested_VP): FeatureBuilder5 = new FeatureBuilder5(nvp)
  def apply(v: Variant): FeatureBuilder2 = new FeatureBuilder2(v)

  implicit def feature2FeatureBuilder(vp: VP) = new FeatureBuilder(vp)
  implicit def feature2FeatureBuilder(vp: OPT_VP) = new FeatureBuilder3(vp)
  implicit def feature2FeatureBuilder(tvp: tech_VP) = new FeatureBuilder4(tvp)
  implicit def feature2FeatureBuilder(nvp: nested_VP) = new FeatureBuilder5(nvp)
  implicit def feature2FeatureBuilder(v: Variant) = new FeatureBuilder2(v)


  def pairsmap(f: Map[String, Int], vs: Map[String, Int]): Unit = {
    println("Map1: " + f)
    println("Map2: " + vs)

    for((k,v) <- links2) {
      println("MapLinks: " + (k.name, v.name))
      if(f.contains(v.name) & vs.contains(k.name.toString)) { // prev: f.contains(v.name) & vs.contains(v.toString.split(" ").last))
        //println("Value is: ("  + f(k.name), vs(v.toString.split(" ").last))
        mapping_links += ((vs(k.name.toString), f(v.name))) // prev: ((f(v.name), vs(k.toString.split(" ").last)))
      }
    }
  }

}

class FeatureBuilder(vp: VP) {
  def implements(f: feature) = {
    traces.links(f) = vp.var_asset_elem
    traces.links2(vp.var_asset_elem) = f
    traces.lst1 += vp.var_asset_elem.toString.split(" ").last
    vp
  }
}

class FeatureBuilder2(v: Variant) {
  def implements(f: feature) = {
    traces.links(f) = v.var_asset_elem
    traces.links2(v.var_asset_elem) = f
    traces.lst1 += v.var_asset_elem.toString.split(" ").last
    v
  }
}

class FeatureBuilder3(vp: OPT_VP) {
  def implements(f: feature) = {
    traces.links(f) = vp.asset_elem
    traces.links2(vp.asset_elem) = f
    traces.lst1 += vp.asset_elem.toString.split(" ").last
    vp
  }
}

class FeatureBuilder4(tvp: tech_VP) {
  def implements(f: feature) = {
    traces.links(f) = tvp.var_elem
    traces.links2(tvp.var_elem) = f
    traces.lst1 += tvp.var_elem.toString.split(" ").last
    tvp
  }
}

class FeatureBuilder5(nvp: nested_VP) {
  def implements(f: feature) = {
    traces.links(f) = nvp.var_elem
    traces.links2(nvp.var_elem) = f
    traces.lst1 += nvp.var_elem.toString.split(" ").last
    nvp
  }
}