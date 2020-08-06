package helper;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public class CalculateBetweennessCentrality implements DegreeVertex {


  // Abstraction function:
  // 如果表示属于g，则映射为一张图
  // 如果表示属于v，则映射为顶点

  // Representation invariant（表示 不变性）:
  // 无

  // Safety from rep exposure（表示暴露的安全性）:
  // 所有的区域块都是私有的。
  // 总之不存在表示暴露。

  private final Graph<Vertex, Edge> graph;
  private final Vertex vertex;


  /*
   * 构造函数
   * 
   * @param 图g，顶点v
   * 
   * @return 无
   */
  public CalculateBetweennessCentrality(Graph<Vertex, Edge> g, Vertex v) {
    this.graph = g;
    this.vertex = v;
  }

  @Override
  public double vertexDegree() {
    return GraphMetrics.betweennessCentrality(graph, vertex);
  }

}
