package SPL_Analysed_Examples.GraphSPL.TemplatePattern

class Vertex(val label: Char) {
  var wasVisited: Boolean = false
  def printvertex = println(label)
}
class Edge(val source: Vertex, val destination: Vertex) {
  def printedge: Unit = println(source.label + ", " + destination.label)
  var weight: Int = 1
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
  var vertices: List[Vertex] = Nil
  var edges: List[Edge] = Nil
  var adjMatrix: Array[Array[Int]] = null

  def addvertex(lblvertex: Char) = {
    val vertex = new Vertex(lblvertex)
    vertices = vertex :: vertices
    vertex
  }
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
    adjMatrix(vertices.indexOf(e.source))(vertices.indexOf(e.destination)) = e.weight
  }

}
//runtime: example - class ConcreteGraph(val weighted: Boolean) extends Graph {
abstract class Graphs extends Graph {
   def weight()
  //var weightx: Boolean = false
  /* start: strategy pattern 
   * Directed and Undirected features */
  def adddirectededge(s: Vertex, d: Vertex, w: Int) = {
    val edge = new Edge(s, d)
    
    /* Directed and Weighted features*/  
    weight()
    
    edges = edge :: edges
    addtoadjacencymatrix(edge)
  }

  /*Undirected and Unweighted features*/
  def addundirectededge(s: Vertex, d: Vertex, w: Int) = {
    val edge1 = new Edge(s, d)
    val edge2 = new Edge(d, s)
    
    /*Undirected and Weighted features*/
    weight()

    edges = edge1 :: edges
    edges = edge2 :: edges
    addtoadjacencymatrix(edge1)
    addtoadjacencymatrix(edge2)
  }

  def addedge(callback: (Vertex, Vertex, Int) => Unit,
              x: Vertex, y: Vertex, w: Int = 1) = callback(x, y, w)
}

class Weighted(w: Int) extends Graphs {
  def weight() = {
    //adddirectededge.w = w
  }
}

object TemplatePatternTest {

  def main(args: Array[String]) {

    /*val g = new ConcreteGraph
    val v1 = g.addvertex('A')
    val v2 = g.addvertex('B')
    val v3 = g.addvertex('C')
    val v4 = g.addvertex('D')
    
 
    g.printvertices()
   
    g.addedge(g.addundirectededge, v2, v3, 5)
    g.printedges()
    g.printAdjacencyMatrix()
    */
  }
}