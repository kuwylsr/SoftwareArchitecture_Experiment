package graph;

import edge.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import vertex.Vertex;

public abstract class ConcreteGraph implements Graph<Vertex, Edge> {

  // Abstraction function:
  // 如果表示属于vertices，则映射为一系列在vertices中的L类型的点
  // 如果表示属于edges，则映射为一系列在edges中的Edge类型的边
  // 如果表示属于GraphType，则映射为图的类型
  // 如果表示属于GraphName，则映射为图的名字
  // 如果表示属于VertexType，则映射为图中顶点的类型
  // 如果表示属于EdgeType，则映射为图中边的类型

  // Representation invariant（表示 不变性）:
  // edges中的元素中的source和target必须存在在vertices集合中,且edges中的元素中的weight必须大于零

  // Safety from rep exposure（表示暴露的安全性）:
  // 所有的区域块都是私有的。
  // 尽管vertices以及edges分别是可变数据类型Set和List，
  // 但类中重写的方法并没有以他们作为参数，并没有多个变量指向一个相同的单元。
  // 并且将其有关键词final定义，使得其引用不可变.
  // 总之不存在表示暴露。

  private final Set<Vertex> vertices = new HashSet<>();
  private final Set<Edge> edges = new HashSet<>();


  protected abstract void checkRep();

  @Override
  public boolean addVertex(Vertex v) {
//    checkRep();
//    try {
//      if (v == null) { // test pre-condition
//        throw new IllegalArgumentException("前置条件不满足！");
//      }
//    } catch (IllegalArgumentException e) {
//      return false;
//    }
    for (Vertex x : vertices) {
      if (x.equals(v)) {
        return false;
      } else {
        continue;
      }
    }
    vertices.add(v);// test post-condition
    // checkRep();
    return true;

  }

  @Override
  public Set<Vertex> vertices() {
    return vertices;
  }

  @Override
  public Map<Vertex, List<Double>> sources(Vertex target) {
//    checkRep();
    try {
      if (target == null) { // test pre-condition
        throw new IllegalArgumentException("前置条件不满足！");
      }
    } catch (IllegalArgumentException e) {
      return null;
    }
    Map<Vertex, List<Double>> map = new HashMap<>();
    for (Edge e : edges) {
      Set<Vertex> s1 = e.targetVertices();
      Set<Vertex> s2 = e.sourceVertices();
      List<Double> l = new ArrayList<>();
      Iterator<Vertex> it = s2.iterator();
      if (s1.contains(target)) {
        Vertex x = it.next();
        if (map.containsKey(x)) {
          map.get(x).add(e.get_weight());
        } else {
          l.add(e.get_weight());
          map.put(x, l);
        }
      }
    }
    // checkRep();
    return map;
  }

  @Override
  public Map<Vertex, List<Double>> targets(Vertex source) {
//    checkRep();
    try {
      if (source == null) { // test pre-condition
        throw new IllegalArgumentException("前置条件不满足！");
      }
    } catch (IllegalArgumentException e) {
      return null;
    }
    Map<Vertex, List<Double>> map = new HashMap<>();
    for (Edge e : edges) {
      Set<Vertex> s1 = e.sourceVertices();
      Set<Vertex> s2 = e.targetVertices();
      List<Double> l = new ArrayList<>();
      Iterator<Vertex> it = s2.iterator();
      while (it.hasNext()) {
        Vertex x = it.next();
        if (s1.contains(source) && !source.equals(x)) {
          if (map.containsKey(x)) {
            map.get(x).add(e.get_weight());
          } else {
            l.add(e.get_weight());
            map.put(x, l);
          }
        }
      }
    }
    // checkRep();
    return map;
  }

  @Override
  public boolean addEdge(Edge edge) {
//    try {
//      if (edge == null) { // test pre-condition
//        throw new IllegalArgumentException("前置条件不满足！");
//      }
//    } catch (IllegalArgumentException e) {
//      return false;
//    }
//    for (Edge e : edges) {
//      if (e.get_label().equals(edge.get_label())) {
//        // checkRep();
//        return false;
//      } else {
//        continue;
//      }
//    }
//    Set<Vertex> s1 = edge.sourceVertices();
//    Set<Vertex> s2 = edge.targetVertices();
//    Iterator<Vertex> it1 = s1.iterator();
//    Iterator<Vertex> it2 = s2.iterator();
//    while (it1.hasNext() && it2.hasNext()) {
//      if (!vertices().contains(it1.next()) || !vertices().contains(it2.next())) {
//        // checkRep();
//        return false;
//      }
//    }

     edges.add(edge);
    // assert edges.add(edge) == true; // test post-condition
    // checkRep();
    return true;
  }

  @Override
  public boolean removeEdge(Edge edge) {
    try {
      if (edge == null) { // test pre-condition
        throw new IllegalArgumentException("前置条件不满足！");
      }
    } catch (IllegalArgumentException e) {
      return false;
    }
    Iterator<Edge> it = edges.iterator();
    while (it.hasNext()) {
      Edge e = it.next();
      if (e.equals(edge)) {
        it.remove();
        // checkRep();
        return true;
      } else {
        continue;
      }
    }
    // checkRep();
    return false;
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
      // checkRep();
      return false;
    } else {
      while (it.hasNext()) {
        Edge e = it.next();
        if (e.containVertex(v)) {
          it.remove();
        } else {
          continue;
        }
      }
      vertices().remove(v);
      // checkRep();
      return true;
    }
  }

//  public void getTime() {
//    long s = System.currentTimeMillis();
//    for (int i = 0; i < num; i++) {
//      this.edges.add(this.edgesA[i]);
//    }
//    long e = System.currentTimeMillis();
//    System.out.println("Time: " + (e - s));
//  }

  @Override
  public Set<Edge> edges() {
    return edges;
  }

  public abstract String get_GraphType();

  public abstract String set_GraphType(String a);

  public abstract String get_GraphName();

  public abstract String set_GraphName(String a);

  public abstract String get_VertexType();

  public abstract String set_VertexType(String a);

  public abstract String get_EdgeType();

  public abstract String set_EdgeType(String a);

}
