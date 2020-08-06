package edge;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import vertex.Movie;
import vertex.Vertex;

public abstract class HyperEdgeTest extends EdgeTest{

	 
	@Test
    public void addvertices() {
    	Vertex x1 = new Movie("1");
    	Vertex x2 = new Movie("2");
    	Vertex x3 = new Movie("3");
    	List<Vertex> list1 = new ArrayList<>();
    	List<Vertex> list2 = new ArrayList<>();
    	list1.add(x1);
    	list1.add(x2);
    	list2.add(x1);
    	list2.add(x2);
    	list2.add(x3);
    	HyperEdge e = new SameMovieHyperEdge("a", 3, list1);
    	assertEquals(false, e.addVertices(list1));
    	assertEquals(true, e.addVertices(list2));
    	e.set_point();
    }
    @Test
    public void sourceVertices() {
    	Vertex x1 = new Movie("1");
    	Vertex x2 = new Movie("2");
    	List<Vertex> list = new ArrayList<>();
    	list.add(x1);
    	list.add(x2);
    	HyperEdge e = new SameMovieHyperEdge("a", 3, list);
    	Set<Vertex> s =new HashSet<>();
    	s.add(x1);
    	s.add(x2);
    	assertEquals(s.toString(), e.sourceVertices().toString());
    }
    @Test
    public void targetVertices(){
    	Vertex x1 = new Movie("1");
    	Vertex x2 = new Movie("2");
    	List<Vertex> list = new ArrayList<>();
    	list.add(x1);
    	list.add(x2);
    	HyperEdge e = new SameMovieHyperEdge("a", 3, list);
    	Set<Vertex> s =new HashSet<>();
    	s.add(x1);
    	s.add(x2);
    	assertEquals(s.toString(), e.targetVertices().toString());
    }
    @Test
    public void remove_vertex() {
    	Vertex x1 = new Movie("1");
    	Vertex x2 = new Movie("2");
    	Vertex x3 = new Movie("3");
    	List<Vertex> list1 = new ArrayList<>();
    	List<Vertex> list2 = new ArrayList<>();
    	list1.add(x1);
    	list1.add(x2);
    	list2.add(x1);
    	list2.add(x2);
    	list2.add(x3);
    	HyperEdge e1 = new SameMovieHyperEdge("a", 3, list1);
    	HyperEdge e2 = new SameMovieHyperEdge("aa", 3, list2);
    	assertEquals(false, e1.remove_vertex(x1));
    	assertEquals(true, e2.remove_vertex(x1));
    }
}
