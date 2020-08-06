package edge;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import vertex.Vertex;
import vertex.Word;

public abstract class DirectedEdgeTest extends EdgeTest {

  /*
   * Testing ConcreteGraph...
   */
  /**
   * 断言已启用的测试
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  @Test
  public void get_map() {
    Vertex x1 = new Word("1");
    Vertex x2 = new Word("2");
    List<Vertex> list = new ArrayList<>();
    list.add(x1);
    list.add(x2);
    DirectedEdge e = new WordNeighborhood("a", 3, x1, x2, "Yes", list);
    Map<Vertex, Vertex> map = new HashMap<>();
    map.put(x1, x2);
    assertEquals(map, e.getMap());
  }

  @Test
  public void addvertices() {
    Vertex x1 = new Word("1");
    Vertex x2 = new Word("2");
    List<Vertex> list1 = new ArrayList<>();
    List<Vertex> list3 = new ArrayList<>();
    list3.add(x1);
    list1.add(x1);
    list1.add(x2);
    List<Vertex> list2 = new ArrayList<>();
    list2.add(x1);
    list2.add(x2);
    Vertex x3 = new Word("3");
    list2.add(x3);
    DirectedEdge e = new WordNeighborhood("a", 3, x1, x2, "Yes", list1);
    assertEquals(true, e.addVertices(list1));
    assertEquals(true, e.addVertices(list3));
    assertEquals(false, e.addVertices(list2));
  }

  @Test
  public void sourceVertices() {
    Vertex x1 = new Word("1");
    Vertex x2 = new Word("2");
    List<Vertex> list = new ArrayList<>();
    list.add(x1);
    list.add(x2);
    DirectedEdge e = new WordNeighborhood("a", 3, x1, x2, "Yes", list);
    Set<Vertex> s = new HashSet<>();
    s.add(x1);
    assertEquals(s.toString(), e.sourceVertices().toString());
  }

  @Test
  public void targetVertices() {
    Vertex x1 = new Word("1");
    Vertex x2 = new Word("2");
    List<Vertex> list = new ArrayList<>();
    list.add(x1);
    list.add(x2);
    DirectedEdge e = new WordNeighborhood("a", 3, x1, x2, "Yes", list);
    Set<Vertex> s = new HashSet<>();
    s.add(x2);
    assertEquals(s.toString(), e.targetVertices().toString());
  }

  @Test
  public void setPoint() {
    Vertex x1 = new Word("1");
    Vertex x2 = new Word("2");
    List<Vertex> list = new ArrayList<>();
    list.add(x1);
    list.add(x2);
    DirectedEdge e = new WordNeighborhood("a", 3, x1, x2, "Yes", list);
    e.set_point();
    assertEquals("Yes||a:<2,1>(3.0)\n", e.toString());
  }
}
