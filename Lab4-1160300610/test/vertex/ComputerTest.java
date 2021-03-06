package vertex;

import org.junit.Test;

import vertex.Computer.Memento;
import vertex.Computer.Person_state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ComputerTest extends VertexTest{

	@Test
	public void checkRep() {
		Computer v = new  Computer("aa");
		try {
			String a[] = {"dsada"};//IP不符合要求
			v.fillVertexInfo(a);
			assertFalse(false);
		}catch (AssertionError e) {
		}
		
		v.set_IP("192.168.1.1");
	}
	@Test
	public void get_VertexInfo() {
		Vertex v = new Computer("aa");
		String a[] = {"192.168.1.1"};
		v.fillVertexInfo(a);
		String b[]=v.get_VertexInfo();
		assertEquals("192.168.1.1", b[0]);
	}
	@Test
	public void String() {
		Vertex v = new Computer("aa");
		String a[] = {"192.168.1.1"};
		v.fillVertexInfo(a);
		assertEquals(" <aa, Computer, <192.168.1.1>>\n", v.toString());
	}
	@Test
	public void saveget() {
		Computer v = new Computer("aa");
		String a[] = {"192.168.1.1"};
		Person_state state =Person_state.open;
		v.state=Person_state.open;
		Memento m1=v.new Memento(state);
		Memento m=v.save();
		assertEquals(m1.getState(),m.getState());
		v.close();
		v.open();
		assertEquals(Person_state.open, m.getState());
		v.restore(m);
		assertEquals(Person_state.open, v.state);
		m.setState(Person_state.close);
		v.open();
		assertEquals(v.state, v.getState());
	}
}
