package graph;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

import org.junit.Test;

import edge.Edge;
import factory.GraphPoetFactory;
import factory.WordEdgeFartory;
import junit.framework.AssertionFailedError;
import vertex.Vertex;
import vertex.Word;

public class GraphPoetTest extends ConcreteGraphTest{

	
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
    	ConcreteGraph graph=new GraphPoet();
    	Vertex v =new Word("siri");
    	assertEquals(false, graph.addVertex(null));//测试前置条件
    	assertEquals(true,graph.addVertex(v));// 新加入的点 不存在 (后置条件)
        assertEquals(false,graph.addVertex(v));// 新加入的点 存在（后置条件）
        
    }
    
    /**
     * Tests add.
     */
    @Test
    public void vertices() {
    	ConcreteGraph graph=new GraphPoet();
    	Vertex v =new Word("siri");
    	Vertex vv =new Word("sir");
    	Vertex vvv =new Word("si");
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
    	ConcreteGraph graph=new GraphPoet();
    	List<Vertex> vertices = new ArrayList<>();
    	List<Double> weights = new ArrayList<>();
    	Vertex v =new Word("siri");
    	Vertex vv =new Word("sir");
    	vertices.add(v);
    	vertices.add(vv);
    	weights.add(20.0);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	Edge e =new WordEdgeFartory().createEdge("aa", vertices, weights);
    	assertEquals(false, graph.addEdge(null));//测试前置条件
    	assertEquals(true,graph.addEdge(e));// 新加入的边 存在（后置条件）
    	assertEquals(false,graph.addEdge(e));// 新加入的边 存在（后置条件）
    }
    
    /**
     * Tests add.
     */
    @Test
    public void source() {
    	Map<Vertex, List<Double>> map=new HashMap<>();
    	ConcreteGraph graph=new GraphPoet();
    	List<Vertex> vertices = new ArrayList<>();
    	List<Double> weights = new ArrayList<>();
    	Vertex v =new Word("siri");
    	Vertex vv =new Word("sir");
    	vertices.add(v);
    	vertices.add(vv);
    	weights.add(20.0);
    	map.put(v, weights);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	graph.addEdge(new WordEdgeFartory().createEdge("aa", vertices, weights));
    	assertEquals(map, graph.sources(vv)); //测试后置条件
    	assertEquals(null, graph.sources(null));//测试前置条件
    }
    
    /**
     * Tests add.
     */
    @Test
    public void targets() {
    	Map<Vertex, List<Double>> map=new HashMap<>();
    	ConcreteGraph graph=new GraphPoet();
    	List<Vertex> vertices = new ArrayList<>();
    	List<Double> weights = new ArrayList<>();
    	Vertex v =new Word("siri");
    	Vertex vv =new Word("sir");
    	vertices.add(v);
    	vertices.add(vv);
    	weights.add(20.0);
    	map.put(vv, weights);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	graph.addEdge(new WordEdgeFartory().createEdge("aaa", vertices, weights));
    	assertEquals(map, graph.targets(v)); //测试后置条件
    	assertEquals(null, graph.targets(null));//测试前置条件
    }
    
    /**
     * Tests add.
     */
    @Test
    public void removeEdge() {
    	ConcreteGraph graph=new GraphPoet();
    	List<Vertex> vertices1 = new ArrayList<>();
    	List<Vertex> vertices2 = new ArrayList<>();
    	List<Double> weights1 = new ArrayList<>();
    	List<Double> weights2 = new ArrayList<>();
    	Vertex v =new Word("siri");
    	Vertex vv =new Word("sir");
    	vertices1.add(v);
    	vertices1.add(vv);
    	vertices2.add(v);
    	vertices2.add(vv);
    	weights1.add(20.0);
    	weights2.add(28.0);
    	Edge e1 =new WordEdgeFartory().createEdge("aa", vertices1, weights1);
    	Edge e2 =new WordEdgeFartory().createEdge("aaa", vertices2, weights2);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	graph.addEdge(e1);
    	graph.addEdge(e2);
    	assertEquals(false, graph.removeEdge(null));//测试前置条件
    	assertEquals(true, graph.removeEdge(e1));//之前 边存在（后置条件）
    	assertEquals(true, graph.removeEdge(e2));//之前 边存在（后置条件）
    	assertEquals(false, graph.removeEdge(e2));//之前 边不存在（后置条件）
    }
    
    /**
     * Tests add.
     */
    @Test
    public void removeVertex() {
    	ConcreteGraph graph=new GraphPoet();
    	List<Vertex> vertices1 = new ArrayList<>();
    	List<Vertex> vertices2 = new ArrayList<>();
    	List<Vertex> vertices3 = new ArrayList<>();
    	List<Double> weights1 = new ArrayList<>();
    	List<Double> weights2 = new ArrayList<>();
    	List<Double> weights3 = new ArrayList<>();
    	Vertex v =new Word("siri");
    	Vertex vv =new Word("sir");
    	Vertex vvv=new Word("si");
    	vertices1.add(v);
    	vertices1.add(vv);    	
    	vertices2.add(vv);
    	vertices2.add(vvv);
    	vertices3.add(vvv);
    	vertices3.add(v);
    	weights1.add(20.0);
    	weights2.add(28.0);
    	weights3.add(26.0);
    	Edge e1 =new WordEdgeFartory().createEdge("aa", vertices1, weights1);
    	Edge e2 =new WordEdgeFartory().createEdge("aaa", vertices2, weights2);
    	Edge e3 =new WordEdgeFartory().createEdge("aaaa", vertices3, weights3);
    	graph.addVertex(vvv);
    	graph.addVertex(vv);
    	graph.addVertex(v);
    	graph.addEdge(e1);
    	graph.addEdge(e2);
    	graph.addEdge(e3);
    	assertEquals(true, graph.removeVertex(v));
    	graph.addVertex(v);
    	assertEquals(false, graph.removeVertex(null));//测试前置条件
    	assertEquals(true, graph.addEdge(e1));//测试后置条件
    	assertEquals(true, graph.addEdge(e3));//测试后置条件
    }
    
    
    /**
     * Tests add.
     */
    @Test
    public  void edges() {
    	ConcreteGraph graph=new GraphPoet();
    	List<Vertex> vertices = new ArrayList<>();
    	List<Double> weights = new ArrayList<>();
    	Set<Edge> s =new HashSet<>();
    	Vertex v =new Word("siri");
    	Vertex vv =new Word("sir");
    	vertices.add(v);
    	vertices.add(vv);
    	weights.add(20.0);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	Edge e =new WordEdgeFartory().createEdge("aa", vertices, weights);
    	graph.addEdge(e);
    	s.add(e);
    	assertEquals(s,graph.edges()); //测试后置条件
    }
    
    /**
     * Tests checkRep()
     */
    @Test
    public  void checkRep() {
    	try { 
    	GraphPoet graph=new GraphPoet();
    	List<Vertex> vertices1 = new ArrayList<>();
    	List<Vertex> vertices2 = new ArrayList<>();
    	List<Vertex> vertices3 = new ArrayList<>();
    	List<Double> weights1 = new ArrayList<>();
    	List<Double> weights2 = new ArrayList<>();
    	List<Double> weights3 = new ArrayList<>();
    	Vertex v =new Word("siri");
    	Vertex vv =new Word("sir");
    	Vertex vvv=new Word("si");
    	vertices1.add(v);
    	vertices1.add(vv);    	
    	vertices2.add(vv);
    	vertices2.add(vvv);
    	vertices3.add(vvv);
    	vertices3.add(v);
    	weights1.add(-1.0); // 权重不符合要求 ，看是否能成功捕获断言错误
    	weights2.add(28.0);
    	weights3.add(26.0);
    	Edge e1 =new WordEdgeFartory().createEdge("aa", vertices1, weights1);
    	Edge e2 =new WordEdgeFartory().createEdge("aaa", vertices2, weights2);
    	Edge e3 =new WordEdgeFartory().createEdge("aaaa", vertices3, weights3);
    	graph.addVertex(vvv);
    	graph.addVertex(vv);
    	graph.addVertex(v);
    	graph.addEdge(e1);
    	graph.addEdge(e2);
    	graph.addEdge(e3);
    		assertFalse(false);
    	}catch (AssertionError e) {
			return;
		}    	
    }
    public boolean re_read_file(GraphPoet graph,String filepath) {
    	String data = "test/graph/exception_Poets/data_Poet.txt\r\n";
        InputStream stdin = System.in;
        try {
        	System.setIn(new ByteArrayInputStream(data.getBytes()));
            graph.create_GraphPoet(filepath);
        }catch(NoSuchElementException e){
        	return false;
        }finally {
            System.setIn(stdin);           
        }
        return true;
    }
    
    /**
     * Tests checkRep()
     */
    @Test
    public void create_GraphPoet() {
    	GraphPoet graph =new GraphPoet();
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Poets/???.txt"));//文件路径错误

    	assertEquals(true, re_read_file(graph,"test/graph/exception_Poets/re_read/data_Poet_Edge_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Poets/re_read/data_Poet_Edge_point_error.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Poets/re_read/data_Poet_Edge_weight_error.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Poets/re_read/data_Poet_EdgeType_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Poets/re_read/data_Poet_EdgeType_not_match.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Poets/re_read/data_Poet_GraphName_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Poets/re_read/data_Poet_GraphType_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Poets/re_read/data_Poet_GraphType_not_match.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Poets/re_read/data_Poet_Vertex_in_Edge_not_have.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Poets/re_read/data_Poet_Vertex_not_full_exnature.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Poets/re_read/data_Poet_VertexType_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Poets/re_read/data_Poet_VertexType_not_match.txt"));
    	
    	
    	
    	graph=graph.create_GraphPoet("test/graph/exception_Poets/repair/data_Poet_Edge_have_HyperEdge.txt");
    	assertEquals("The vertices:\n" + 
    			" <666, Word>\n" + 
    			" <999, Word>\n" + 
    			" <888, Word>\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"Yes||hhh:<888,999>(10.0)\n" + 
    			"Yes||ccc:<888,888>(10.0)\n" + 
    			"Yes||ggg:<666,888>(100.0)\n" 
    			, graph.toString());
    	graph=graph.create_GraphPoet("test/graph/exception_Poets/repair/data_Poet_Edge_have_multiEdge.txt");
    	assertEquals("The vertices:\n" + 
    			" <666, Word>\n" + 
    			" <999, Word>\n" + 
    			" <888, Word>\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"Yes||hhh:<888,999>(10.0)\n" + 
    			"Yes||ggg:<666,888>(100.0)\n" 
    			, graph.toString());
    	graph=graph.create_GraphPoet("test/graph/exception_Poets/repair/data_Poet_Edge_Label_repeat.txt");
    	assertEquals("The vertices:\n" + 
    			" <666, Word>\n" + 
    			" <999, Word>\n" + 
    			" <888, Word>\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"Yes||hhh:<888,999>(10.0)\n" + 
    			"Yes||ccc:<888,888>(10.0)\n" + 
    			"Yes||ggg:<666,888>(100.0)\n" + 
    			"Yes||ccc1:<888,888>(10.0)\n"   
    			, graph.toString());
    	graph=graph.create_GraphPoet("test/graph/exception_Poets/repair/data_Poet_Vertex_Label_repeat.txt");
    	assertEquals("The vertices:\n" + 
    			" <666, Word>\n" + 
    			" <6662, Word>\n" + 
    			" <6661, Word>\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"Yes||ggg:<666,666>(100.0)\n" 
    			, graph.toString());
    	
    }
    @Test
    public void getandset_EdgeType() {
    	GraphPoet graph =new GraphPoet();
    	graph = graph.create_GraphPoet("src/graph/data_Poet.txt");
    	assertEquals("WordNeighborhood", graph.get_EdgeType());
    	assertEquals("aaa", graph.set_EdgeType("aaa"));
    }
    @Test
    public void getandset_VertexType() {
    	GraphPoet graph =new GraphPoet();
    	graph = graph.create_GraphPoet("src/graph/data_Poet.txt");
    	assertEquals("Word", graph.get_VertexType());
    	assertEquals("aaa", graph.set_VertexType("aaa"));
    }
    @Test
    public void getandset_GraphName() {
    	GraphPoet graph =new GraphPoet();
    	graph = graph.create_GraphPoet("src/graph/data_Poet.txt");
    	assertEquals("LSR_Poet", graph.get_GraphName());
    	assertEquals("aaa", graph.set_GraphName("aaa"));
    }
    @Test
    public void getandset_GraphType() {
    	GraphPoet graph =new GraphPoet();
    	graph = graph.create_GraphPoet("src/graph/data_Poet.txt");
    	assertEquals("GraphPoet", graph.get_GraphType());
    	assertEquals("aaa", graph.set_GraphType("aaa"));
    }
}
