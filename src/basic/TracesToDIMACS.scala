package basic

import java.io.{File, PrintWriter}

import dsl.traces._

import scala.collection.mutable.Map

/**
  * Created by Tërnava on 3/1/2017.
  */
object TracesToDIMACS {


  def writeLinks(toFile: File) = {

    val destination: PrintWriter = new PrintWriter(toFile)

    var nrVariables = mapping_links.values.max
    var nrLines = mapping_links.size * 2

    destination.write("c " + toFile.getName + "\n")
    destination.write("p cnf " + nrVariables + " " + nrLines + "\n")
    for ((k, v) <- mapping_links) {
      destination.write("-" + k + " " + v + " 0\n" + k + " -" + v + " 0\n")
    }
    destination.close()
  }

  val moven_pl_names_for_vp: File = new File("moven_pl_names_for_vp.txt")

  val destination: PrintWriter = new PrintWriter(moven_pl_names_for_vp)

  def save_pl_names_for_vp(mappings: Map[String, Int]): Unit = {
    for((k,v) <- mappings) {
      destination.write(k + " " + v + "\n")
    }
    destination.close()
  }

}
