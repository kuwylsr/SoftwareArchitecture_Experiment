package edge;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import vertex.Vertex;
import vertex.Word;

public abstract class EdgeTest {

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
  public void containVertex() {
    Vertex x1 = new Word("1");
    Vertex x2 = new Word("2");
    List<Vertex> list = new ArrayList<>();
    list.add(x1);
    list.add(x2);
    Edge e = new WordNeighborhood("a", 3, x1, x2, "Yes", list);
    assertEquals(true, e.containVertex(x1));
    Vertex x3 = new Word("3");
    assertEquals(false, e.containVertex(x3));
  }

  @Test
  public void vertices() {
    Vertex x1 = new Word("1");
    Vertex x2 = new Word("2");
    List<Vertex> list = new ArrayList<>();
    list.add(x1);
    list.add(x2);
    Edge e = new WordNeighborhood("a", 3, x1, x2, "Yes", list);
    assertEquals("[ <1, Word>\n" + ",  <2, Word>\n" + "]", e.vertices().toString());
  }

  @Test
  public void getset_weight_Label() {
    Vertex x1 = new Word("1");
    Vertex x2 = new Word("2");
    List<Vertex> list = new ArrayList<>();
    list.add(x1);
    list.add(x2);
    Edge e = new WordNeighborhood("a", 3, x1, x2, "Yes", list);
    assertEquals("a", e.get_label());
    e.set_label("s");
    assertEquals("s", e.get_label());
    assertEquals(3.0, e.get_weight(), 0.01);
    e.set_weight(3.5);
    assertEquals(3.5, e.get_weight(), 0.01);

  }

  @Test
  public void equals() {
    Vertex x1 = new Word("1");
    Vertex x2 = new Word("2");
    List<Vertex> list = new ArrayList<>();
    list.add(x1);
    list.add(x2);
    Edge e = new WordNeighborhood("a", 3, x1, x2, "Yes", list);
    Edge e1 = new WordNeighborhood("aa", 3, x1, x2, "Yes", list);
    assertEquals(false, e.equals(e1));
    assertEquals(false, e.hashCode() == e1.hashCode());
    assertEquals(true, e.equals(e));
  }
}
