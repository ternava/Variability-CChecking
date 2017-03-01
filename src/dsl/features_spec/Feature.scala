package dsl.features_spec

import anaysis.FM_moven_toCNF
import dsl.{Variant, VariationPoint}

import scala.collection.mutable._

/**
  * Created by Tërnava on 6/10/2016.
  */

class Feature(val name: String)

object Feature {
  def apply(name: String) = new Feature(name)
}





object traces {

  val set_traces: Map[Feature, String] = Map()

  def apply(f: Feature): FeatureBuilder = new FeatureBuilder(f)
  def apply(vp: Seq[VariationPoint]): FeatureBuilder4 = new FeatureBuilder4(vp)
  def apply(vp: VariationPoint): FeatureBuilder3 = new FeatureBuilder3(vp)
  def apply(v: Variant): FeatureBuilder2 = new FeatureBuilder2(v)

  implicit def feature2FeatureBuilder(f: Feature) = new FeatureBuilder(f)
  implicit def feature2FeatureBuilder4(vp: Seq[VariationPoint]) = new FeatureBuilder4(vp)
  implicit def feature2FeatureBuilder3(vp: VariationPoint) = new FeatureBuilder3(vp)
  implicit def feature2FeatureBuilder2(v: Variant) = new FeatureBuilder2(v)

}

protected[features_spec] class FeatureBuilder(f: Feature) {
  def implements(vp: String) = {
    traces.set_traces(f) = vp
    f
  }
}

protected[features_spec] class FeatureBuilder4(vp: Seq[VariationPoint]) {
  def implements(f: Feature) = {
    vp
  }
}
protected[features_spec] class FeatureBuilder3(vp: VariationPoint) {
  def implements(f: Feature) = {
    vp
  }
}
protected[features_spec] class FeatureBuilder2(vp: Variant) {
  def implements(f: Feature) = {
    vp
  }
}





object Features {

  val f_DisplayLanguage = Feature("f_DisplayLanguage")
  val f_DefaultLanguage = Feature("f_DefaultLanguage")
  val f_HeatingLevel = Feature("f_HeatingLevel")



  import traces._

  def main(args: Array[String]): Unit = {

    println("Found? : " + FM_moven_toCNF.map(f_DisplayLanguage.name))

    traces {
      f_DisplayLanguage implements("Language")
      f_HeatingLevel implements("HeatingLevel")
    }

    for((k, v) <- traces.set_traces) println((k.name, v))

    //traces {

     //Language implements f_DisplayLanguage
     //English implements f_DefaultLanguage

   //}


  }

}