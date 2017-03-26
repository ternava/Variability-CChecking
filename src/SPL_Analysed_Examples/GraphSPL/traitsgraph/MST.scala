package SPL_Analysed_Examples.GraphSPL.traitsgraph

/**
 * @author ternava
 */
class MST(g: Graph) {

  private class DisjointSet {

    //creating a disjoint-set for each vertex
    def createdisjointsets() = {
      for (v <- g.vertices) v.parent = v
    }
    //finds the representative of the set that i is an element of
    def find(i: Vertex): Vertex = {
      if (i.parent == i) return i
      else return find(i.parent)
    }
    //unite two found disjoint-sets
    def union(i: Vertex, j: Vertex) = {
      val irep: Vertex = find(i)
      val jrep: Vertex = find(j)
      irep.parent = jrep
    }

  }

  def kruskalAlgorithm(): List[Edge] = {

    val ds = new DisjointSet()
    ds.createdisjointsets()
    var spanningtree: List[Edge] = List()
    val weights_sorted = g.edges sortBy (_.weight)
    var i: Int = 0

    while (i != weights_sorted.size & spanningtree.size != g.vertices.size - 1) {
      val e: Edge = weights_sorted(i)
      if (ds.find(e.source) != ds.find(e.destination)) {
        spanningtree = e :: spanningtree
        ds.union(e.source, e.destination)
      }
      i += 1
    }
    return spanningtree
  }

}
