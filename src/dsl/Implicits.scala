package dsl

/**
  * Created by Tërnava on 6/5/2016.
  */

object module {
  var name: String = _
  //def apply(rules: VariationPoint => ModuleVariability) = new VariabilityBuilderRules(rules)
  def apply(url: String)(vp: VariationPoint): Unit = {
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

  implicit def VariantToVariationPoint(v: Variant) = new VariationPointBuilder(v)

  implicit def VariantToVariationPoint2(v: Variant) = new VariationPointBuilder2(v)

}

protected[dsl] class VariationPointBuilder(val v: Variant) {
  def toNestedVP: nested_VP = {
    //println(v.var_asset_elem.asClass)
    nested_VP(asset(v.var_asset_elem.asClass))
  }
}
protected[dsl] class VariationPointBuilder2(val v: Variant) {
  def toTechnicalVP: tech_VP = {
    //println(v.var_asset_elem.asClass)
    tech_VP(asset(v.var_asset_elem.asClass))
  }
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



protected[dsl] class LogicBuilder(val vp: VariationPoint) {
  //println("3: " + module.name)
  var lgc: Logic = _
  def is(logic: Logic): LogicBuilder = {
    assert(logic != OPT)
    lgc = logic
    this
  }
  def is_ (logic: Logic): VariationPoint = {
    assert(logic == OPT)
    VariabilityModel.add(vp, null, OPT, null, null, null)
    lgc = OPT
    vp
  }
}


protected[dsl] class VariantBuilder(val logic_builder: LogicBuilder) {
  //println("4: " + module.name)
  val vp = logic_builder.vp
  val lgc = logic_builder.lgc
  var vrnts: Seq[Variant] = _
  def with_variants(variants: Variant*): VariantBuilder = {
    //vp.add_variant(variants, logic_builder.lgc)
    vrnts = variants
    this
  }
}

protected[dsl] class MechanismBuilder(val variant_builder: VariantBuilder) {
  //println("5: " + module.name)
  val vp = variant_builder.vp
  val lgc = variant_builder.lgc
  val vrnts = variant_builder.vrnts
  var mchnsm: Mechanism = _
  def use(mechanism: Mechanism): MechanismBuilder = {
    mchnsm = mechanism
    this
  }
}

protected[dsl] class BindTimeBuilder(val mechanism: MechanismBuilder) {
  val vp: VariationPoint      = mechanism.vp
  val lgc: Logic              = mechanism.lgc
  val vrnts: Seq[Variant]     = mechanism.vrnts
  val mchnsm: Mechanism       = mechanism.mchnsm
  var bndg: BindingTime       = _
  def with_binding(binding: BindingTime): BindTimeBuilder = {
    //println("6: " + module.name)
    bndg = binding
    this
  }
}

protected[dsl] class EvolutionBuilder(val bt: BindTimeBuilder) {
  //println("7: " + module.name)
  def and_evolution(evolution: Evolve) = {
    //VariabilityModel.add(url.url_name, bt.vp, bt.mchnsm, bt.lgc,bt.bndg, evolution, bt.vrnts)
    VariabilityModel.add(bt.vp, bt.mchnsm, bt.lgc, bt.bndg, evolution, bt.vrnts)
    //println(bt.vp + " : " + bt.mchnsm + " -> ("+ bt.lgc + ", " + bt.bndg + ", " + evolution + ") AND " + bt.vrnts)
    bt.vp
  }
}