package math.dsl_old

/**
  * Created by botek on 6/5/2016.
  */
sealed abstract class Logic(name: String)
case object ALT extends Logic("Alternative")
case object OPT extends Logic("Optional")
case object MUL extends Logic("Multi-Coexisting")
