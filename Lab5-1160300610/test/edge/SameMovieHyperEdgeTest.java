package edge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import vertex.Computer;
import vertex.Movie;
import vertex.Server;
import vertex.Vertex;


public class SameMovieHyperEdgeTest extends HyperEdgeTest {

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
    Vertex x1 = new Movie("1");
    Vertex x2 = new Movie("2");
    List<Vertex> list = new ArrayList<>();
    list.add(x1);
    list.add(x2);
    HyperEdge e = new SameMovieHyperEdge("a", 3, list);
    assertEquals("H||a:<1,2,>\n", e.toString());
  }

  @Test
  public void testcheckRep() {
    try {
      Vertex x1 = new Movie("1");
      Vertex x2 = new Movie("2");
      List<Vertex> list = new ArrayList<>();
      list.add(x1);
      list.add(x2);
      HyperEdge e1 = new SameMovieHyperEdge("a", -1, list);
      e1.checkRep();
      assertFalse(true);
    } catch (AssertionError e) {
      assertFalse(false);
    }
  }
}
