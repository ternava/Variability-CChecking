package anaysis

/**
  * Created by Tërnava on 2/26/2017.
  */

import java.io.File

import basic._
import basic_sat4j_setup.SAT4jSetup
import logic.propositional._
import org.sat4j.specs.{ContradictionException, IProblem}

import scala.collection.mutable
//import scala.reflect.io.File

object FM_moven_toCNF {

  private val map = mutable.Map.empty[String, Int]

  def main(args: Array[String]): Unit = {

    val theFMfile = new File(args(0))
    val source = scala.io.Source.fromFile(theFMfile)

    val lines = try source.mkString finally source.close()

    CreateVariablesForFeatures(theFMfile, map)
    println(map) // debugging stuff....

    val sentence = ConvertToCNF(lines)
    println(sentence) // debugging stuff....

    val toFile: File = new File("Test_01.cnf")
    ConvertToDimacs(sentence, map, toFile)

    /* SAT4j usage ----------------------------------------------*/
    import basic_sat4j_setup.SAT4jSetup._

    var nrModels: Double = 0
    val problem: IProblem = reader.parseInstance(toFile.getName)

    try {
      if(isConsistent(problem)) {
        println("SAT! ")
        nrModels = SAT4jSetup.validConfigurations(problem)
        println("Nr of Configurations is: " + nrModels)
      }
    } catch {
      case e: ContradictionException => println("UnSAT ", e)
    }


  }








}
