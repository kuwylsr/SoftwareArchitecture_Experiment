package vertex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;
import vertex.Computer.Memento;
import vertex.Computer.PersonState;



public class ComputerTest extends VertexTest {

  @Test
  public void checkRep() {
    Computer v = new Computer("aa");
    try {
      String[] a = {"dsada"};// IP不符合要求
      v.fillVertexInfo(a);
      assertFalse(true);
    } catch (AssertionError e) {
      assertFalse(false);
    }

    v.setIP("192.168.1.1");
  }

  @Test
  public void get_VertexInfo() {
    Vertex v = new Computer("aa");
    String[] a = {"192.168.1.1"};
    v.fillVertexInfo(a);
    String[] b = v.get_VertexInfo();
    assertEquals("192.168.1.1", b[0]);
  }

  @Test
  public void tostring() {
    Vertex v = new Computer("aa");
    String[] a = {"192.168.1.1"};
    v.fillVertexInfo(a);
    assertEquals(" <aa, Computer, <192.168.1.1>>\n", v.toString());
  }

  @Test
  public void saveget() {
    Computer v = new Computer("aa");
    String[] a = {"192.168.1.1"};
    PersonState state = PersonState.open;
    v.state = PersonState.open;
    Memento m1 = v.new Memento(state);
    Memento m = v.save();
    assertEquals(m1.getState(), m.getState());
    v.close();
    v.open();
    assertEquals(PersonState.open, m.getState());
    v.restore(m);
    assertEquals(PersonState.open, v.state);
    m.setState(PersonState.close);
    v.open();
    assertEquals(v.state, v.getState());
  }
}
