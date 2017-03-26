package dsl

/**
  * Created by Tërnava on 6/5/2016.
  */

class Mechanism(name: String) {
  //def with_binding(builder: MechanismBuilder) = apply(builder)
  def apply(builder: MechanismBuilder) = {}
}

/* The other techniques can be added! */
case object INHERITANCE extends Mechanism("Inheritance")
case object OVERRIDING extends Mechanism("Overriding")
case object STRATEGY_P extends Mechanism("StrategyPattern")
case object PARAMETER extends Mechanism("Runtime Parameter")


sealed trait Evolve
case object OPEN extends Evolve
case object CLOSE extends Evolve


sealed trait BindingTime
case object COMPILE_TIME extends BindingTime
case object RUN_TIME extends BindingTime
case object LINK_TIME extends BindingTime
case object PROGRAMMING_TIME extends BindingTime