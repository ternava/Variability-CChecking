package basic_sat4j_setup

import java.io.{File, PrintWriter}

import org.sat4j.minisat.SolverFactory
import org.sat4j.reader.DimacsReader
import org.sat4j.specs.{IProblem, ISolver}
import org.sat4j.tools.ModelIterator

/**
  * Created by Tërnava on 2/22/2017.
  */
object SAT4jSetup {

  val solver: ISolver = new ModelIterator(SolverFactory.newDefault())
  val timeout = solver.setTimeout(3600) // 1 hour timeout
  val reader: DimacsReader = new DimacsReader(solver)
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

}
