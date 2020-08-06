package edge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JToolBar.Separator;

import org.apache.commons.collections15.SetUtils;

import vertex.Vertex;

public abstract class Edge {

  // Abstraction function:
  // 如果表示属于vertices，则映射为一系列在边中所包含的顶点
  // 如果表示属于label，则映射为边的标签
  // 如果表示属于weight，则映射为边的权重

  // Representation invariant（表示 不变性）:
  // 边的权重不是必须大于等于0或者等于-1

  // Safety from rep exposure（表示暴露的安全性）:
  // 所有的区域块都是私有的。
  // 尽管vertices以及edges分别是可变数据类型Set和List，
  // 但类中重写的方法并没有以他们作为参数，并没有多个变量指向一个相同的单元。
  // 并且将其有关键词final定义，使得其引用不可变.
  // 总之不存在表示暴露。

  private Collection<Vertex> vertices = new ArrayList<>();
  private String label;
  private double weight;


  /**
   * Edge的构造函数
   * 
   * @param 边的标签，权重
   * 
   * @return 无
   */
  public Edge(String label, double weight, Collection<Vertex> vertices) {
    this.label = label;
    this.weight = weight;
    this.vertices.addAll(vertices);
  }

  /*
   * 如果是超边，vertices.size()>=2，该函数添加 vertices 中的所有节点到该超边； 如果是有向边，vertices.size()=2，该操作将vertices
   * 中的第一个元素作为 source，将第二个元素作为 target； 如果是无向边，vertices.size()=2，无需考虑次序； 如果是 loop，vertices.size()=1
   * 
   * @param 顶点集合
   * 
   * @return 返回是否成功添加由这些点形成的边，是为true，否为false
   */
  public abstract boolean addVertices(List<Vertex> vertices);

  /**
   * 该边中是否包含指定的点v
   * 
   * @param 顶点
   * 
   * @return 返回边中是否包含该顶点，是为true，否为false
   */
  public boolean containVertex(Vertex v) {
    for (Vertex v1 : vertices) {
      if (v1.equals(v)) {
        return true;
      } else {
        continue;
      }
    }
    return false;
  }

  /**
   * 找到边所包含的顶点集合
   * 
   * @param 无
   * 
   * @return 返回Set，其中包含该边包含的所有点
   */
  public Set<Vertex> vertices() {

    Set<Vertex> s = new HashSet<Vertex>() {
      public String toString() {
        String a = " ";
        Iterator<Vertex> it = vertices.iterator();
        while (it.hasNext()) {
          a = a + "\"" + it.next().getLabel() + "\"";
          if (it.hasNext()) {
            a = a + ", ";
          }
        }
        return a.trim();
      }
    };
    s.addAll(vertices);
    return s;
  }

  /*
   * 寻找该边的源节点集合
   * 
   * @param 无
   * 
   * @return 返回该边的所有起点构成的集合
   */
  public abstract Set<Vertex> sourceVertices();

  /*
   * 寻找该边目标节点集合
   * 
   * @param 无
   * 
   * @return 返回该边的所有目标节点构成的集合
   */
  public abstract Set<Vertex> targetVertices();

  /*
   * 改变边的起点和终点
   * 
   * @return 是否成功改变边的起点和终点
   */
  public abstract void set_point();

  /*
   * get the weight of the edge
   * 
   * @return the weight of the edge
   */
  public double get_weight() {
    return this.weight;
  }

  /*
   * set the weight of the edge
   * 
   * @return 是否成功设置边的权重
   */
  public void set_weight(double weight) {
    this.weight = weight;
  }


  /*
   * get the label of the edge
   * 
   * @return the label of the edge
   */
  public String get_label() {
    return this.label;
  }

  /*
   * set the weight of the edge
   * 
   * @return 是否成功设置边的标签
   */
  public void set_label(String label) {
    this.label = label;
  }

  public abstract String get_point1();

  public abstract void set_point1(String point);


  @Override
  public boolean equals(Object b) {
    if (b instanceof Edge) {
      Edge other = (Edge) b;
      if (this.label.equals(other.label)) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }

  }

  @Override
  public int hashCode() {
    return this.label.hashCode() + (int) weight;
  }

  protected abstract void checkRep();


}
