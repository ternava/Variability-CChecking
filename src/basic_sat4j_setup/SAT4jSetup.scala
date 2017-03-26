package basic_sat4j_setup

import java.io.{File, PrintWriter}

import org.sat4j.core.LiteralsUtils
import org.sat4j.minisat.SolverFactory
import org.sat4j.minisat.core.{DataStructureFactory, Solver}
import org.sat4j.reader.DimacsReader
import org.sat4j.specs.{IProblem, ISolver}
import org.sat4j.tools.ModelIterator

/**
  * Created by Tërnava on 2/22/2017.
  */
object SAT4jSetup {

  //val solver: ISolver = new ModelIterator(SolverFactory.newDefault())
  val solver: ISolver = SolverFactory.newDefault
  val mi: ModelIterator = new ModelIterator(solver)
  val timeout = solver.setTimeout(3600) // 1 hour timeout
  val reader: DimacsReader = new DimacsReader(mi)
  val out: PrintWriter = new PrintWriter(new File("example_fm_01.txt"))

  /* Check the consistency of a formula ---------------------------------------------------------*/
  def isConsistent(p: IProblem): Boolean = p.isSatisfiable

  /* Find and count the number of valid configurations of an FM ---------------------------------*/
  def validConfigurations(p: IProblem): Double = {
    var count: Double = 0
    if (isConsistent(p)) {
      try {
        while (p.isSatisfiable()) {
          count += 1
          println(reader.decode(p.model))
          //mdl :+ p.model
          saveOut(p)
        }
      } catch {
        case e: Exception => println("The VM does not have any valid configuration!")
      }
      out.close()
    }
    count
  }
  /* Write the resulted FM configurations in a file! --------------------------------------------*/
  def saveOut(p: IProblem) {
    reader.decode(p.model, out)
    out.println()
  }

  /* Checking whether a feature in FM is Dead!
* ------------------------------------------------------------------------------*/
  def isDead(fIndex: Int) = {
    // if(isConsistent(p)) {
    if((solver.asInstanceOf[Solver[DataStructureFactory]]).assume(LiteralsUtils.posLit(fIndex))) {
      //println("Feature " + fIndex + " is Dead!")
      false
      //false
    } else
    true
    //{
      //println("Feature " + fIndex + " is not Dead!")
    //true
    //}
    //!isConsistent(p)
    //}

    //} else {
    // println("The FM is inconsistent!")
  }

  /* Checking whether a feature in FM is Common!
  * ------------------------------------------------------------------------------*/
  def isCommon(fIndex: Int) = {
    // if(isConsistent(p)) {
    if((solver.asInstanceOf[Solver[DataStructureFactory]]).assume(LiteralsUtils.negLit(fIndex))) {
      //println("Feature " + fIndex + " is not Common!")
      false
    } else
    //{
     // println("Feature " + fIndex + " is Common!")
    true
    //}
    //!isConsistent(p)
    //}

    //} else {
    // println("The FM is inconsistent!")
  }


}
