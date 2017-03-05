package anaysis

/**
  * Created by Tërnava on 2/27/2017.
  */

import java.io.{File, PrintWriter}

import SPL_Analysed_Examples.MicrowaveOvenSPL._
import basic_sat4j_setup.SAT4jSetup
import dsl._
import org.sat4j.reader.ParseFormatException
import org.sat4j.specs._

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
import scala.io.Source

class ExtractFile(fromFile: Iterator[String], applyInFile: Iterator[String]) {

  /* Extracting the distinct variables of a .cnf file; saving them in an Array */
  private val fileToArray: Array[String] = {
    fromFile.drop(2).mkString(" ").split(" ").distinct diff Array("0")
  }

  /* Extracting the part of the formula based on a previous file/traces */
  def extract: List[String] = applyInFile.drop(2).filter {
    l =>
      l.split(" ").exists(variable => fileToArray.contains(variable))
  }.toList

}

class WriteFile(fromFile: List[String], toFile: File, theFile: Iterator[String]) {

  val destination: PrintWriter = new PrintWriter(toFile)

  def max(xs: List[Int]): Option[Int] = xs match {
    case Nil => None
    case List(x: Int) => Some(x)
    case x :: y :: rest => max( (if (x > y) x else y) :: rest )
  }

  /* Getting the number of lines and variables in a .cnf file */
  private def getLinesVariables: String = {
    var nrLines:     Int = 0
    var lst_buff: ArrayBuffer[List[Int]] = ArrayBuffer()

    fromFile.foreach { s =>
        nrLines += 1
        val lst = """[0-9]+""".r.findAllIn(s).toList.map(_.toInt)//s.split(" ").toList.max.toInt // Problem with this!???
        lst_buff += lst
    }

   //println("The list is: " + lst_buff.flatten.toList)
    val nrVariables: Int = max(lst_buff.flatten.toList) match {
                            case Some(i) => i
                            case None => 0
                          }
    println(nrVariables + " " + nrLines)
    nrVariables + " " + (nrLines + 1)
  }

  def writeHeaderTL: Unit = {
    destination.write("c " + toFile.getName + "\n")
    destination.write("p cnf " + getLinesVariables + "\n")
  }
  def writeHeaderFM: Unit = {
    destination.write("c " + toFile.getName + "\n")
    destination.write("p cnf " + getLinesVariables + "\n")
    //I was taking the root: "1 0 \n" !!!
    //destination.write(theFile.drop(2).toList.head + "\n")
    destination.write("1 0 \n")
  }

  def writeLines: Unit = {
    for(line <- fromFile) {
      println(line)
      destination.write(line)
      destination.write("\n")
    }
    destination.close()
  }

}

object TVM_moven_toCNF {

  import basic._
  val map = mutable.Map.empty[String, Int]
  val generatedFromTraces: File = new File("moven_pl_names_for_vp.txt")

  /* For Consistency Checking files --------------------------------------*/
  def fnSource: File = new File("Test_02.cnf")
  def fnTraces: File = new File("Test_03.cnf")
  def fnFModel: File = new File("Test_01.cnf")

  def s = Source.fromFile(fnSource)
  def t = Source.fromFile(fnTraces)
  def fm = Source.fromFile(fnFModel)

  def source = s.getLines()
  def traces = t.getLines()
  def fmodel = fm.getLines()

  val d1: File = new File("sliceTL_01.cnf")
  val d2: File = new File("sliceFM_02.cnf")

  def traces_final = Source.fromFile(d1)
  def fm_final = Source.fromFile(d2)
  def traces_excerpt = traces_final.getLines()
  def fm_excerpt = fm_final.getLines()

  /*-----------------------------------------------------------------------*/





def main(args: Array[String]): Unit = {
  //def mainTVM() = {

  /* Choose one or more TVM(s) to check! */
  import tvms._
  //tvm_door;
  tvm_language
  //tvm_temperature
   //tvm_weight
  //tvm_rotate
  //tvm_door
  //tvm_temperature

  GetVariablesForVPs(generatedFromTraces, map)

  println("The assets with the documented variability are: " + GetVariablesForVPs.lst) // debugging stuff...
  println("The VPs and Vs are: " + map) // debugging stuff...

  //  if(map.isEmpty) {
  //    return println("The trace links are missing for this TVM!")
   // }


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

  solver.clearLearntClauses()
  try {
    if(isConsistent(problem)) {
      println("The TVM(s) is Consistent!")
      nrModels = SAT4jSetup.validConfigurations(problem)
      println("Nr of Configurations is: " + nrModels)
    }
  } catch {
    case e: ContradictionException => println("The TVM(s) is NOT Consistent!", e)
  }


  /* Consistency Checking part: ------------------------------- */

  var nrModels1: Double = 0
  var nrModelsAll: Double = 0

  val ef = new ExtractFile(source, traces)
  val fileToWrite: List[String] = ef.extract
  for(et <- fileToWrite) println("Test:" + et)

  val wf = new WriteFile(fileToWrite, d1, source)
  wf.writeHeaderTL
  wf.writeLines

  s.close()
  t.close()

  val ef2 = new ExtractFile(traces_excerpt, fmodel)
  val fileToWrite2: List[String] = ef2.extract ::: List("1 0")
  //for(et <- fileToWrite2)
    println("Test2: " + fileToWrite2)

  val wf2 = new WriteFile(fileToWrite2, d2, fmodel)
  wf2.writeHeaderFM
  wf2.writeLines

  fm.close()

  AllSlicesToDimacs(fileToWrite, fileToWrite2, toFile)





  solver.clearLearntClauses()
  val problem4: IProblem = reader.parseInstance(d2.getName)

  try {
    if(isConsistent(problem4)) {
      println("SAT! ")
      nrModels1 = validConfigurations(problem4)
      println("Nr of Configurations is: " + nrModels1)
    }
  } catch {
    case e: ContradictionException => println("UnSAT ", e)
  }

  solver.clearLearntClauses()
  val problemAll: IProblem = reader.parseInstance("all_slices.cnf")

  try {
    if(isConsistent(problemAll)) {
      println("SAT! ")
      nrModelsAll = validConfigurations(problemAll)
      println("Nr of Configurations is: " + nrModelsAll)
    }
  } catch {
    case e: ContradictionException => println("UnSAT ", e)
  }

  //assert(nrModels4 == nrModels1, "Equal")
  if((nrModels == nrModels1) & (nrModels == nrModelsAll) & (nrModels1 == nrModelsAll)){
    println("Models are Consistent to each other!",(nrModels1), nrModels, nrModelsAll)
  } else {
    println("Models are Inconsistent to each other!", (nrModels1), nrModels, nrModelsAll)
  }

}


}
