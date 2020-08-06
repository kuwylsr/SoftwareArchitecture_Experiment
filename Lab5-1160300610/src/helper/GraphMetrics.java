package helper;

import edge.Edge;
import graph.Graph;
import graph.GraphPoet;
import graph.SocialNetwork;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import vertex.Vertex;

public class GraphMetrics {



  /**
   * 计算图 g 中节点 v 的 degree centrality
   * 
   * @param 图g，点v
   * 
   * @return v 的 degree centrality
   */
  public static double degreeCentrality(Graph<Vertex, Edge> g, Vertex v) {
    Map<Vertex, List<Double>> map = g.sources(v);
    Set<Vertex> vertex = map.keySet();
    double vertexSumSource = vertex.size();
    double vertexSum = g.vertices().size() - 1;
    return vertexSumSource / vertexSum;
  }

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
   * 计算图 g 中节点 v 的 closeness centrality
   * 
   * @param 图g，点v
   * 
   * @return 图 g 中节点 v 的 closeness centrality
   */
  public static double closenessCentrality(Graph<Vertex, Edge> g, Vertex v) {
    double sum = 0;
    double vertexSum = g.vertices().size() - 1;
    for (Vertex vv : g.vertices()) {
      if (GraphMetrics.distance(g, v, vv) == -1) {
        sum += 0;
      } else {

        sum += GraphMetrics.distance(g, v, vv);
      }
    }
    return (1.0 / sum) * vertexSum;
  }

  /**
   * 计算图 g 中节点 v 的 betweenness centrality
   * 
   * @param 图g，点v
   * 
   * @return 图 g 中节点 v 的 betweenness centrality
   */
  public static double betweennessCentrality(Graph<Vertex, Edge> g, Vertex v) {
    double sum = 0;
    double visitedSum = 0;
    double vertexSum = g.vertices().size();
    int sumV = g.vertices().size();
    if (g instanceof GraphPoet || g instanceof SocialNetwork) {
      sum = vertexSum * (vertexSum - 1);
    } else {
      sum = (vertexSum * (vertexSum - 1)) / 2;
    }
    for (Vertex x1 : g.vertices()) {
      for (Vertex x2 : g.vertices()) {
        Vertex[] vertexlist = new Vertex[sumV];
        int[] title = new int[sumV];
        if (x1.equals(x2)) {
          continue;
        }
        if (GraphMetrics.getDistance(g, x1, x2, vertexlist) == -1) {
          continue;
        }
        handle(x1, x2, vertexlist, title);
        if (contain(title, v.get_title() + 1)) { // 因为 没有被赋值的数组默认为0，这与点的编号0冲突，所以加1来与其区分
          visitedSum++;
        }
      }
    }
    if (visitedSum == 0) {
      return 0;
    }
    return visitedSum / sum;
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
   * ) 计算图 g 中节点 v 的 outdegree centrality
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

