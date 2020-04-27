package math.dsl_old

/**
  * Created by ternava on 6/5/2016.
  */

import scala.reflect.runtime.universe._

class module(name: String)(vp: VariationPoint) {

}

object module {
  var name: String = _
  //def apply(rules: VariationPoint => ModuleVariability) = new VariabilityBuilderRules(rules)
  def apply(url: String)(vp: VariationPoint) = {
    name = url
    new LogicBuilder(vp)
    //new module(url)(vp)
    //new VariabilityBuilderRules(url)(module)
  }

  //implicit def variationPoint2LogicBuilder(vp: VariationPoint[Symbol]) = new LogicBuilder(vp)
  //implicit def variationPoint2LogicBuilder(vp: VariationPoint[String]) = new LogicBuilder(vp)

  implicit def variationPoint2LogicBuilder(vp: VariationPoint) = new LogicBuilder(vp)

  implicit def logicBuilder2VariantsBuilder(lb: LogicBuilder) = new VariantBuilder(lb)

  implicit def variantBuilder2MechanismBuilder(vb: VariantBuilder) = new MechanismBuilder(vb)

  implicit def MechanismBuilder2BindTimeBuilder(mb: MechanismBuilder) = new BindTimeBuilder(mb)

  implicit def BindTimeBuilder2EvolutionBuilder(bt: BindTimeBuilder) = new EvolutionBuilder(bt)

}

/*class VariabilityException(message: String, cause: Throwable)
  extends RuntimeException(message, cause)

//a control structure?
protected[dsl] class VariabilityBuilderRules(name: String)(mod: Unit) {//(module: VariationPoint => VariantBuilder) { //ModuleVariability) {
  val url = name
  println("2: " + name)
  def apply(vp: VariationPoint) =
    {
    try {
      module(name)(vp)
    } catch {
      case th: Throwable => new VariabilityException(
        "Failed to process variability for the Variation Point: " + vp, th)
    }
  }

}*/


protected[dsl_old] class LogicBuilder(val vp: VariationPoint) {
  println("3: " + module.name)
  var lgc: Logic = _
  def is(logic: Logic) = {
    assert(logic != OPT)
    lgc = logic
    this
  }
  def is_ (logic: Logic) = {
    assert(logic == OPT)
    VariabilityModel.add(vp, null, lgc, null, null, null)
    lgc = logic
    vp
  }
}


protected[dsl_old] class VariantBuilder(val logic_builder: LogicBuilder) {
  println("4: " + module.name)
  val vp = logic_builder.vp
  val lgc = logic_builder.lgc
  var vrnts: Seq[Variant] = _
  def with_variants(variants: Variant*) = {
    //vp.add_variant(variants, logic_builder.lgc)

    vrnts = variants
    this
  }
}

protected[dsl_old] class MechanismBuilder(val variant_builder: VariantBuilder) {
  println("5: " + module.name)
  val vp = variant_builder.vp
  val lgc = variant_builder.lgc
  val vrnts = variant_builder.vrnts
  var mchnsm: Mechanism = _
  def use(mechanism: Mechanism) = {
    mchnsm = mechanism
    this
  }
}

protected[dsl_old] class BindTimeBuilder(val mechanism: MechanismBuilder) {
  val vp: VariationPoint      = mechanism.vp
  val lgc: Logic              = mechanism.lgc
  val vrnts: Seq[Variant]     = mechanism.vrnts
  val mchnsm: Mechanism       = mechanism.mchnsm
  var bndg: BindingTime       = _
  def with_binding(binding: BindingTime) = {
    println("6: " + module.name)
    bndg = binding
    this
  }
}

protected[dsl_old] class EvolutionBuilder(val bt: BindTimeBuilder) {
  println("7: " + module.name)
  def and_evolution(evolution: Evolve) = {
    //VariabilityModel.add(url.url_name, bt.vp, bt.mchnsm, bt.lgc,bt.bndg, evolution, bt.vrnts)
    VariabilityModel.add(bt.vp, bt.mchnsm, bt.lgc, bt.bndg, evolution, bt.vrnts)
    println(bt.vp.var_asset_elem + " : " + bt.mchnsm + " -> ("
      + bt.lgc + ", " + bt.bndg + ", " + evolution + ") AND " + bt.vrnts)
    //this
    bt.vp
  }
}