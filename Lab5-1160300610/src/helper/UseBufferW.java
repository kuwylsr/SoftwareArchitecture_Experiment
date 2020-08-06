package helper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import edge.Edge;
import edge.SameMovieHyperEdge;
import graph.ConcreteGraph;
import vertex.Vertex;

public class UseBufferW implements WriteFile {


  private final ConcreteGraph graph;
  private final String filePath;
  private long begin = 0; // 开始时间
  private long end = 0; // 结束时间

  public UseBufferW(ConcreteGraph graph, String filePath) {
    this.graph = graph;
    this.filePath = filePath;
  }
  
  /**
   * 获取运行时间
   * @return 运行时间
   */
  public long getTime() {
    return (this.end-this.begin);
  }

  @Override
  public boolean WriteGraphToFile() {
    FileOutputStream fs = null;
    BufferedOutputStream fb = null;
    try {
      fs = new FileOutputStream(new File(filePath));
      fb = new BufferedOutputStream(fs);
      begin = System.currentTimeMillis();
      String sourceGraphType = "GraphType = \"" + graph.get_GraphType() + "\"" + "\r\n";
      fb.write(sourceGraphType.getBytes());
      String sourceGraphName = "GraphName = \"" + graph.get_GraphName() + "\"" + "\r\n";
      fb.write(sourceGraphName.getBytes());
      String sourceVertexType1 = graph.get_VertexType().replaceAll(" ", "\", \"");
      String sourceVertexType = "VertexType = \"" + sourceVertexType1 + "\"" + "\r\n";
      fb.write(sourceVertexType.getBytes());
      for (Vertex v : graph.vertices()) {
        String imfor = " ";
        String info[] = v.get_VertexInfo();
        if (info == null) {
          imfor = imfor.trim();
        } else {
          imfor = ", <";
          for (int i = 0; i < info.length; i++) {
            imfor = imfor + "\"" + info[i] + "\"";
            if (i + 1 < info.length) {
              imfor = imfor + ", ";
            }
          }
          imfor = imfor + ">";
        }
        String sourceVertex = "Vertex = <\"" + v.getLabel() + "\"" + ", " + "\""
            + v.getClass().getSimpleName() + "\"" + imfor + ">" + "\r\n";
        fb.write(sourceVertex.getBytes());
      }
      String sourceEdgeType1 = graph.get_EdgeType().replaceAll(" ", "\", \"");
      String sourceEdgeType = "EdgeType = \"" + sourceEdgeType1 + "\"" + "\r\n";
      fb.write(sourceEdgeType.getBytes());
      for (Edge e : graph.edges()) {
        if (e instanceof SameMovieHyperEdge) {
          String sourceEdge = "HyperEdge = <\"" + e.get_label() + "\"" + ", " + "\""
              + e.getClass().getSimpleName() + "\"" + ", "
              + ((SameMovieHyperEdge) e).sourceVertices().toString() + ">" + "\r\n";
          fb.write(sourceEdge.getBytes());
        } else {
          String sourceEdge = "Edge = <\"" + e.get_label() + "\"" + ", " + "\""
              + e.getClass().getSimpleName() + "\"" + ", " + "\"" + e.get_weight() + "\"" + ", "
              + e.vertices().toString() + ", " + "\"" + e.get_point1() + "\"" + ">" + "\r\n";
          fb.write(sourceEdge.getBytes());
        }
      }
      end = System.currentTimeMillis();
      System.out.println("BufferedOutputStream执行耗时:" + (end - begin) + " 毫秒");
      fb.flush();
      fb.close();
      System.out.println("写入文件成功！");
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }
  }

}