  public static double getDistance(Graph<Vertex, Edge> g, Vertex name1, Vertex name2,
      Vertex[] vertexVisited) {
    double[] distance = new double[g.vertices().size()];
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
    queue.offer(name1);
    name1.set_visited(true);
    while (!queue.isEmpty()) {
      Vertex temp = queue.poll();

      if (name1.get_title() == name2.get_title()) {
        return 0;
      }
      Map<Vertex, List<Double>> map = g.targets(temp);
      for (Map.Entry<Vertex, List<Double>> entry : map.entrySet()) {
        Vertex p1 = entry.getKey();
        if (!p1.get_visited()) {
          queue.offer(p1);
          distance[p1.get_title()] = distance[temp.get_title()] + 1;
          vertexVisited[p1.get_title()] = temp;
          p1.set_visited(true);
          if (p1.getLabel().equals(name2.getLabel())) {
            return distance[p1.get_title()];
          }
        }
      }
    }
    return -1;
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

  /*
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

  /*
   * 节点 start 和 end 之间的最短距离（DJ算法）
   * 
   * @param 图 g，起点start，终点end
   * 
   * @return 返回节点 start 和 end 之间的最短距离
   */
  static Set<Vertex> open = new HashSet<Vertex>();
  static Set<Vertex> close = new HashSet<Vertex>();
  static Map<Vertex, Double> path = new HashMap<Vertex, Double>();// 封装路径距离
  static Map<Vertex, Vertex> pathInfo = new HashMap<Vertex, Vertex>();// 封装路径信息


  public static Vertex init(Graph<Vertex, Edge> g, Vertex start) {

    // 初始路径,因没有A->E这条路径,所以path(E)设置为Integer.MAX_VALUE
    for (Vertex v : g.vertices()) {
      if (v.equals(start)) {
        close.add(v);
        continue;
      } else {
        open.add(v);
        // pathInfo.put(v, start);
        path.put(v, Double.MAX_VALUE);
      }
    }
    Map<Vertex, Double> map = get_target(g, start);
    for (Map.Entry<Vertex, Double> entry : map.entrySet()) {
      path.put(entry.getKey(), entry.getValue());
      pathInfo.put(entry.getKey(), start); // pathInfo中存放<后继节点，前驱节点>
    }
    return start;

  }

  public static void computePath(Graph<Vertex, Edge> g, Vertex start) {
    Vertex nearest = getShortestPath(g, start);// 取距离start节点最近的子节点,放入close
    if (nearest == null) {
      return;
    }
    close.add(nearest);
    open.remove(nearest);
    Map<Vertex, Double> map = get_target(g, nearest);
    for (Vertex v : map.keySet()) {
      if (open.contains(v)) { // 如果子节点在open中
        Double newCompute = path.get(nearest) + map.get(v);
        if (path.get(v) > newCompute) { // 之前设置的距离大于新计算出来的距离
          path.put(v, newCompute);
          pathInfo.put(v, nearest); // 更新pathInfo，使得value值 始终是key值的前驱结点
        }
      }
    }
    computePath(g, start);// 重复执行自己,确保所有子节点被遍历
    computePath(g, nearest);// 向外一层层递归,直至所有顶点被遍历
  }

  private static Vertex getShortestPath(Graph<Vertex, Edge> g, Vertex vertex) {
    Vertex res = null;
    double minDis = Double.MAX_VALUE;
    Map<Vertex, Double> map = get_target(g, vertex);
    for (Vertex vv : map.keySet()) {
      if (open.contains(vv)) {
        double distance = map.get(vv);
        if (distance < minDis) {
          minDis = distance;
          res = vv;
        }
      }
    }
    return res;
  }

  public static Map<Vertex, Double> get_target(Graph<Vertex, Edge> g, Vertex vertex) {
    Map<Vertex, Double> map = new HashMap<>();
    for (Edge e : g.edges()) {
      Set<Vertex> s1 = e.sourceVertices();
      Set<Vertex> s2 = e.targetVertices();
      Iterator<Vertex> it1 = s1.iterator();
      Iterator<Vertex> it2 = s2.iterator();
      while (it1.hasNext() && it2.hasNext()) {
        Vertex v1 = it1.next();
        Vertex v2 = it2.next();
        if (v1.equals(vertex)) {
          map.put(v2, e.get_weight());
        }
      }
    }
    return map;
  }

  public void printPathInfo(Vertex end, Vertex[] a) {
    a[0] = end;
    int j = 1;
    for (Map.Entry<Vertex, Vertex> entry : pathInfo.entrySet()) {
      if (end.equals(entry.getKey())) {
        a[j] = entry.getValue();
        end = entry.getValue();
        j++;
      }
    }
    String str = " ";
    for (int i = j - 1; i >= 0; i--) {
      if (i != 0) {
        str = str + a[i].getLabel() + "-->";
      } else {
        str = str + a[i].getLabel();
      }

    }
    System.out.println(str);
  }

  // -========================================================================

  // static Set<Vertex> open=new HashSet<Vertex>();
  // static Set<Vertex> close=new HashSet<Vertex>();
  // static Map<Vertex,Double> path=new HashMap<Vertex,Double>();//封装路径距离
  // static Map<String,String> pathInfo=new HashMap<String,String>();//封装路径信息

  // public static Vertex init(Graph<Vertex, Edge> g, Vertex start) {
  //
  // //初始路径,因没有A->E这条路径,所以path(E)设置为Integer.MAX_VALUE
  // for(Vertex v:g.vertices()) {
  // if(v.equals(start)) {
  // close.add(v);
  // continue;
  // }else {
  // open.add(v);
  // pathInfo.put(v.getLabel(), start.getLabel());
  // path.put(v, Double.MAX_VALUE);
  // }
  // }
  // Map<Vertex,Double> map=get_target(g, start);
  // for(Map.Entry<Vertex,Double> entry : map.entrySet()) {
  // path.put(entry.getKey(), entry.getValue());
  // pathInfo.put(entry.getKey().getLabel(),start.getLabel()+"->"+entry.getKey().getLabel());
  // }
  // return start;
  //
  // }
  // public static void computePath(Graph<Vertex, Edge> g,Vertex start) {
  // Vertex nearest=getShortestPath(g,start);//取距离start节点最近的子节点,放入close
  // if(nearest==null){
  // return ;
  // }
  // close.add(nearest);
  // open.remove(nearest);
  // Map<Vertex,Double> map=get_target(g, nearest);
  // for(Vertex v:map.keySet()){
  // if(open.contains(v)){//如果子节点在open中
  // Double newCompute=path.get(nearest)+map.get(v);
  // if(path.get(v)>newCompute){//之前设置的距离大于新计算出来的距离
  // path.put(v, newCompute);
  // pathInfo.put(v.getLabel(), pathInfo.get(nearest.getLabel())+"->"+v.getLabel());
  // }
  // }
  // }
  // computePath(g,start);//重复执行自己,确保所有子节点被遍历
  // computePath(g,nearest);//向外一层层递归,直至所有顶点被遍历
  // }
  // private static Vertex getShortestPath(Graph<Vertex, Edge> g,Vertex vertex){
  // Vertex res=null;
  // double minDis=Double.MAX_VALUE;
  // Map<Vertex,Double> map=get_target(g, vertex);
  // for(Vertex vv:map.keySet()){
  // if(open.contains(vv)){
  // double distance=map.get(vv);
  // if(distance<minDis){
  // minDis=distance;
  // res=vv;
  // }
  // }
  // }
  // return res;
  // }
  //
  // public static Map<Vertex,Double> get_target(Graph<Vertex, Edge> g,Vertex vertex){
  // Map<Vertex,Double> map = new HashMap<>();
  // for(Edge e : g.edges()) {
  // Set<Vertex> s1= e.sourceVertices();
  // Set<Vertex> s2= e.targetVertices();
  // Iterator<Vertex> it1 = s1.iterator();
  // Iterator<Vertex> it2 = s2.iterator();
  // while(it1.hasNext()&&it2.hasNext()) {
  // Vertex v1=it1.next();
  // Vertex v2=it2.next();
  // if(v1.equals(vertex)) {
  // map.put(v2, e.get_weight());
  // }
  // }
  // }
  // return map;
  // }
  //
  // public void printPathInfo(){
  // Set<Map.Entry<String, String>> pathInfos=pathInfo.entrySet();
  // for(Map.Entry<String, String> pathInfo:pathInfos){
  // System.out.println(pathInfo.getKey()+":"+pathInfo.getValue());
  // }
  // }


}
