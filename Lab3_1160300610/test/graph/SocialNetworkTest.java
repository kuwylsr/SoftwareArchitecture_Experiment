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
import factory.FriendConnectionFactory;
import vertex.Person;
import vertex.Vertex;

public class SocialNetworkTest extends ConcreteGraphTest{

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
    	SocialNetwork graph=new SocialNetwork();
    	Vertex v =new Person("siri");
    	assertEquals(true,graph.addVertex(v));// 新加入的点 不存在
        assertEquals(false,graph.addVertex(v));// 新加入的点 存在
    }
    
    /**
     * Tests add.
     */
    @Test
    public void vertices() {
    	SocialNetwork graph=new SocialNetwork();
    	Vertex v =new Person("siri");
    	Vertex vv =new Person("sir");
    	Vertex vvv =new Person("si");
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
    	SocialNetwork graph=new SocialNetwork();
    	List<Vertex> vertices = new ArrayList<>();
    	List<Double> weights = new ArrayList<>();
    	Vertex v =new Person("siri");
    	Vertex vv =new Person("sir");
    	vertices.add(v);
    	vertices.add(vv);
    	weights.add(20.0);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	Edge e =new FriendConnectionFactory().createEdge("aa", vertices, weights);
    	assertEquals(true,graph.addEdge(e));
    	assertEquals(false,graph.addEdge(e));
    }
    
    /**
     * Tests add.
     */
    @Test
    public void source() {
    	Map<Vertex, List<Double>> map=new HashMap<>();
    	SocialNetwork graph=new SocialNetwork();
    	List<Vertex> vertices = new ArrayList<>();
    	List<Double> weights = new ArrayList<>();
    	Vertex v =new Person("siri");
    	Vertex vv =new Person("sir");
    	vertices.add(v);
    	vertices.add(vv);
    	weights.add(20.0);
    	map.put(v, weights);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	graph.addEdge(new FriendConnectionFactory().createEdge("aa", vertices, weights));
    	assertEquals(map, graph.sources(vv));
    }
    
    /**
     * Tests add.
     */
    @Test
    public void targets() {
    	Map<Vertex, List<Double>> map=new HashMap<>();
    	ConcreteGraph graph=new SocialNetwork();
    	List<Vertex> vertices = new ArrayList<>();
    	List<Double> weights = new ArrayList<>();
    	Vertex v =new Person("siri");
    	Vertex vv =new Person("sir");
    	vertices.add(v);
    	vertices.add(vv);
    	weights.add(20.0);
    	map.put(vv, weights);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	graph.addEdge(new FriendConnectionFactory().createEdge("aaa", vertices, weights));
    	assertEquals(map, graph.targets(v));
    }
    
    /**
     * Tests add.
     */
    @Test
    public void removeEdge() {
    	ConcreteGraph graph=new SocialNetwork();
    	List<Vertex> vertices1 = new ArrayList<>();
    	List<Vertex> vertices2 = new ArrayList<>();
    	List<Double> weights1 = new ArrayList<>();
    	List<Double> weights2 = new ArrayList<>();
    	Vertex v =new Person("siri");
    	Vertex vv =new Person("sir");
    	vertices1.add(v);
    	vertices1.add(vv);
    	vertices2.add(v);
    	vertices2.add(vv);
    	weights1.add(0.9);
    	weights2.add(0.1);
    	Edge e1 =new FriendConnectionFactory().createEdge("aa", vertices1, weights1);
    	Edge e2 =new FriendConnectionFactory().createEdge("aaa", vertices2, weights2);
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
    	ConcreteGraph graph=new SocialNetwork();
    	List<Vertex> vertices1 = new ArrayList<>();
    	List<Vertex> vertices2 = new ArrayList<>();
    	List<Vertex> vertices3 = new ArrayList<>();
    	List<Double> weights1 = new ArrayList<>();
    	List<Double> weights2 = new ArrayList<>();
    	List<Double> weights3 = new ArrayList<>();
    	Vertex v =new Person("siri");
    	Vertex vv =new Person("sir");
    	Vertex vvv=new Person("si");
    	vertices1.add(v);
    	vertices1.add(vv);    	
    	vertices2.add(vv);
    	vertices2.add(vvv);
    	vertices3.add(vvv);
    	vertices3.add(v);
    	weights1.add(0.8);
    	weights2.add(0.11);
    	weights3.add(0.09);
    	Edge e1 =new FriendConnectionFactory().createEdge("aa", vertices1, weights1);
    	Edge e2 =new FriendConnectionFactory().createEdge("aaa", vertices2, weights2);
    	Edge e3 =new FriendConnectionFactory().createEdge("aaaa", vertices3, weights3);
    	graph.addVertex(vvv);
    	graph.addVertex(vv);
    	graph.addVertex(v);
    	graph.addEdge(e1);
    	graph.addEdge(e2);
    	graph.addEdge(e3);
    	assertEquals(true, graph.removeVertex(v));
    	graph.addVertex(v);
    	assertEquals(true, graph.addEdge(e1));
    	assertEquals(true, graph.addEdge(e3));
    }
    
    /**
     * Tests add.
     */
    @Test
    public void Read_file() throws IOException{
    	ConcreteGraph graph=new SocialNetwork();   	
    	assertEquals(true,graph.Read_file(new File("src/graph/data_Social.txt"), graph));
    }
    
    /**
     * Tests add.
     */
    @Test
    public  void edges() {
    	ConcreteGraph graph=new SocialNetwork();
    	List<Vertex> vertices = new ArrayList<>();
    	List<Double> weights = new ArrayList<>();
    	Set<Edge> s =new HashSet<>();
    	Vertex v =new Person("siri");
    	Vertex vv =new Person("sir");
    	vertices.add(v);
    	vertices.add(vv);
    	weights.add(20.0);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	Edge e =new FriendConnectionFactory().createEdge("aa", vertices, weights);
    	graph.addEdge(e);
    	s.add(e);
    	assertEquals(s,graph.edges());
    }
}
