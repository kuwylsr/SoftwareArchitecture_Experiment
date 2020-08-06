package factory;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public abstract class GraphFactory {

  /*
   * 创建图对象的工厂方法
   * 
   * @param 文件路径字符串
   * 
   * @return 返回图的实例化对象
   */
  public abstract Graph<Vertex, Edge> createGraphUseBuffer(String filePath);
  
  public abstract Graph<Vertex, Edge> createGraphUseReader(String filePath);
  
  public abstract Graph<Vertex, Edge> createGraphUseStream(String filePath);
  
  public abstract Graph<Vertex, Edge> createGraphUseNio(String filePath);
  

}
