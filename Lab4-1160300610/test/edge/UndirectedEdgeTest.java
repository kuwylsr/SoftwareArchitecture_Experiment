package edge;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import vertex.Actor;
import vertex.Movie;
import vertex.Vertex;
import vertex.Word;

public class UndirectedEdgeTest extends EdgeTest{


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
    public void get_map(){
    	Vertex x1 = new Movie("1");
    	Vertex x2 = new Actor("2");
    	List<Vertex> list = new ArrayList<>();
    	list.add(x1);
    	list.add(x2);
    	UndirectedEdge e = new MovieActorRelation("a", 3, x1, x2, "Yes", list);
    	Map<Vertex,Vertex> map =new HashMap<>();
    	map.put(x1, x2);
    	map.put(x2, x1);
    	assertEquals(map, e.get_map());
    }
    @Test
    public void addvertices() {
    	Vertex x1 = new Movie("1");
    	Vertex x2 = new Actor("2");
    	Vertex x3 = new Word("3");
    	List<Vertex> list1 = new ArrayList<>();
    	List<Vertex> list2 = new ArrayList<>();
    	list1.add(x1);
    	list1.add(x2);
    	list2.add(x1);
    	list2.add(x2);
    	list2.add(x3);
    	UndirectedEdge e = new MovieActorRelation("a", 3, x1, x2, "Yes", list1);
    	assertEquals(true, e.addVertices(list1));
    	assertEquals(false, e.addVertices(list2));
    }
    @Test
    public void sourceVertices() {
    	Vertex x1 = new Movie("1");
    	Vertex x2 = new Actor("2");
    	List<Vertex> list = new ArrayList<>();
    	list.add(x1);
    	list.add(x2);
    	UndirectedEdge e = new MovieActorRelation("a", 3, x1, x2, "Yes", list);
    	Set<Vertex> s =new HashSet<>();
    	s.add(x1);
    	s.add(x2);
    	assertEquals(s.toString(), e.sourceVertices().toString());
    }
    @Test
    public void targetVertices(){
    	Vertex x1 = new Movie("1");
    	Vertex x2 = new Actor("2");
    	List<Vertex> list = new ArrayList<>();
    	list.add(x1);
    	list.add(x2);
    	UndirectedEdge e = new MovieActorRelation("a", 3, x1, x2, "Yes", list);
    	Set<Vertex> s =new HashSet<>();
    	s.add(x2);
    	s.add(x1);
    	assertEquals(s.toString(), e.targetVertices().toString());
    }
    
    
}
