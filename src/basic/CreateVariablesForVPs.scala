package basic

import logic.propositional.PropositionSymbol

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Tërnava on 2/27/2017.
  */

object CreateVariablesForVPs {

  def apply(varNames: ArrayBuffer[String], toMap: mutable.Map[String, Int]): Unit = {

    toMap += add(PropositionSymbol("VProot"), 1)

    var i: Int = 1
    for(l <- varNames) {
      i = i + 1
      toMap += add(PropositionSymbol(l), i)
    }
  }

}
