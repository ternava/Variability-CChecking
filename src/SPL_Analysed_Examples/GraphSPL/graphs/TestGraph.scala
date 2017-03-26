package SPL_Analysed_Examples.GraphSPL.graphs

import scala.collection.mutable.Map

object Conf {
  final val DIRECTED: Boolean = false
  final val WEIGHTED: Boolean = false
}

class Vertex(val label: Char) {
  var wasVisited: Boolean = false
  def printvertex = println(label)
}
class Edge(val source: Vertex, val destination: Vertex) {
  def printedge = println(source.label + ", " + destination.label)
  class Weight {
    val weight: Int = 0
  }
}

//---------------------------------------------------------------------
abstract class EdgeType(val source: Vertex, val destination: Vertex) {
  def printedge
}
class DirectedEdge(s: Vertex, d: Vertex) extends EdgeType(s, d) {
  def printedge = println(source.label + ", " + destination.label)
}
class UndirectedEdge(s: Vertex, d: Vertex) extends EdgeType(s, d) {
  def printedge = {
    println(source.label + ", " + destination.label)
    println(destination.label + ", " + source.label)
  }
}

//---------------------------------------------------------------------

abstract class GraphA {
  var vertices: List[Vertex]
  var edges: List[Edge]
  var adjMatrix: Array[Array[Int]]

  def addvertex(lblvertex: Char): Vertex
  def addedge(s: Vertex, d: Vertex)
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

}

class ConcreteGraph extends Graph {

  def addedge(s: Vertex, d: Vertex) = {

    if (Conf.DIRECTED) {
      val edge = new Edge(s, d)
      edges = edge :: edges
      addtoadjacencymatrix(edge)
    } 
    else {      
      val edge1 = new Edge(s, d)
      val edge2 = new Edge(d, s)      
      edges = edge1 :: edges
      edges = edge2 :: edges
      addtoadjacencymatrix(edge1)
      addtoadjacencymatrix(edge2)
    }

  }

  def addtoadjacencymatrix(e: Edge) = {
    if (adjMatrix == null) {
      adjMatrix = Array.ofDim[Int](vertices.size, vertices.size)
    }
    
    if(Conf.DIRECTED) {
      adjMatrix(vertices.indexOf(e.source))(vertices.indexOf(e.destination)) = 1
    }
    else {
      adjMatrix(vertices.indexOf(e.source))(vertices.indexOf(e.destination)) = 1
      adjMatrix(vertices.indexOf(e.destination))(vertices.indexOf(e.source)) = 1
    }
  }

}

object TestGraph {

  def main(args: Array[String]) {

    val g = new ConcreteGraph
    val v1 = g.addvertex('A')
    val v2 = g.addvertex('B')
    val v3 = g.addvertex('C')
    val v4 = g.addvertex('D')

    g.printvertices()

    g.addedge(v1, v2)
    g.addedge(v2, v3)
    g.addedge(v1, v3)

    g.printedges()
    g.printAdjacencyMatrix()

  }

}