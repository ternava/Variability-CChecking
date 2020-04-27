package math.geom2d.domain

/**
  * Created by Tërnava on 2/5/2019.
  */
import scala.reflect.runtime.universe._
import math.dsl._

object tvm_domain {

  val vp_OrientedCurve2D: VP = VP(asset(typeOf[OrientedCurve2D].typeSymbol))
  val v_ContinuousOrientedCurve2D: Variant = Variant(asset(typeOf[ContinuousOrientedCurve2D].typeSymbol))
  val v_PolyOrientedCurve2D: Variant = Variant(asset(typeOf[PolyOrientedCurve2D[_]].typeSymbol))
  val v_Boundary2D: Variant = Variant(asset(typeOf[Boundary2D].typeSymbol))
  val v_Contour2D: Variant = Variant(asset(typeOf[Contour2D].typeSymbol))
  val v_SmoothOrientedCurve2D: Variant = Variant(asset(typeOf[SmoothOrientedCurve2D].typeSymbol))

  val v_BoundaryPolyCurve2D: Variant = Variant(asset(typeOf[BoundaryPolyCurve2D[_]].termSymbol))
  val v_ContourArray2D: Variant = Variant(asset(typeOf[ContourArray2D[_]].termSymbol))
  val v_SmoothContour2D: Variant = Variant(asset(typeOf[SmoothContour2D].termSymbol))

  val vp_Domain2D: VP = VP(asset(typeOf[Domain2D].typeSymbol))
  val v_DomainSet2D: Variant = Variant(asset(typeOf[DomainSet2D[_]].typeSymbol))

  val v_GenericDomain2D: Variant = Variant(asset(typeOf[GenericDomain2D].termSymbol))
  val v_DomainArray2D: Variant = Variant(asset(typeOf[DomainArray2D[_]].termSymbol))

  import module._

  val vp_ContinuousOrientedCurve2D: nested_VP = v_ContinuousOrientedCurve2D.toNestedVP
  val vp_PolyOrientedCurve2D: nested_VP = v_PolyOrientedCurve2D.toNestedVP
  val vp_Boundary2D: nested_VP = v_Boundary2D.toNestedVP
  val vp_Contour2D: nested_VP = v_Contour2D.toNestedVP
  val vp_SmoothOrientedCurve2D: nested_VP = v_SmoothOrientedCurve2D.toNestedVP
  val vp_DomainSet2D: nested_VP = v_DomainSet2D.toNestedVP


  module("domain") {
    vp_OrientedCurve2D is ALT with_variants (v_ContinuousOrientedCurve2D, v_Boundary2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_ContinuousOrientedCurve2D is ALT with_variants (v_PolyOrientedCurve2D, v_BoundaryPolyCurve2D,
    v_SmoothOrientedCurve2D, v_Contour2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_PolyOrientedCurve2D is ALT with_variants (v_BoundaryPolyCurve2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_Boundary2D is ALT with_variants (v_Contour2D, v_ContourArray2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_Contour2D is ALT with_variants (v_ContourArray2D, v_SmoothContour2D, v_BoundaryPolyCurve2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_SmoothOrientedCurve2D is ALT with_variants (v_SmoothContour2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_Domain2D is ALT with_variants (v_DomainSet2D, v_GenericDomain2D, v_DomainArray2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_DomainSet2D is ALT with_variants (v_DomainArray2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN


  }


}
