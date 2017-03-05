package basic

import java.io.{File, PrintWriter}
import logic.propositional._
import scala.collection.mutable

/**
  * Created by Tërnava on 2/21/2017.
  */

private class ConvertToDimacs(val cnfSentence: CNFSentence, val map: mutable.Map[String, Int], val toFile: File) {
  val destination: PrintWriter = new PrintWriter(toFile)

  def keyToVal = {
    //This expression will do the same mapping, too.
    //println(map.foldLeft(r3){case (s, (k,v)) => s.replaceAll(k,v.toString)})
    val rep = """(f_|)[A-Za-z]+""".r.replaceAllIn(cnfSentence.toString, m => map.getOrElse(m.group(0), m.group(0)).toString)
    rep.replaceAll("VProot", "100")
  }

  def max(xs: List[Int]): Option[Int] = xs match {
    case Nil => None
    case List(x: Int) => Some(x)
    case x :: y :: rest => max( (if (x > y) x else y) :: rest )
  }

  val lst = """[0-9]+""".r.findAllIn(keyToVal).toList.map(_.toInt)
  val nrVariables = max(lst) match {
                      case Some(i) => i
                      case None => 0
                    }
  val nrLines = "&".r.findAllIn(keyToVal).toList.size + 1

  var countCh: Int = 0
  def writeInDimacsFormat = {
    destination.write("c " + toFile.getName + "\n")
    destination.write("p cnf " + nrVariables + " " + nrLines + "\n")
    for (w <- keyToVal.trim) {
      if(countCh != keyToVal.size-1){
        w match {
          case '~' => destination.write("-".trim)
          case '|' => destination.write(" ".trim)
          case '&' => destination.write("0\n")
          case '(' => destination.write("".trim)
          case ')' => destination.write("".trim)
          case _ => destination.write(w)
        }
        countCh += 1
      } else {
        destination.write(" 0")
      }

    }
    destination.close()
  }
}

object ConvertToDimacs {

  def apply(sentence: CNFSentence, mapping: mutable.Map[String, Int], toFile: File) =  {
    val dmcs = new ConvertToDimacs(sentence, mapping, toFile)
    val s: String = dmcs.keyToVal
    dmcs.writeInDimacsFormat

    //debugging stuff .....
    println(s)
  }

}