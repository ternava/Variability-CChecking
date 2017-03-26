package basic

import java.io.{File, PrintWriter, Reader}

import scala.io.Source

/**
  * Created by Tërnava on 3/2/2017.
  */

object AllSlicesToDimacs {

  val all_slices: File = new File("all_slices.cnf")
  val destination: PrintWriter = new PrintWriter(all_slices)
  var nrVariables: Int = 0
  var nrLines: Int = 0


  def writeHeaderAll: Unit = {
    destination.write("c " + all_slices.getName + "\n")
    destination.write("p cnf " + nrVariables + " " + nrLines + "\n")
    //I was taking the root: "1 0 \n" !!!
    //destination.write(theFile.drop(2).toList.head + "\n")
    destination.write("1 0 \n")
  }

  def writeLinesAll(fromFile: List[String]): Unit = {
    for(line <- fromFile) {
      // --- println(line)
      destination.write(line)
      destination.write("\n")
    }
    destination.close()
  }

  def extractAll(fromFile: Iterator[String]) = {
    fromFile.drop(2)

  }

  def apply(sliceTL: List[String], sliceFM: List[String], fileTVMx: File) = {

    val lstAll = AllSlicesToDimacs.extractAll(Source.fromFile(fileTVMx).getLines())
   // println("LstAll: " + lstAll)

    val all_ef = sliceTL ::: sliceFM ::: lstAll.toList
   // println("All Test: " + all_ef)

    // --- println(all_ef.mkString(" ").trim)
    val lst = """[0-9]+""".r.findAllIn(all_ef.mkString(" ").trim).toList.map(_.toInt)
    // --- println("LST: ", lst)

    nrVariables = lst.distinct.max
    nrLines = lst.count(_ == 0) + 1



    AllSlicesToDimacs.writeHeaderAll
    AllSlicesToDimacs.writeLinesAll(all_ef)

  }


}
