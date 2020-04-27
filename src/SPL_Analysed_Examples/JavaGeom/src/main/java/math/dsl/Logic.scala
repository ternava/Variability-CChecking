package math.dsl

/**
  * Created by Tërnava on 6/5/2016.
  */
sealed class Logic(name: String)
case object MND extends Logic("Mandatory")
case object ALT extends Logic("Alternative")
case object OPT extends Logic("Optional")
case object MUL extends Logic("Multi-Coexisting")


