package edge;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import vertex.Actor;
import vertex.Person;
import vertex.Vertex;
import vertex.Word;

public class CommentTieTest extends DirectedEdgeTest{
	
	/*
     * Testing ConcreteGraph...
     */
    /**
     * 断言已启用的测试
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    @Test
    public void tostring() {
    	Vertex x1 = new Person("1");
    	Vertex x2 = new Person("2");
    	List<Vertex> list = new ArrayList<>();
    	list.add(x1);
    	list.add(x2);
    	DirectedEdge e = new CommentTie("a", 3, x1, x2, "Yes", list);
    	assertEquals("Yes||a:<1,2>(3.0)\n", e.toString());
    }
    @Test
	public void test_checkRep() {
    	try {
    		Vertex x1 = new Person("1");
    		Vertex x2 = new Person("2");
    		List<Vertex> list = new ArrayList<>();
    		List<Edge> list1 = new ArrayList<>();
    		list.add(x1);
    		list.add(x2);
    		DirectedEdge e1 = new CommentTie("a", 3, x1, x2, "Yes", list);
    		DirectedEdge e2 = new CommentTie("aa", 3, x1, x2, "Y", list);
    		list1.add(e1);
    		list1.add(e2);
    		assertFalse(false);

    	}catch (AssertionError e) {
    	}
    }
}
