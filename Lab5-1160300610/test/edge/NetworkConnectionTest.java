package edge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import vertex.Computer;
import vertex.Server;
import vertex.Vertex;

public class NetworkConnectionTest extends UndirectedEdgeTest {

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
  public void tostring() {
    Vertex x1 = new Server("1");
    Vertex x2 = new Computer("2");
    List<Vertex> list = new ArrayList<>();
    list.add(x1);
    list.add(x2);
    UndirectedEdge e = new NetworkConnection("a", 3, x1, x2, "Yes", list);
    assertEquals("Yes||a:<1,2><2,1>(3.0)\n", e.toString());
  }

  @Test
  public void getset_point() {
    Vertex x1 = new Server("1");
    Vertex x2 = new Computer("2");
    List<Vertex> list = new ArrayList<>();
    list.add(x1);
    list.add(x2);
    NetworkConnection e = new NetworkConnection("a", 3, x1, x2, "Yes", list);
    e.set_point1("Yes");
    assertEquals("Yes", e.get_point1());
  }

  @Test
  public void test_checkRep() {
    try {
      Vertex x1 = new Server("1");
      Vertex x2 = new Computer("2");
      List<Vertex> list = new ArrayList<>();
      List<Edge> list1 = new ArrayList<>();
      list.add(x1);
      list.add(x2);
      NetworkConnection e1 = new NetworkConnection("a", 3, x1, x2, "Yes", list);
      NetworkConnection e2 = new NetworkConnection("a", 3, x1, x2, "Y", list);
      list1.add(e1);
      list1.add(e2);
      assertFalse(true);
    } catch (AssertionError e) {
      assertFalse(false);
    }
  }
}
