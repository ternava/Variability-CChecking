package SPL_Analysed_Examples.GraphSPL.traitsgraph

/**
 * @author Xhevahire Ternava
 */


class DFS {
  
  def detectCycle(gr: Graph, vr: Vertex): Boolean = false
  
  var elements: List[Vertex] = List()
  
  def dfs(g: Graph, v: Vertex, count: Int = 0): Unit = {
    v.wasVisited = true
    v.id = count
    v.printvertex; println(v.id)
    for(w <- g.getUnvisitedChild(v)) {
      if(!w.wasVisited) {
        elements = w :: elements
        dfs(g, w, count)
      }
    }
  }
  
}

class BFS {
  
  def bfs(g: Graph, v: Vertex): Unit = {
     var q: List[Vertex] = List()
     q = v :: q
     v.wasVisited = true
     while(!q.isEmpty) {
       val v: Vertex = q.last
       for(w <- g.getUnvisitedChild(v)) {
         if(!w.wasVisited) {
           q = w :: q
           w.wasVisited = true
           w.parent = v
         }
       }
     }
  }
  
}


/*----Another implementation---------------------------------*/

class Search {
  
  def detectCycle(gr: Graph, vr: Vertex): Boolean = false
  var giveanumber: Int = 0
  
  def DFS(graph: Graph, vertex: Vertex) = {   
    
    var visited: List[Vertex] = Nil
    
    vertex.wasVisited = true
    vertex.color = "GREY"
    
    visited = vertex :: visited
    vertex.printvertex
    println(vertex.color)


    while (visited.nonEmpty) {
      val v: Vertex = visited.head //peek an element from the List
      println("last: " + v.label)
      
      giveanumber += 1
      vertex.number = giveanumber
      println(vertex.number)
      
      if(detectCycle(graph, v)) {
        println("There is a cycle!")
      }
            
      val child: Vertex = graph.getUnvisitedChildNode(v)
      println("is not null? " + (child != null))
      if (child != null) {

        child.wasVisited = true
        child.color = "GREY"
        child.parent = v
        
        child.printvertex
        println(child.color)

        visited = child :: visited

      } else {
        visited = visited.tail
        v.color = "BLACK"
      }
        
    }

    for (i <- 0 to graph.vertices.size - 1)
      graph.vertices(i).wasVisited = false

  }

  def BFS(graph: Graph, vertex: Vertex) = {
    var visited: List[Vertex] = Nil
    vertex.wasVisited = true
    visited = vertex :: visited
    vertex.printvertex

    while (visited.nonEmpty) {
      val v: Vertex = visited.last
      println("last: " + v.label)

      val child: Vertex = graph.getUnvisitedChildNode(v)
      println("is not null? " + (child != null))
      if (child != null) {
        child.wasVisited = true
        child.printvertex

        visited = child :: visited

      } else
        visited = visited.reverse.tail.reverse
    }

    for (i <- 0 to graph.vertices.size - 1)
      graph.vertices(i).wasVisited = false

  }
  
  /*Strategy pattern*/
  def search(callback: (Graph, Vertex) => Unit,
    g: Graph, v: Vertex) = callback(g, v)

}

