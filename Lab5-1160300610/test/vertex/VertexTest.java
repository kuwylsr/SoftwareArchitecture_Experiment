package vertex;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import vertex.Vertex;
import vertex.Word;

public abstract class VertexTest {

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

  public void setgetLabeltitle() {
    Vertex v = new Word("aaa");
    assertEquals("aaa", v.getLabel());
    assertEquals(0, v.get_title());
    assertEquals(true, v.set_Label("aaaa"));
    assertEquals(true, v.set_title(1));
  }

  public void getset_visited() {
    Vertex v = new Word("aaa");
    assertEquals(true, v.set_visited(true));
    assertEquals(true, v.get_visited());
  }
}
