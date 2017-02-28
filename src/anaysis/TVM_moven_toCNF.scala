package anaysis

/**
  * Created by Tërnava on 2/27/2017.
  */

import java.io.File

import SPL_Analysed_Examples.MicrowaveOvenSPL._
import basic_sat4j_setup.SAT4jSetup
import dsl._
import org.sat4j.specs._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object TVM_moven_toCNF {

  import basic._
  private val map = mutable.Map.empty[String, Int]
  private val lst: ArrayBuffer[String] = new ArrayBuffer()

def main(args: Array[String]): Unit = {

  /* Choose one or more TVM(s) to check! */
  import tvms._
  //tvm_door;
  tvm_language
  //tvm_temperature

  for(l <- asset.variant_asset_elements) {
    lst += l.toString.split(" ").last
  }
  println(lst) // debugging stuff...

  CreateVariablesForVPs(lst, map)
  println(map) // debugging stuff...

  val vm = VariabilityModel.get
  println(vm)// debugging stuff...

  val vm_buffer = TuplesToList(vm)
  val sentence = ConvertToCNF(ConvertDSLtoPLogic(vm_buffer))
  println("The CNF sentence is: " + sentence) // debugging stuff...

  println(ConvertDSLtoPLogic(vm_buffer)) // debugging stuff...

  val toFile: File = new File("Test_02.cnf")
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
