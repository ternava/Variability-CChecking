package math.geom2d.circulinear

/**
  * Created by Tërnava on 2/5/2019.
  */

import scala.reflect.runtime.universe._
import math.dsl._

object tvm_circulinear {

  val vp_CirculinearShape2D: VP = VP(asset(typeOf[CirculinearShape2D].typeSymbol))
  val v_CirculinearDomain2D: Variant = Variant(asset(typeOf[CirculinearDomain2D].typeSymbol))
  val v_CirculinearCurve2D: Variant = Variant(asset(typeOf[CirculinearCurve2D].typeSymbol))

  val v_GenericCirculinearDomain2D: Variant  = Variant(asset(typeOf[GenericCirculinearDomain2D.type].termSymbol))

  val v_CirculinearContinuousCurve2D: Variant = Variant(asset(typeOf[CirculinearContinuousCurve2D].typeSymbol))
  val v_CirculinearBoundary2D: Variant = Variant(asset(typeOf[CirculinearBoundary2D].typeSymbol))
  val v_CirculinearCurveSet2D: Variant = Variant(asset(typeOf[CirculinearCurveSet2D[_]].termSymbol))
  val v_CirculinearCurveArray2D: Variant = Variant(asset(typeOf[CirculinearCurveArray2D[_]].termSymbol))

  val v_PolyCirculinearCurve2D: Variant = Variant(asset(typeOf[PolyCirculinearCurve2D[_]].typeSymbol))
  val v_BoundaryPolyCirculinearCurve2D: Variant = Variant(asset(typeOf[BoundaryPolyCirculinearCurve2D[_]].typeSymbol))
  //[GenericExample[_]].typeSymbol.asClass

  val v_CirculinearElement2D: Variant = Variant(asset(typeOf[CirculinearElement2D].typeSymbol))
  val v_CirculinearContour2D: Variant = Variant(asset(typeOf[CirculinearContour2D].typeSymbol))

  val v_CirculinearContourArray2D: Variant = Variant(asset(typeOf[CirculinearContourArray2D[_]].termSymbol))
  val v_CirculinearRing2D: Variant = Variant(asset(typeOf[CirculinearRing2D].typeSymbol))

  val v_CircleLine2D: Variant = Variant(asset(typeOf[CircleLine2D.type].termSymbol))
  val v_GenericCirculinearRing2D: Variant = Variant(asset(typeOf[GenericCirculinearRing2D.type].termSymbol))

  val vp_PolyCirculinearCurve2D: VP = VP(asset(typeOf[PolyCirculinearCurve2D[_]].typeSymbol))

  /* Defining the nested variation points*/

  import module._

  val vp_CirculinearDomain2D: nested_VP = v_CirculinearDomain2D.toNestedVP
  val vp_CirculinearCurve2D: nested_VP = v_CirculinearCurve2D.toNestedVP
  val vp_CirculinearContinuousCurve2D: nested_VP = v_CirculinearContinuousCurve2D.toNestedVP
  val vp_CirculinearBoundary2D: nested_VP = v_CirculinearBoundary2D.toNestedVP
  val vp_BoundaryPolyCirculinearCurve2D: nested_VP = v_BoundaryPolyCirculinearCurve2D.toNestedVP
  val vp_CirculinearElement2D: nested_VP = v_CirculinearElement2D.toNestedVP
  val vp_CirculinearContour2D: nested_VP = v_CirculinearContour2D.toNestedVP
  val vp_CirculinearRing2D: nested_VP = v_CirculinearRing2D.toNestedVP

  module("circulinear") {

    vp_CirculinearShape2D is ALT with_variants (v_CirculinearDomain2D, v_CirculinearCurve2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_CirculinearDomain2D is ALT with_variants(v_GenericCirculinearDomain2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_CirculinearCurve2D is ALT with_variants(v_CirculinearContinuousCurve2D, v_CirculinearBoundary2D,
      v_CirculinearCurveSet2D, v_CirculinearCurveArray2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_CirculinearContinuousCurve2D is ALT with_variants(v_PolyCirculinearCurve2D, v_BoundaryPolyCirculinearCurve2D,
      v_CirculinearElement2D, v_CirculinearContour2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_CirculinearBoundary2D is ALT with_variants(v_CirculinearContour2D, v_CirculinearContourArray2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_CirculinearContour2D is ALT with_variants(v_CirculinearContourArray2D, v_CirculinearRing2D, v_CircleLine2D,
    v_BoundaryPolyCirculinearCurve2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_CirculinearElement2D is ALT with_variants(v_CircleLine2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_PolyCirculinearCurve2D is ALT with_variants(v_BoundaryPolyCirculinearCurve2D, v_GenericCirculinearRing2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

    vp_CirculinearRing2D is ALT with_variants(v_GenericCirculinearRing2D) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN

  }

}
