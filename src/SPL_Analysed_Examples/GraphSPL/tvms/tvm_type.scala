package SPL_Analysed_Examples.GraphSPL.tvms

import dsl._

import scala.reflect.runtime.universe._
import SPL_Analysed_Examples.GraphSPL.strategypattern._
import SPL_Analysed_Examples.GraphSPL.traitsgraph.Search

/**
  * Created by Tërnava on 3/10/2017.
  */

object tvm_type {

  val TypeG: VP                    = VP(asset(typeOf[ConcreteGraph].member(stringToTermName("addedge"))))
  val Directed: Variant            = Variant(asset(typeOf[ConcreteGraph].member(stringToTermName("adddirectededge"))))
  val UnDirected: Variant          = Variant(asset(typeOf[ConcreteGraph].member(stringToTermName("addundirectededge"))))

  val Weight: VP                   = VP(asset(typeOf[Conf.type].member(stringToTermName("WEIGHTED"))))
  val Weighted: Variant            = Variant(asset(typeOf[Conf.type].member(stringToTermName("WEIGHTED"))))
  val UnWeighted: Variant          = Variant(asset(typeOf[ConcreteGraph].member(stringToTermName("weighted"))))

  val Search: OPT_VP                   = OPT_VP(asset(typeOf[Search].member(stringToTermName("search"))))
  val dfs: Variant                 = Variant(asset(typeOf[Search].member(stringToTermName("DFS"))))
  val bfs: Variant                 = Variant(asset(typeOf[Search].member(stringToTermName("BFS"))))

  import module._

  module("TypeGraph.scala") {
    TypeG is ALT with_variants (Directed, UnDirected) use
      STRATEGY_P with_binding RUN_TIME and_evolution CLOSE

    Weight is ALT with_variants (Weighted, UnWeighted) use
      PARAMETER with_binding RUN_TIME and_evolution CLOSE
  }

  module("Search.scala") {
    Search is ALT with_variants (dfs, bfs) use
      STRATEGY_P with_binding RUN_TIME and_evolution CLOSE
  }

}
