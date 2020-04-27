package math.geom2d.curve

/**
  * Created by Tërnava on 2/5/2019.
  */

import scala.reflect.runtime.universe._
import math.dsl._

object tvm_curve {

  val vp_Curve2D: VP = VP(asset(typeOf[Curve2D].typeSymbol))
  val v_CurveSet2D: Variant = Variant(asset(typeOf[CurveSet2D[_]].typeSymbol))
  val v_ContinuousCurve2D: Variant = Variant(asset(typeOf[ContinuousCurve2D].typeSymbol))
  val v_SmoothCurve2D: Variant = Variant(asset(typeOf[SmoothCurve2D].typeSymbol))
  val v_AbstractContinuousCurve2D: Variant = Variant(asset(typeOf[AbstractContinuousCurve2D].typeSymbol))
  val v_CurveArray2D: Variant =  Variant(asset(typeOf[CurveArray2D[_]].typeSymbol))

  val v_PolyCurve2D: Variant = Variant(asset(typeOf[PolyCurve2D[_]].termSymbol))
  val v_AbstractSmoothCurve2D: Variant = Variant(asset(typeOf[AbstractSmoothCurve2D.type].termSymbol))

  import module._

  val vp_CurveSet2D: nested_VP = v_CurveSet2D.toNestedVP
  val vp_ContinuousCurve2D: nested_VP = v_ContinuousCurve2D.toNestedVP
  val vp_SmoothCurve2D: nested_VP = v_SmoothCurve2D.toNestedVP
  val vp_AbstractContinuousCurve2D: nested_VP = v_AbstractContinuousCurve2D.toNestedVP
  val vp_CurveArray2D: nested_VP = v_CurveArray2D.toNestedVP

  module("curve") {
    vp_Curve2D is ALT with_variants (v_ContinuousCurve2D, v_CurveSet2D, v_CurveArray2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_CurveSet2D is ALT with_variants (v_CurveArray2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_ContinuousCurve2D is ALT with_variants (v_SmoothCurve2D, v_AbstractContinuousCurve2D, v_PolyCurve2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_SmoothCurve2D is ALT with_variants (v_AbstractSmoothCurve2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_AbstractContinuousCurve2D is ALT with_variants (v_AbstractSmoothCurve2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_CurveArray2D is ALT with_variants (v_PolyCurve2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

  }

}
