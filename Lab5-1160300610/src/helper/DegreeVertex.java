package helper;

import edge.Edge;
import graph.Graph;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import vertex.Vertex;

public interface DegreeVertex {

  /*
   * 计算不同种类的度的通用方法
   * 
   * @param 无
   * 
   * @return 不同类型的度
   */
  public double vertexDegree();

  /**
   * 计算图 g 的总体 degree centrality
   * 
   * @param 图g
   * 
   * @return 图 g 的总体 degree centrality
   */
  public static double degreeCentrality(Graph<Vertex, Edge> g) {
    double s = 0;
    for (Vertex v : g.vertices()) {
      Map<Vertex, List<Double>> map = g.sources(v);
      Set<Vertex> vertex = map.keySet();
      double vertexSumSource = vertex.size();
      double vertexSum = g.vertices().size() - 1;
      s += vertexSumSource / vertexSum;
    }
    return s;
  }

  /**
   * 计算图 g 中节点 v 的 indegree centrality
   * 
   * @param 图g，点v
   * 
   * @return 图 g 中节点 v 的 indegree centrality
   */
  public static double inDegreeCentrality(Graph<Vertex, Edge> g, Vertex v) {
    Map<Vertex, List<Double>> map = g.sources(v);
    Set<Vertex> vertex = map.keySet();
    double vertexSumSource = vertex.size();
    double vertexSum = g.vertices().size();
    return vertexSumSource / vertexSum;
  }

  /**
   * 计算图 g 中节点 v 的 outdegree centrality
   * 
   * 
   * @param 图g，点v
   * 
   * @return 图 g 中节点 v 的 outdegree centrality
   */
  public static double outDegreeCentrality(Graph<Vertex, Edge> g, Vertex v) {
    Map<Vertex, List<Double>> map = g.targets(v);
    Set<Vertex> vertex = map.keySet();
    double vertexSumSource = vertex.size();
    double vertexSum = g.vertices().size();
    return vertexSumSource / vertexSum;
  }

  /**
   * 对路径数组进行处理
   * 
   * @param 起点x1，终点x2，逆路径数组V，处理后的数组v
   * 
   * @return 无
   */
  public static void handle(Vertex x1, Vertex x2, Vertex[] vertex, int[] v) {
    int temp = x2.get_title();
    int[] vv = new int[vertex.length];
    for (int i = 0;; i++) {
      vv[i] = temp;
      if (temp == x1.get_title()) {
        vv[i] = -1;
        break;
      }
      temp = vertex[temp].get_title();
    }
    for (int i = 0; vv[i + 1] != -1; i++) {
      v[i] = vv[i + 1] + 1; // 因为 没有被赋值的数组默认为0，这与点的编号0冲突，所以加1来与其区分
    }
  }

  /**
   * 看数组中是否包含title
   * 
   * @param 数组a，带查找title
   * 
   * @return 数组中是否存在title
   */
  public static boolean contain(int[] a, int title) {
    for (int i = 0; i < a.length; i++) {
      if (a[i] == title) {
        return true;
      } else {
        continue;
      }
    }
    return false;
  }

  /**
   * 节点 start 和 end 之间的最短距离（广度搜索）
   * 
   * @param 图 g，起点start，终点end
   * 
   * @return 返回节点 start 和 end 之间的最短距离
   */
  public static double distance(Graph<Vertex, Edge> g, Vertex start, Vertex end) {
    int[] distance = new int[g.vertices().size()];
    int i = 0;
    Iterator<Vertex> it = g.vertices().iterator();
    while (it.hasNext()) { // 将每个人都设置为未被访问过
      Vertex p = it.next();
      p.set_title(i);
      p.set_visited(false);
      distance[p.get_title()] = 0;
      i++;
    }
    Queue<Vertex> queue = new LinkedList<>();
    queue.offer(start);
    start.set_visited(true);
    while (!queue.isEmpty()) {
      Vertex temp = queue.poll();

      if (start.get_title() == end.get_title()) {
        return 0;
      }
      Map<Vertex, List<Double>> map = g.targets(temp);
      for (Map.Entry<Vertex, List<Double>> entry : map.entrySet()) {
        Vertex p1 = entry.getKey();
        if (!p1.get_visited()) {
          queue.offer(p1);
          distance[p1.get_title()] = distance[temp.get_title()] + 1;
          p1.set_visited(true);
          if (p1.getLabel().equals(end.getLabel())) {
            return distance[p1.get_title()];
          }
        }
      }
    }
    return -1;
  }

  /**
   * 计算图 g 中节点 v 的 偏心度
   * 
   * @param 图g，点v
   * 
   * @return 图 g 中节点 v 的 偏心度
   */
  public static double eccentricity(Graph<Vertex, Edge> g, Vertex v) {
    int vertexSum = g.vertices().size();
    double[] a = new double[vertexSum];
    int i = 0;
    for (Vertex vv : g.vertices()) {
      a[i] = GraphMetrics.distance(g, vv, v);
      i++;
    }
    Arrays.sort(a);
    return a[i - 1];
  }

  /**
   * 计算图 g 的半径
   * 
   * @param 图g
   * 
   * @return 图 g 的半径
   */
  public static double radius(Graph<Vertex, Edge> g) {
    int vertexSum = g.vertices().size();
    double[] a = new double[vertexSum];
    int i = 0;
    for (Vertex v : g.vertices()) {
      a[i] = GraphMetrics.eccentricity(g, v);
      i++;
    }
    Arrays.sort(a);
    return a[0];
  }

  /**
   * 计算图 g 的直径
   * 
   * @param 图g
   * 
   * @return 图 g 的直径
   */
  public static double diameter(Graph<Vertex, Edge> g) {
    int vertexSum = g.vertices().size();
    double[] a = new double[vertexSum];
    int i = 0;
    for (Vertex v : g.vertices()) {
      a[i] = GraphMetrics.eccentricity(g, v);
      i++;
    }
    Arrays.sort(a);
    return a[i - 1];
  }
}
