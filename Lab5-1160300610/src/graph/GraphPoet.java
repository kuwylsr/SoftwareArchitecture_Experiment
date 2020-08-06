package graph;

import edge.Edge;
import factory.GraphPoetFactory;
import java.util.regex.Pattern;
import vertex.Vertex;


public class GraphPoet extends ConcreteGraph {

  private String graphType;
  private String graphName;
  private String vertexType;
  private String edgeType;


  public GraphPoet create_GraphPoet(String filepath) {
    GraphPoet graph = new GraphPoetFactory().createGraphUseBuffer(filepath);
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
    // 边中的顶点，在顶点集中都存在
//    for (Edge e : edges()) {
      // for(Vertex x: e.sourceVertices()) {
      // assert contain(x, vertices());
      // }for(Vertex x: e.targetVertices()) {
      // assert contain(x, vertices());
      // }
      // 诗歌图 满足边的权重为正整数
//      Pattern pattern = Pattern.compile("[0-9]+(.0)?0*");
//      assert pattern.matcher(String.valueOf(e.get_weight())).matches();
//    }

  }


}
