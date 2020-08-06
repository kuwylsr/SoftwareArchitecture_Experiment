package graph;

import edge.Edge;
import factory.SocialNetworkFactory;
import java.util.Iterator;
import vertex.Vertex;

public class SocialNetwork extends ConcreteGraph {

  private String graphType;
  private String graphName;
  private String vertexType;
  private String edgeType;



  public SocialNetwork create_SocialNetwork(String filepath) {
    SocialNetwork graph = new SocialNetworkFactory().createGraphUseBuffer(filepath);
//    checkRep();
    return graph;
  }

  /*
   * 设置图的类型
   * 
   * @param 要设置的GraphType
   * 
   * @return 无
   */
  public String set_GraphType(String a) {
    this.graphType = a;
    return this.graphType;
  }

  /*
   * 返回图的名字
   * 
   * @param 要设置的GraphName
   * 
   * @return 无
   */
  public String set_GraphName(String a) {
    this.graphName = a;
    return this.graphName;
  }

  /*
   * 返回图中边的类型
   * 
   * @param 要设置的EdgeType
   * 
   * @return 无
   */
  public String set_EdgeType(String a) {
    this.edgeType = a;
    return this.edgeType;
  }

  /*
   * 设置图中顶点的类型
   * 
   * @param 要设置的VertexType
   * 
   * @return 无
   */
  public String set_VertexType(String a) {
    this.vertexType = a;
    return this.vertexType;
  }

  /*
   * 返回图的类型
   * 
   * @param 无
   * 
   * @return 返回图的类型
   */
  public String get_GraphType() {
    return this.graphType;
  }

  /*
   * 返回图的名字
   * 
   * @param 无
   * 
   * @return 返回图的名字
   */
  public String get_GraphName() {
    return this.graphName;
  }

  /*
   * 返回图中边的类型
   * 
   * @param 无
   * 
   * @return 返回图中边的类型
   */
  public String get_EdgeType() {
    return this.edgeType;
  }

  /*
   * 返回图中顶点的类型
   * 
   * @param 无
   * 
   * @return 返回图中顶点的类型
   */
  public String get_VertexType() {
    return this.vertexType;
  }

  @Override
  public boolean removeVertex(Vertex v) {
    try {
      if (v == null) { // test pre-condition
        throw new IllegalArgumentException("前置条件不满足！");
      }
    } catch (IllegalArgumentException e) {
      return false;
    }
    Iterator<Edge> it = edges().iterator();
    if (addVertex(v)) {
      vertices().remove(v);
      checkRep();
      return false;
    } else {
      while (it.hasNext()) {
        Edge e = it.next();
        if (e.containVertex(v)) {
          double wa = e.get_weight();
          for (Edge e1 : edges()) {
            double wb = e1.get_weight();
            e1.set_weight(wb / (1 - wa));
          }
          it.remove();
        } else {
          continue;
        }
      }
      vertices().remove(v);
      checkRep();
      return true;
    }
  }

  @Override
  public boolean addEdge(Edge edge) {
//    checkRep();
//    try {
//      if (edge == null) { // test pre-condition
//        throw new IllegalArgumentException("前置条件不满足！");
//      }
//    } catch (IllegalArgumentException e) {
//      return false;
//    }
//    for (Edge e : edges()) {
//      if (e.equals(edge)) {
//        checkRep();
//        return false;
//      } else {
//        continue;
//      }
//    }
//    double wa = edge.get_weight();
//    for (Edge e1 : edges()) {
//      double wb = e1.get_weight();
//      e1.set_weight(wb * (1 - wa));
//    }
    edges().add(edge);
//    checkRep();
    return true;
  }

  @Override
  public boolean removeEdge(Edge edge) {
//    checkRep();
    try {
      if (edge == null) { // test pre-condition
        throw new IllegalArgumentException("前置条件不满足！");
      }
    } catch (IllegalArgumentException e) {
      return false;
    }
    Iterator<Edge> it = edges().iterator();
    while (it.hasNext()) {
      Edge e = it.next();
      if (e.equals(edge)) {
        double wa = e.get_weight();
        for (Edge e1 : edges()) {
          double wb = e1.get_weight();
          e1.set_weight(wb / (1 - wa));
        }
        it.remove();
        checkRep();
        return true;
      } else {
        continue;
      }
    }
    checkRep();
    return false;
  }

  @Override
  public String toString() {
    String a = "The vertices:" + "\n";
    for (Vertex v : vertices()) {
      a = a + v.toString();
    }
    a = a + "\n" + "_________________________________________________________________" + "\n" + "\n"
        + "The edge:" + "\n";
    for (Edge e : edges()) {
      a = a + e.toString();
    }
    return a;
  }

  @Override
  protected void checkRep() {
//    for (Edge e : edges()) {
//      // for(Vertex x: e.sourceVertices()) {
//      // assert contain(x, vertices());
//      // }for(Vertex x: e.targetVertices()) {
//      // assert contain(x, vertices());
//      // }
//      assert e.get_weight() > 0 && e.get_weight() - 1 < Math.pow(10, -5);
//    }
//    double sum = 0;
//    for (Edge e : edges()) {
//      sum += e.get_weight();
//    }
//    assert Math.abs(1 - sum) < Math.pow(10, -4) || sum == 0;

  }

}
