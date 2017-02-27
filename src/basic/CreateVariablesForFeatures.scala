package basic

import java.io.File
import logic.propositional.PropositionSymbol
import scala.collection.mutable

/**
  * Created by Tërnava on 2/27/2017.
  */
object CreateVariablesForFeatures {

  def apply(fromFile: File, toMap: mutable.Map[String, Int]) = {

    val source1 = scala.io.Source.fromFile(fromFile)
    val lines1 = try source1.mkString finally source1.close()

    val dist = """(f_|)[A-Za-z]+""".r.findAllIn(lines1.toString).toList.distinct

    var i:  Int = 0
    for(l <- dist) {
      i = i + 1
      toMap += add(PropositionSymbol(l.toString), i)
    }
  }

}
