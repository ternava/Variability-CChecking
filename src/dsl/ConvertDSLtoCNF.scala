package dsl

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
  * Created by Tërnava on 2/25/2017.
  */
object ConvertDSLtoPLogic {

  val root: String = "VProot"

  private def convert(lst: List[(String, String, String, String, String, ArrayBuffer[String], String)]) = lst map {
    case (a: String, b: String, c: String, d: String, e: String, y: ArrayBuffer[String], z: String)
    => {
      //s"$root"
      val yy = y.combinations(2).toList
      println(yy)
      (a, c, z) match {
        case (_: String, "MUL", "MND") => s"$a & ($a <=> (${y.mkString(" | ")}))"
        case (_: String, "MUL", "OPT")  => s"($a => $root) & ($a <=> (${y.mkString(" | ")}))"
        case (_: String, "ALT", "MND") => s"$a & ($a <=> (${y.mkString(" | ")})) & ${
          y.combinations(2).map { case Seq(x, y) => s"(~$x | ~$y)" }.mkString(" & ")}"
        case (_: String, "ALT", "OPT") => s"($a => $root) & ($a <=> (${y.mkString(" | ")})) & ${
          y.combinations(2).map { case Seq(x, y) => s"(~$x | ~$y)" }.mkString(" & ")}"
        case (_: String, "ALT", "NESTED") => s"($a <=> (${y.mkString(" | ")})) & ${
          y.combinations(2).map { case Seq(x, y) => s"(~$x | ~$y)" }.mkString(" & ")}"
        case (_: String, "MUL", "NESTED") => s"$a & ($a <=> (${y.mkString(" | ")}))"
        case (_: String, "ALT", "TECHNICAL") => s"($a <=> (${y.mkString(" | ")})) & ${
          y.combinations(2).map { case Seq(x, y) => s"(~$x | ~$y)" }.mkString(" & ")}"
        case (_: String, "MUL", "TECHNICAL") => s"$a & ($a <=> (${y.mkString(" | ")}))"
        case (_: String, "OPT", "") => s"($a => $root)"
      }
    }

    case _ => println("This conversion if not applicable! Check the only available conversions, till now!")
  }

  def apply(lst: List[(String, String, String, String, String, ArrayBuffer[String], String)]) = {
    convert(lst).mkString(" & ")
  }


}
