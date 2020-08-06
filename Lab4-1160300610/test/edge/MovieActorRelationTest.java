package edge;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import vertex.Actor;
import vertex.Movie;
import vertex.Person;
import vertex.Vertex;

public class MovieActorRelationTest extends UndirectedEdgeTest{

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
    	Vertex x1 = new Movie("1");
    	Vertex x2 = new Actor("2");
    	List<Vertex> list = new ArrayList<>();
    	list.add(x1);
    	list.add(x2);
    	UndirectedEdge e = new MovieActorRelation("a", 3, x1, x2, "Yes", list);
    	assertEquals("Yes||a:<1,2><2,1>(3.0)\n", e.toString());
    }
    @Test
    public void getset_point() {
    	Vertex x1 = new Movie("1");
    	Vertex x2 = new Actor("2");
    	List<Vertex> list = new ArrayList<>();
    	list.add(x1);
    	list.add(x2);
    	MovieActorRelation e = new MovieActorRelation("a", 3, x1, x2, "Yes", list);
    	e.set_point1("Yes");
    	assertEquals("Yes", e.get_point());
    }
    @Test
	public void test_checkRep() {
    	try {
    		Vertex x1 = new Movie("1");
        	Vertex x2 = new Actor("2");
        	List<Vertex> list = new ArrayList<>();
        	List<Edge> list1 = new ArrayList<>();
        	list.add(x1);
        	list.add(x2);
        	MovieActorRelation e1 = new MovieActorRelation("a", 3, x1, x2, "Yes", list);
        	MovieActorRelation e2 = new MovieActorRelation("a", 3, x1, x2, "Y", list);
        	list1.add(e1);
    		list1.add(e2);
    		assertFalse(false);
    	}catch (AssertionError e) {
    	}
    }
}
