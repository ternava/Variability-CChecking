package SPL_Analysed_Examples.GraphSPL.traitsgraph

/**
 * @author Xhevahire Tërnava
 */
class StronglyConnected(graph: Graph) extends ConnectedComponent(graph) {
  def connected(v: Vertex, w: Vertex): Boolean = {
    return (v.id == w.id)
  }
}


class ConnectedComponent(graph: Graph) {
  val dfs2 = new DFS
  //var marked: List[Boolean] = List()
  //var id: List[Int] = List()
  var elements: List[Vertex] = List()
  var count: Int = 0

  for (v <- graph.vertices) {
    if (!v.wasVisited) {
      //count variable is introduced because of the interaction between two features DFS and ConnectedComponents
      dfs2.dfs(graph, v, count) //dfs(graph, v)     //for(e <- elements) e.printvertex
      count += 1
    }
  }

  for (i <- 0 to graph.vertices.size - 1)
    graph.vertices(i).wasVisited = false

}

class Prims() {

  def prim(graph: Graph, vertex: Vertex): List[Edge] = {

    // Prim's algorithm only works on undirected graphs
    if (graph.TYPE == "directed")
      throw (new IllegalArgumentException("Undirected graphs only."));

    import scala.util.Random
    import scala.collection.mutable.PriorityQueue

    val startNode = vertex

    var mst_nodes = List(startNode)
    var mst_edges = List[Edge]()

    val pq = new PriorityQueue[Edge]

    startNode.neighbours.foreach(e => pq.enqueue(e))

    while (!pq.isEmpty) {
      val edge = pq.dequeue

      if (!(mst_nodes contains edge.destination)) {
        mst_nodes = edge.destination :: mst_nodes
        mst_edges = edge :: mst_edges

        edge.destination.neighbours.foreach(e => pq.enqueue(e))
      }
    }

    mst_edges
  }

}

import scala.collection.mutable.HashSet

trait DetectCycle {
  //self: Search =>
 self: DFS =>

  override def detectCycle(graph: Graph, vertex: Vertex): Boolean = {
    print("to be checked: "); vertex.printvertex
    for (i <- 0 to graph.vertices.size - 1) {
      if (graph.adjMatrix(graph.vertices.indexOf(vertex))(i) != 0 &
        graph.vertices(i).color == "GREY" &
        vertex.parent != graph.vertices(i))
        return true
    }
    return false
  }

}

object Algorithms {

  def MST(graph: Graph, vertex: Vertex) = {

    var visited: List[Vertex] = Nil
    vertex.wasVisited = true
    visited = vertex :: visited
    vertex.printvertex

    while (visited.nonEmpty) {
      val currentVertex: Vertex = visited.head
      val child: Vertex = graph.getUnvisitedChildNode(currentVertex)
      if (child != null) {
        child.wasVisited = true
        visited = child :: visited
        currentVertex.printvertex
        child.printvertex
        println(" ")
      } else {
        visited = visited.tail
      }
    }

    for (i <- 0 to graph.vertices.size - 1)
      graph.vertices(i).wasVisited = false

  }

}