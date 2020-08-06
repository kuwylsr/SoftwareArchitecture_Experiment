package vertex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import vertex.Person.Memento;
import vertex.Person.PersonState;



public class PersonTest extends VertexTest {

  @Test
  public void checkRep() {
    Vertex v = new Person("aa");
    try {
      String[] a = {"M", "-5"};// 年龄不符合要求
      v.fillVertexInfo(a);
      assertFalse(true);
    } catch (AssertionError e) {
      assertFalse(false);
    }
    try {
      String[] a = {"H", "15"};// 性别不符合要求
      v.fillVertexInfo(a);
      assertFalse(true);
    } catch (AssertionError e) {
      assertFalse(false);
    }
    String[] a = {"M", "5"};
    v.fillVertexInfo(a);
  }

  @Test
  public void get_VertexInfo() {
    Vertex v = new Person("aa");
    String[] a = {"M", "5"};
    v.fillVertexInfo(a);
    String[] b = v.get_VertexInfo();
    assertEquals("5", b[1]);
    assertEquals("M", b[0]);
  }

  @Test
  public void tostring() {
    Person v = new Person("aa");
    String[] a = {"M", "5"};
    v.fillVertexInfo(a);
    v.set_weight(0.3);
    assertEquals(" <aa, Person, <5, M>>（0.300000）\n", v.toString());
  }

  @Test
  public void saveget() {
    Person v = new Person("aa");
    String[] a = {"M", "5"};
    v.state = PersonState.locked;
    v.unlock();
    v.deactive();
    v.active();
    v.deactive();
    v.lock();
    v.unlock();
    v.active();
    v.lock();
    Memento m1 = v.new Memento(v.state);
    Memento m = v.save();
    m.setState(PersonState.locked);
    assertEquals(m1.getState(), m.getState());
    v.restore(m);
    assertEquals(PersonState.locked, v.getState());


  }
}
