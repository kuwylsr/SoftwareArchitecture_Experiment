package vertex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class DirectorTest extends VertexTest{
	@Test
	public void checkRep() {
		Vertex v = new Director("aa");
		try {
			String a[] = {"-5","M"};//年龄不符合要求
			v.fillVertexInfo(a);
			assertFalse(false);
		}catch (AssertionError e) {
		}
		try {
			String a[] = {"15","H"};//性别不符合要求
			v.fillVertexInfo(a);
			assertFalse(false);
		}catch (AssertionError e) {
		}
		String a[] = {"5","M"};
		v.fillVertexInfo(a);
	}
	@Test
	public void get_VertexInfo() {
		Vertex v = new Director("aa");
		String a[] = {"5","M"};
		v.fillVertexInfo(a);
		String b[]=v.get_VertexInfo();
		assertEquals("5", b[0]);
		assertEquals("M", b[1]);
	}
	@Test
	public void String() {
		Vertex v = new Director("aa");
		String a[] = {"5","M"};
		v.fillVertexInfo(a);
		assertEquals(" <aa, Director, <5, M>>\n", v.toString());
	}
}
