package vertex;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class WordTest extends VertexTest{

	@Test
	public void checkRep() {
		Vertex v = new Word("aa");
		String a[] = {};
		v.fillVertexInfo(a);
		v.checkRep();
		assert v.get_VertexInfo()==null;
	}
	
	@Test
	public void String() {
		Vertex v = new Word("aa");
		String a[] = {};
		v.fillVertexInfo(a);
		assertEquals(" <aa, Word>\n", v.toString());
	}
}
