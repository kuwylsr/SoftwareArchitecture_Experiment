package vertex;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WordTest extends VertexTest {

  @Test
  public void checkRep() {
    Vertex v = new Word("aa");
    String[] a = {};
    v.fillVertexInfo(a);
    v.checkRep();
    assert v.get_VertexInfo() == null;
  }

  @Test
  public void tostring() {
    Vertex v = new Word("aa");
    String[] a = {};
    v.fillVertexInfo(a);
    assertEquals(" <aa, Word>\n", v.toString());
  }
}
