package basic

import logic.propositional._

/**
  * Created by Tërnava on 2/21/2017.
  */
object ConvertToCNF {
  private val PLP = PropositionalLogicParser
  def apply(sentence: String): CNFSentence = SentenceToCNF(PLP.parse(sentence))
}

object add {
  def apply(ps: PropositionSymbol, dimacsVal: Int) = {
    (ps.key, dimacsVal)
  }
}