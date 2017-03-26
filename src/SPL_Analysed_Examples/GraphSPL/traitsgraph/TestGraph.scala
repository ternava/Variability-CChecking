package SPL_Analysed_Examples.GraphSPL.traitsgraph

import Declarations._

class Vertex(val label: Char) {
  var wasVisited: Boolean = false
  def printvertex = println(label)
  var color: String = "WHITE"
  var parent: Vertex = null
  var number: Int = 0
  var neighbours: List[Edge] = List()
  var id: Int = 0
}
class Edge(val source: Vertex, val destination: Vertex) extends Ordered[Edge] {
  var weight: Int = 1
  def printedge: Unit = println(source.label + ", " + destination.label + ", " + weight)
  def compare(that : Edge) = that.weight compare weight
}

abstract class GraphA {
  var vertices: List[Vertex]
  var edges: List[Edge]
  var adjMatrix: Array[Array[Int]]
  def addvertex(lblvertex: Char): Vertex
  def addtoadjacencymatrix(e: Edge)
  def printvertices()
  def printedges()
  def printAdjacencyMatrix()
}

abstract class Graph extends GraphA {
  import scala.collection.mutable.HashMap
  val TYPE: String
  var vertices: List[Vertex] = Nil
  var edges: List[Edge] = Nil
  var adjMatrix: Array[Array[Int]] = null
  
  val adjList = new HashMap[Vertex, Vector[Vertex]] withDefaultValue Vector.empty
  
  def addvertex(lblvertex: Char) = {
    val vertex = new Vertex(lblvertex)
    vertices = vertex :: vertices
    vertex
  }

  //the use of overloading for variability
  /*Unweighted Graph */
  def addedge(s: Vertex, d: Vertex): Unit
  //Weighted Graph */
  def addedge(s: Vertex, d: Vertex, w: Int): Unit

  def printvertices() = {
    for (v <- vertices.reverse) v.printvertex
  }
  def printedges() = {
    for (e <- edges.reverse) e.printedge
  }
  def printAdjacencyMatrix() = {
    println(adjMatrix.deep.mkString("\n"))
  }

  def addtoadjacencymatrix(e: Edge) = {
    if (adjMatrix == null) {
      adjMatrix = Array.ofDim[Int](vertices.size, vertices.size)
    }
    //println(vertices.indexOf(e.source) + ", " + vertices.indexOf(e.destination))
    adjMatrix(vertices.indexOf(e.source))(vertices.indexOf(e.destination)) = e.weight
  }
    
 def getUnvisitedChildNode(tobevisited: Vertex): Vertex = {
    print("to be visited: "); tobevisited.printvertex
    for (i <- 0 to vertices.size - 1) {
      if (adjMatrix(vertices.indexOf(tobevisited))(i) != 0 &
       vertices(i).wasVisited == false) {
        //vertices(i).color == "WHITE")
        return vertices(i)
      }
    }
    return null
  }
 
  def getUnvisitedChild(tobevisited: Vertex): List[Vertex] = {
    print("to be visited: "); tobevisited.printvertex
    var vrtx: List[Vertex] = List()
    for (i <- 0 to vertices.size - 1) {
      if (adjMatrix(vertices.indexOf(tobevisited))(i) != 0 &
       vertices(i).wasVisited == false) {
        //vertices(i).color == "WHITE")
        vrtx = vertices(i) :: vrtx
       
      } 
    }
     return vrtx
  }

}

/*
case class Delegator() {
  private val delegate = Delegate()
  def print = delegate.printer
  override def toString = "Delegator"
}

case class Delegate() {
  def printer = println("Hello World!")
}

*/


