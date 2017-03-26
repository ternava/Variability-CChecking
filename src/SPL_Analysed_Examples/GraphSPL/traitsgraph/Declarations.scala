package SPL_Analysed_Examples.GraphSPL.traitsgraph

import scala.annotation.StaticAnnotation

object Declarations {

  class feature(name: String) extends StaticAnnotation

  class bindsetfunctionality(features: Set[String], tp: String)
    extends StaticAnnotation
    
}