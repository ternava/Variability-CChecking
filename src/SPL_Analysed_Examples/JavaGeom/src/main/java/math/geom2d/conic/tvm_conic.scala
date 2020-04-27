package math.geom2d.conic

/**
  * Created by Tërnava on 2/5/2019.
  */

import scala.reflect.runtime.universe._
import math.dsl._
import math.geom2d.conic.Conics2D._

object tvm_conic {

  val vp_EllipseArcShape2D: VP = VP(asset(typeOf[EllipseArcShape2D].typeSymbol))
  val vp_CircularShape2D: VP = VP(asset(typeOf[CircularShape2D].typeSymbol))
  val vp_Conic2D: VP = VP(asset(typeOf[Conic2D].typeSymbol))

  val v_EllipseShape2D: Variant = Variant(asset(typeOf[EllipseShape2D].typeSymbol))

  val v_EllipseArc2D: Variant =  Variant(asset(typeOf[EllipseArc2D.type].termSymbol))
  val v_CircleArc2D: Variant =  Variant(asset(typeOf[CircleArc2D.type].termSymbol))
  val v_Circle2D: Variant =  Variant(asset(typeOf[Circle2D.type].termSymbol))
  val v_Ellipse2D: Variant =  Variant(asset(typeOf[Ellipse2D.type].termSymbol))
  val v_ConicStraightLine2D: Variant =  Variant(asset(typeOf[ConicStraightLine2D.type].termSymbol))
  val v_Parabola2D: Variant =  Variant(asset(typeOf[Parabola2D.type].termSymbol))
  val v_ConicTwoLines2D: Variant =  Variant(asset(typeOf[ConicTwoLines2D.type].termSymbol))
  val v_Hyperbola2D: Variant =  Variant(asset(typeOf[Hyperbola2D.type].termSymbol))

  import module._

  val vp_EllipseShape2D: nested_VP = v_EllipseShape2D.toNestedVP

   module("conic") {
     vp_EllipseArcShape2D is ALT with_variants (v_EllipseArc2D, v_CircleArc2D) use
       INHERITANCE with_binding RUN_TIME and_evolution OPEN

     vp_CircularShape2D is ALT with_variants (v_CircleArc2D, v_Circle2D) use
       INHERITANCE with_binding RUN_TIME and_evolution OPEN

     vp_EllipseShape2D is ALT with_variants (v_Circle2D, v_Ellipse2D) use
       INHERITANCE with_binding RUN_TIME and_evolution OPEN

     vp_Conic2D is ALT with_variants (v_EllipseShape2D, v_ConicStraightLine2D, v_Parabola2D,
     v_ConicTwoLines2D, v_Hyperbola2D) use
       INHERITANCE with_binding RUN_TIME and_evolution OPEN

   }


}
