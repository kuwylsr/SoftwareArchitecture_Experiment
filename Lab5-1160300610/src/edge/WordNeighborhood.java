package edge;

import java.util.Collection;
import java.util.Map;

import vertex.Vertex;

public class WordNeighborhood extends DirectedEdge {

  // Abstraction function:
  // point 映射为边是有向边还是 无向边

  // Representation invariant（表示 不变性）:
  // point 只能有两种取值 YES 和 NO

  // Safety from rep exposure（表示暴露的安全性）:
  // 所有的区域块都是私有的。
  // 总之不存在表示暴露

  private String point;

  /**
   * NetworkConnection边的构造函数
   * 
   * @param 边的标签，边的权重，边的起点，边的终点，边所包含的点的集合
   * 
   * @return 无
   */
  public WordNeighborhood(String label, double weight, Vertex source, Vertex target, String point,
      Collection<Vertex> vertices) {
    super(label, weight, source, target, vertices);
    this.point = point;
//    checkRep();
  }

  @Override
  public String toString() {
    String a = this.point + "||" + get_label() + ":";
    for (Map.Entry<Vertex, Vertex> entry : getMap().entrySet()) {
      a = a + "<" + entry.getKey().getLabel() + "," + entry.getValue().getLabel() + ">";
    }
    return a + "(" + this.get_weight() + ")" + "\n";
  }

  @Override
  protected void checkRep() {
//    assert true;
  }

  @Override
  public String get_point1() {
    return this.point;
  }

  @Override
  public void set_point1(String point) {
    this.point=point;
  }


}
