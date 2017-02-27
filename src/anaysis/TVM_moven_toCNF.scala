package anaysis

/**
  * Created by Tërnava on 2/27/2017.
  */

import java.io.File

import basic._

import scala.reflect.runtime.universe._
import MicrowaveOvenSPL.oven._
import basic_sat4j_setup.SAT4jSetup
import dsl._
import org.sat4j.specs._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object TVM_moven_toCNF {

  val Language: VariationPoint    = VP(asset(typeOf[Language].typeSymbol))
  val English: Variant            = Variant(asset(typeOf[English.type].termSymbol))
  val French: Variant             = Variant(asset(typeOf[French.type].termSymbol))
  val Italian: Variant            = Variant(asset(typeOf[Italian.type].termSymbol))
  val Albanian: Variant           = Variant(asset(typeOf[Albanian.type].termSymbol))
  val German: Variant             = Variant(asset(typeOf[German.type].termSymbol))
  val Spanish: Variant            = Variant(asset(typeOf[Spanish.type].termSymbol))

  val Door                      = OPT_VP(asset(typeOf[DoorStatus].typeSymbol))
  val Open: Variant             = Variant(asset(typeOf[Open.type].termSymbol))
  val Close: Variant            = Variant(asset(typeOf[Close.type].termSymbol))


  import basic._
  private val map = mutable.Map.empty[String, Int]




def main(args: Array[String]): Unit = {

  import module._
/*
  module("Language.scala") {
    Language is ALT with_variants (English, French, Albanian, Italian, German, Spanish) use
      INHERITANCE with_binding RUN_TIME and_evolution OPEN
  } */

  module("Door.scala") {
    Door is ALT with_variants(Open, Close) use
      INHERITANCE with_binding RUN_TIME and_evolution CLOSE
  }



  val lst: ArrayBuffer[String] = new ArrayBuffer()
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
