package graph;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import edge.Edge;
import factory.MovieActorRelationFactory;
import factory.WordEdgeFartory;
import vertex.Actor;
import vertex.Movie;
import vertex.Vertex;

public class MovieGraphTest extends ConcreteGraphTest{

	
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
    
    /**
     * Tests add.
     */
    @Test
    public void addVertex() {
    	MovieGraph graph=new MovieGraph();
    	Vertex v =new Movie("siri");
    	assertEquals(true,graph.addVertex(v));// 新加入的点 不存在
        assertEquals(false,graph.addVertex(v));// 新加入的点 存在
    }
    
    /**
     * Tests add.
     */
    @Test
    public void vertices() {
    	MovieGraph graph=new MovieGraph();
    	Vertex v =new Movie("siri");
    	Vertex vv =new Movie("sir");
    	Vertex vvv =new Movie("si");
    	Set<Vertex> s=new HashSet<>();
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	graph.addVertex(vvv);
    	s.add(v);
    	s.add(vv);
    	s.add(vvv);
    	assertEquals(s, graph.vertices());
    }
    
    /**
     * Tests add.
     */
    @Test
    public void addEdge() {
    	MovieGraph graph=new MovieGraph();
    	List<Vertex> vertices = new ArrayList<>();
    	List<Double> weights = new ArrayList<>();
    	Vertex v =new Movie("siri");
    	Vertex vv =new Actor("sir");
    	vertices.add(v);
    	vertices.add(vv);
    	weights.add(20.0);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	Edge e =new MovieActorRelationFactory().createEdge("aa", vertices, weights);
    	assertEquals(true,graph.addEdge(e));
    	assertEquals(false,graph.addEdge(e));
    }
    
    /**
     * Tests add.
     */
    @Test
    public void source() {
    	Map<Vertex, List<Double>> map=new HashMap<>();
    	MovieGraph graph=new MovieGraph();
    	List<Vertex> vertices = new ArrayList<>();
    	List<Double> weights = new ArrayList<>();
    	Vertex v =new Movie("siri");
    	Vertex vv =new Actor("sir");
    	vertices.add(v);
    	vertices.add(vv);
    	weights.add(20.0);
    	map.put(v, weights);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	graph.addEdge(new MovieActorRelationFactory().createEdge("aa", vertices, weights));
    	assertEquals(map, graph.sources(vv));
    }
    
    /**
     * Tests add.
     */
    @Test
    public void targets() {
    	Map<Vertex, List<Double>> map=new HashMap<>();
    	ConcreteGraph graph=new MovieGraph();
    	List<Vertex> vertices = new ArrayList<>();
    	List<Double> weights = new ArrayList<>();
    	Vertex v =new Movie("siri");
    	Vertex vv =new Actor("sir");
    	vertices.add(v);
    	vertices.add(vv);
    	weights.add(20.0);
    	map.put(vv, weights);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	graph.addEdge(new MovieActorRelationFactory().createEdge("aaa", vertices, weights));
    	assertEquals(map, graph.targets(v));
    }
    
    /**
     * Tests add.
     */
    @Test
    public void removeEdge() {
    	ConcreteGraph graph=new MovieGraph();
    	List<Vertex> vertices1 = new ArrayList<>();
    	List<Vertex> vertices2 = new ArrayList<>();
    	List<Double> weights1 = new ArrayList<>();
    	List<Double> weights2 = new ArrayList<>();
    	Vertex v =new Movie("siri");
    	Vertex vv =new Actor("sir");
    	vertices1.add(v);
    	vertices1.add(vv);
    	vertices2.add(v);
    	vertices2.add(vv);
    	weights1.add(20.0);
    	weights2.add(28.0);
    	Edge e1 =new MovieActorRelationFactory().createEdge("aa", vertices1, weights1);
    	Edge e2 =new MovieActorRelationFactory().createEdge("aaa", vertices2, weights2);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	graph.addEdge(e1);
    	graph.addEdge(e2);
    	assertEquals(true, graph.removeEdge(e1));//之前 边存在
    	assertEquals(true, graph.removeEdge(e2));//之前 边存在
    	assertEquals(false, graph.removeEdge(e2));//之前 边不存在
    }
    
    /**
     * Tests add.
     */
    @Test
    public void removeVertex() {
    	ConcreteGraph graph=new MovieGraph();
    	List<Vertex> vertices1 = new ArrayList<>();
    	List<Vertex> vertices2 = new ArrayList<>();
    	List<Double> weights1 = new ArrayList<>();
    	List<Double> weights2 = new ArrayList<>();
    	Vertex v =new Movie("siri");
    	Vertex vv =new Actor("sir");
    	Vertex vvv=new Movie("si");
    	vertices1.add(v);
    	vertices1.add(vv);    	
    	vertices2.add(vv);
    	vertices2.add(vvv);
    	weights1.add(20.0);
    	weights2.add(28.0);
    	Edge e1 =new MovieActorRelationFactory().createEdge("aa", vertices1, weights1);
    	Edge e2 =new MovieActorRelationFactory().createEdge("aaa", vertices2, weights2);
    	graph.addVertex(vvv);
    	graph.addVertex(vv);
    	graph.addVertex(v);
    	graph.addEdge(e1);
    	graph.addEdge(e2);
    	assertEquals(true, graph.removeVertex(v));
    	graph.addVertex(v);
    	assertEquals(true, graph.addEdge(e1));
    }
    
    /**
     * Tests add.
     */
    @Test
    public void Read_file() throws IOException{
    	ConcreteGraph graph=new MovieGraph();   	
    	assertEquals(true,graph.Read_file(new File("src/graph/data_Movie.txt"), graph));
    }
    
    /**
     * Tests add.
     */
    @Test
    public  void edges() {
    	ConcreteGraph graph=new MovieGraph();
    	List<Vertex> vertices = new ArrayList<>();
    	List<Double> weights = new ArrayList<>();
    	Set<Edge> s =new HashSet<>();
    	Vertex v =new Movie("siri");
    	Vertex vv =new Actor("sir");
    	vertices.add(v);
    	vertices.add(vv);
    	weights.add(20.0);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	Edge e =new MovieActorRelationFactory().createEdge("aa", vertices, weights);
    	graph.addEdge(e);
    	s.add(e);
    	assertEquals(s,graph.edges());
    }
}