/*start: Factory pattern*/
/*The use of overloading*/

  @feature("directed")
  private class Directed extends Graph {
    val TYPE = "directed"
    @feature("unweighted")
    def addedge(s: Vertex, d: Vertex) =
      {
        val edge = new Edge(s, d)
        edges = edge :: edges
        addtoadjacencymatrix(edge)
        s.neighbours = edge :: s.neighbours
      }
    
    @feature("weighted")
    def addedge(s: Vertex, d: Vertex, w: Int) =
      {
        val edge = new Edge(s, d)
        edge.weight = w
        edges = edge :: edges
        addtoadjacencymatrix(edge)
        s.neighbours = edge :: s.neighbours
      }
    
   
  }

  @feature("undirected")
  private class Undirected extends Graph {
    val TYPE = "undirected"
    @feature("unweighted")
    def addedge(s: Vertex, d: Vertex) = {
      val edge1 = new Edge(s, d)
      val edge2 = new Edge(d, s)
      edges = edge1 :: edges
      edges = edge2 :: edges
      addtoadjacencymatrix(edge1)
      addtoadjacencymatrix(edge2)
      s.neighbours = edge1 :: s.neighbours
      d.neighbours = edge1 :: d.neighbours
      s.neighbours = edge2 :: s.neighbours
      d.neighbours = edge2 :: d.neighbours
    }
    
    @feature("weighted")
    def addedge(s: Vertex, d: Vertex, w: Int) = {
      val edge1 = new Edge(s, d)
      val edge2 = new Edge(d, s)
      edge1.weight = w
      edge2.weight = w
      edges = edge1 :: edges
      edges = edge2 :: edges
      addtoadjacencymatrix(edge1)
      addtoadjacencymatrix(edge2)
      s.neighbours = edge1 :: s.neighbours
      d.neighbours = edge1 :: d.neighbours
      s.neighbours = edge2 :: s.neighbours
      d.neighbours = edge2 :: d.neighbours
    }

  }
object Graph {
  
  //the 'factory' method
  @bindsetfunctionality(Set("directed", "undirected"), "alternative")
  def apply(kind: String): Graph = kind match {
    case "directed" => return new Directed
    case "undirected" => return new Undirected
    case _ => throw new RuntimeException("the graph can be only directed or undirected!")
  }
}
/*end: Factory Pattern*/

object TestGraph {

  def main(args: Array[String]) {

    val g = Graph("directed")
    val v1 = g.addvertex('A')
    val v2 = g.addvertex('B')
    val v3 = g.addvertex('C')
    val v4 = g.addvertex('D')
    val v5 = g.addvertex('E')
    val v6 = g.addvertex('F')
    val v7 = g.addvertex('G')

    g.printvertices()

    /*val e1 = g.addedge(v1, v2)
    val e2 = g.addedge(v2, v4)
    val e3 = g.addedge(v4, v3)
    val e4 = g.addedge(v3, v2)
    val e5 = g.addedge(v3, v5)*/
    
    /*val e0 = g.addedge(v1, v1)
    val e1 = g.addedge(v1, v2)
    val e2 = g.addedge(v3, v2)
    val e3 = g.addedge(v2, v4)
    val e4 = g.addedge(v4, v5)
    val e5 = g.addedge(v5, v6)
    val e6 = g.addedge(v6, v7)
    val e7 = g.addedge(v7, v5)
    val e8 = g.addedge(v4, v3) */
    
    val e1 = g.addedge(v1, v2, 6)
    val e2 = g.addedge(v4, v1, 4)
    //val e3 = g.addedge(v2, v3, 10)
    val e4 = g.addedge(v2, v4, 7)
   // val e5 = g.addedge(v2, v5, 7)
    //val e6 = g.addedge(v3, v4, 8)
    val e7 = g.addedge(v3, v5, 5)
    //val e8 = g.addedge(v3, v6, 6)
    //val e9 = g.addedge(v4, v5, 12)
    //val e10 = g.addedge(v5, v6, 7)
    val v11 = g.addedge(v6, v7)
    


    g.printedges()
    g.printAdjacencyMatrix()

   // val search = new Search with DetectCycle
   // search.search(search.DFS, g, v1)
    
    /*val mst = new MST(g)
    
    val spanningtree = mst.kruskalAlgorithm()
    
    println("------ The spanning tree -------")
    for(spt <- spanningtree) spt.printedge */
    
    //val prim = new Prims
    //val rez = prim.prim(g, v1)
    //for(r <- rez) r.printedge
    
    
    
    //val p_spanningtree = prim.spanningtree
    
    //println("------ The spanning tree -------")
    //for(p <- p_spanningtree) p.printedge
    
    
    
    
    //val primes = Algorithms.MSTprimes(g, v1)
    //val hascycle = new Cycle(g, v1)
    
    //val algorithm = new Algorithms
    //algorithm.MST(g, v1)
    //println("Has cycle? : " + algorithm.containsCycle(g))
    
   // MSTPrims.prims(g, v1)
    
    val cc = new ConnectedComponent(g)
    
    val dfs = new DFS with DetectCycle
    
    val scc = new StronglyConnected(g)
    if(scc.connected(v1, v4)) {
      println(v1.label + " is in a connected component with " + v3.label)
    }
    
    
  }

}