package SPL_Analysed_Examples.GraphSPL.traitsgraph

/**
 * @author botek
 */
object OtherComented {

/* ----------------------------------------
 *   for(e <- elements) e.printvertex
  
  def dfs(g: Graph, v: Vertex): Unit = {
    v.wasVisited = true
    v.id = count
    v.printvertex; println(v.id)
    for(w <- graph.getUnvisitedChild(v)) {
      if(!w.wasVisited) {
        elements = w :: elements
        dfs(g, w)
      }
    }
 -------------------------------------------*/
  
/* -----------------------------------------
 * def prims(graph: Graph, start: Vertex): List[Edge] = {

    val adjList = graph.edges.groupBy(_.source).mapValues(edges => edges.map(_.destination)).toList

    adjList.foreach(println)

    for (al <- adjList) {
      println(al._1.label)
      for (a1 <- al._2) {
        print(a1.label); print(", ")
      }
      println()
    }

    var vertex = start
    var cost: Int = 0
    var path: List[Edge] = List()
    var unvisited: List[Vertex] = graph.vertices
    var edgesavailable: List[Edge] = List()
    var edgesunvisited: List[Edge] = graph.edges
    
    edgesavailable = edgesavailable sortBy (_.weight)

    unvisited.foreach { println(_) }
    
    while (!unvisited.isEmpty) {
      //println(unvisited.isEmpty)
      for (e <- edgesunvisited) {
        println(e.printedge)
        if ((e.source == vertex | e.destination == vertex) & unvisited.contains(e.destination)) {
          edgesavailable = e :: edgesavailable
        }

      }
        val e: Edge = edgesavailable.head
        path = e :: path
        vertex = e.destination
        unvisited = unvisited.filterNot { m => m == vertex }

    }

    return path

  }
-------------------------------------------- */
  
/* -----------------------------------------
  // Prim's algorithm only works on undirected graphs
  if (graph.TYPE == "directed") 
      throw (new IllegalArgumentException("Undirected graphs only."));
        
  var u: List[Vertex] = List()
  var vu: List[Vertex] = graph.vertices

  u = vertex :: u
  vu = vu.filterNot(m => m == vertex)

  var spanningtree: List[Edge] = List()
  
 /* var dist: Array[Int] = Array()
  var edgess: Array[Edge] = Array()
  for(i <- 0 to graph.vertices.size - 1) {
    dist(i) = 999
    edgess(i) = null
  }
  */
 
  
  
  
  
  //def prim(): List[Edge] = {
    while (vu.nonEmpty) {
      for (e <- graph.edges) {
        print("edges: "); e.printedge
        if (u.contains(e.source) & vu.contains(e.destination)) {
          spanningtree = e :: spanningtree
          //e.printedge
          //for(p <- spanningtree) p.printedge
          u = e.destination :: u
          vu = vu.filterNot(m => m == e.destination)

        }
        else {
          for(p <- spanningtree) p.printedge
        }
      }
    }
   // spanningtree
  //}
  
  //for(p <- spanningtree) p.printedge
}




------------------------------------*/
}