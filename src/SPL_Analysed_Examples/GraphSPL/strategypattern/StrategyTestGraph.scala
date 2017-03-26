package SPL_Analysed_Examples.GraphSPL.strategypattern

import scala.language.dynamics

object Conf {
  /* Variation Point: WEIGHT */
  final val WEIGHTED: Boolean = true
}

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

class ConcreteGraph extends Graph {

  def weighted(edge: Edge, w: Int) = {
    if (Conf.WEIGHTED) {
      edge.weight = w
    }
  }
  def weighted(edge1: Edge, edge2: Edge, w: Int) = {
    if (Conf.WEIGHTED) {
      edge1.weight = w
      edge2.weight = w
    }
  }

  //var weight: Boolean = false
  /* Variant Directed and Unweighted*/
  /*===================================================*/
  def adddirectededge(s: Vertex, d: Vertex, w: Int) = {
    val edge = new Edge(s, d)
    
    /* Variant Weighted*/
    /*-------------------------------------------------*/
     weighted(edge, w)
    //if (Conf.WEIGHTED) {
      //edge.weight = w
    //}
    /*-------------------------------------------------*/
    edges = edge :: edges
    addtoadjacencymatrix(edge)
  }
  /*===================================================*/

  /* Variant Undirected and Unweighted */
  /*===================================================*/
  def addundirectededge(s: Vertex, d: Vertex, w: Int) = {
    val edge1 = new Edge(s, d)
    val edge2 = new Edge(d, s)
    
    /* Variant Weighted */
    /*-------------------------------------------------*/
    weighted(edge1, edge2, w)
    //if (Conf.WEIGHTED) {
      //edge1.weight = w
      //edge2.weight = w
    //}
    /*-------------------------------------------------*/
    edges = edge1 :: edges
    edges = edge2 :: edges
    addtoadjacencymatrix(edge1)
    addtoadjacencymatrix(edge2)
  }
  /*===================================================*/
  /* Variation Point: Type = (Un)Directed */
  def addedge(callback: (Vertex, Vertex, Int) => Unit,
              x: Vertex, y: Vertex, w: Int = 1) = callback(x, y, w)

  /* end: strategy pattern */

  //def compiletimeaddedge(a: Vertex, b: Vertex, c: Int) = addedge(adddirectededge, a, b, c)
}

object StrategyTestGraph {

  def main(args: Array[String]) {

    val g = new ConcreteGraph
    val v1 = g.addvertex('A')
    val v2 = g.addvertex('B')
    val v3 = g.addvertex('C')
    val v4 = g.addvertex('D')
    
  //runtime configuration  
    
  /* val weight = {
      if(args(0) == "w")
        Conf.WEIGHTED = true
      else
        Conf.WEIGHTED = false
    }'[
    
    val createedge: (Vertex, Vertex, Int) => Unit = 
    if (args(1) == "d") 
      g.adddirectededge
    else 
      g.addundirectededge
      

    val e1 = g.addedge(createedge, v2, v3, 3) */
    //end: runtime configuration

    g.printvertices()
   
    //Conf.WEIGHTED = true
    
    /*construction time configuration? */
     //g.weightx = true
     
     g.addedge(g.addundirectededge, v2, v3, 5)

    //g.compiletimeaddedge(v2, v3, 5)
    //g.addedge(g.adddirectededge, v2, v3, 3)

    g.printedges()
    g.printAdjacencyMatrix()

  }
}