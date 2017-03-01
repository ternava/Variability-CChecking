package basic

import java.io.File

import dsl.asset
import logic.propositional.PropositionSymbol

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Tërnava on 2/27/2017.
  */

object CreateVariablesForVPs {

  def apply(varNames: ArrayBuffer[String], toMap: mutable.Map[String, Int]): Unit = {

    toMap += add(PropositionSymbol("VProot"), 100)

    var i: Int = 100
    for(l <- varNames) {
      i = i + 1
      toMap += add(PropositionSymbol(l), i)
    }
  }

}

object GetVariablesForVPs {


  val lst: ArrayBuffer[String] = new ArrayBuffer()
  private val map2 = mutable.Map.empty[String, Int]

  def apply(fromFile: File, toMap: mutable.Map[String, Int]): Unit = {

    for(l <- asset.variant_asset_elements) {
      lst += l.toString.split(" ").last
      //println("From DSL: " + l)
    }

    val source2 = scala.io.Source.fromFile(fromFile)
    //val lines = try source.mkString finally source.close()

    // Do not use this, because the file iterator will be consumed!!!
    // println("Lines from the file: ")
    // source2.getLines().foreach(println(_))

    for(l <- source2.getLines()) {
      val sp = l.split(" ").toList
      map2(sp.head) = sp.last.toInt
    }
    source2.close()

    //println("All Trace Links in Map 2 are: " + map2)

    for(l <- lst) {
      if(map2.contains(l)) {
        toMap(l) = map2(l)
      }
    }
  }
}