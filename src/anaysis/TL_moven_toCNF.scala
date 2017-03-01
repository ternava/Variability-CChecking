package anaysis

/**
  * Created by Tërnava on 2/28/2017.
  */

import java.io.{File, PrintWriter}

import SPL_Analysed_Examples.MicrowaveOvenSPL

import scala.collection.mutable._
import basic.{CreateVariablesForVPs, TracesToDIMACS}
import basic_sat4j_setup.SAT4jSetup
import org.sat4j.specs.{ContradictionException, IProblem}

object TL_moven_toCNF {

  val pl_names_for_vp: Map[String, Int] = Map()
  val toFile: File = new File("Test_03.cnf")

  def main(args: Array[String]): Unit = {

    import dsl.traces._

    FM_moven_toCNF.mainFM(args(0))
    //TVM_moven_toCNF.mainTVM()

    // Create mapping links...
    MicrowaveOvenSPL.FeatureVPtraces

    println("List is: " + lst1)

    CreateVariablesForVPs(lst1, pl_names_for_vp)
    println("The mapped names: " + pl_names_for_vp)

    TracesToDIMACS.save_pl_names_for_vp(pl_names_for_vp)

    /* matching features with vps values to take their keys
     * for writting in dimacs format*/
    pairsmap(FM_moven_toCNF.map, pl_names_for_vp)


    TracesToDIMACS.writeLinks(toFile)

    /* SAT4j usage ----------------------------------------------*/
    import basic_sat4j_setup.SAT4jSetup._

    //var nrModels: Double = 0
    solver.clearLearntClauses()
    val problem: IProblem = reader.parseInstance(toFile.getName)

    try {
      if(isConsistent(problem)) {
        println("Trace links are Consistent!")
        //nrModels = SAT4jSetup.validConfigurations(problem)
        //println("Nr of Configurations is: " + nrModels)
      }
    } catch {
      case e: ContradictionException => println("Trace links are NOT Consistent!", e)
    }

  }

}