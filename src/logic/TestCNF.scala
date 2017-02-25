package logic

import scala.collection.mutable

/**
  * Created by Ternava on 2/19/2017.
  */

object TestCNF {

  private val map = mutable.Map.empty[String, Int]

  map += add(PropositionSymbol("GPL") , 1)
  map += add(PropositionSymbol("Type"), 2)
  map += add(PropositionSymbol("Weight"), 3)
  map += add(PropositionSymbol("Weighted"), 4)
  map += add(PropositionSymbol("Unweighted"), 5)
  map += add(PropositionSymbol("Search"), 6)
  map += add(PropositionSymbol("Algorithms"), 7)
  map += add(PropositionSymbol("Directed"), 8)
  map += add(PropositionSymbol("Undirected"), 9)
  map += add(PropositionSymbol("DFS"), 10)
  map += add(PropositionSymbol("BFS"), 11)
  map += add(PropositionSymbol("CycleChecking"), 12)
  map += add(PropositionSymbol("MST"), 13)
  map += add(PropositionSymbol("ConnectedComp"), 14)
  map += add(PropositionSymbol("StronglyConComp"), 15)
  map += add(PropositionSymbol("Number"), 16)
  map += add(PropositionSymbol("SingleShPath"), 17)
  map += add(PropositionSymbol("MSTPrim"), 18)
  map += add(PropositionSymbol("MSTKruskal"), 19)


  def main(args: Array[String]): Unit = {

    /* Convert a propositional formula in CNF form ---------------------------------------------------------------*/
    val r: CNFSentence = ConvertToCNF("GPL & (Type <=> GPL) & (Weight <=> GPL) & (Search => GPL) & (Algorithms => GPL) &  (Directed => Type) & (Undirected => Type) & (Type => (Directed | Undirected)) & (Type => ~(Directed & Undirected)) &(Weighted => Weight) &(Unweighted => Weight) & (Weight => (Weighted | Unweighted)) & (Weight => ~(Weighted & Unweighted)) & (DFS => Search)& (BFS => Search) & (Search => (DFS | BFS)) & (Search => ~(DFS & BFS)) & (Algorithms <=> (CycleChecking | MST | ConnectedComp | StronglyConComp | Number | SingleShPath)) & (MSTPrim => MST) & (MSTKruskal => MST) & (MST => (MSTPrim | MSTKruskal)) & (MST => ~(MSTPrim & MSTKruskal))")
    /* Convert a CNF formula to .dimacs format, by writing in at a specific file.cnf/.dimacs. --------------------
     * To avoid any accidental overriding, I will keep the file explicit instead of <<new File(args(0))>> --------*/
    //val toFile: File = new File("Test_09.cnf")
    //val dmcs = toDIMACS(r, map, toFile)
    //val s: String = dmcs.keyToVal
    //dmcs.writeInDimacsFormat

    //Debugging stuff....
    // def sf = Source.fromFile("C://Users//botek//workspaceSAT//SATSolver_Example_03//src//analyse_graph_spl//fm_prop_graph_spl.txt")
    //println("from File: " + sf.getLines().toList.toString().trim)
    println(map)
    //val PLP = PropositionalLogicParser
    //val r0: CNFSentence = SentenceToCNF(PLP.parse("GPL & (Type <=> GPL) & (Weight <=> GPL) & (Search => GPL) & (Algorithms => GPL) &  (Directed => Type) & (Undirected => Type) & (Type => (Directed | Undirected)) & (Type => ~(Directed & Undirected)) &(Weighted => Weight) &(Unweighted => Weight) & (Weight => (Weighted | Unweighted)) & (Weight => ~(Weighted & Unweighted)) & (DFS => Search)& (BFS => Search) & (Search => (DFS | BFS)) & (Search => ~(DFS & BFS)) & (Algorithms <=> (CycleChecking | MST | ConnectedComp | StronglyConComp | Number | SingleShPath)) & (MSTPrim => MST) & (MSTKruskal => MST) & (MST => (MSTPrim | MSTKruskal)) & (MST => ~(MSTPrim & MSTKruskal))"))
    //println(r0)
    println(r)
    //println("rez: " + s)
    //println(dmcs.nrVariables)
    //println(dmcs.nrLines)

  }

}
