package vertex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class MovieTest extends VertexTest {
  @Test
  public void checkRep() {
    Vertex v = new Movie("FrankDarabont");
    try {
      String[] a = {"120", "USA", "8.5"};// 年不符合要求
      v.fillVertexInfo(a);
      assertFalse(true);
    } catch (AssertionError e) {
      assertFalse(false);
    }
    try {
      String[] a = {"1999", null, "8.5"};// 国家不符合要求
      v.fillVertexInfo(a);
      assertFalse(true);
    } catch (AssertionError e) {
      assertFalse(false);
    }
    try {
      String[] a = {"1999", null, "-1"};// IMDb不符合要求
      v.fillVertexInfo(a);
      assertFalse(true);
    } catch (AssertionError e) {
      assertFalse(false);
    }
    String[] a = {"1995", "USA", "8.5"};
    v.fillVertexInfo(a);
  }

  @Test
  public void get_VertexInfo() {
    Vertex v = new Movie("aa");
    String[] a = {"1995", "USA", "8.5"};
    v.fillVertexInfo(a);
    String[] b = v.get_VertexInfo();
    assertEquals("1995", b[0]);
    assertEquals("USA", b[1]);
    assertEquals("8.5", b[2]);
  }

  @Test
  public void tostring() {
    Vertex v = new Movie("aa");
    String[] a = {"1995", "USA", "8.5"};
    v.fillVertexInfo(a);
    assertEquals(" <aa, Movie, <1995, USA, 8.500000>>\n", v.toString());
  }
}
