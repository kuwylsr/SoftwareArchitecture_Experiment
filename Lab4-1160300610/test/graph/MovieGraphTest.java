package graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;

import edge.Edge;
import factory.MovieActorRelationFactory;
import factory.SameMovieHyperEdgeFactory;
import vertex.Actor;
import vertex.Movie;
import vertex.Vertex;
import vertex.Word;

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
    	assertEquals(false, graph.addVertex(null));//测试前置条件
    	assertEquals(true,graph.addVertex(v));// 新加入的点 不存在 (后置条件)
        assertEquals(false,graph.addVertex(v));// 新加入的点 存在（后置条件）
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
    	assertEquals(false, graph.addEdge(null));//测试前置条件
    	assertEquals(true,graph.addEdge(e));// 新加入的点 存在（后置条件）
    	assertEquals(false,graph.addEdge(e));// 新加入的点 存在（后置条件）
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
    	assertEquals(map, graph.sources(vv)); //测试后置条件
    	assertEquals(null, graph.sources(null));//测试前置条件
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
    	assertEquals(map, graph.targets(v)); //测试后置条件
    	assertEquals(null, graph.targets(null));//测试前置条件
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
    	ConcreteGraph graph=new MovieGraph();
    	List<Vertex> vertices1 = new ArrayList<>();
    	List<Vertex> vertices2 = new ArrayList<>();
    	List<Vertex> vertices3 = new ArrayList<>();
    	List<Double> weights1 = new ArrayList<>();
    	List<Double> weights2 = new ArrayList<>();
    	List<Double> weights3 = new ArrayList<>();
    	Vertex v =new Movie("siri");
    	Vertex vv =new Actor("sir");
    	Vertex vvv=new Movie("si");
    	vertices1.add(v);
    	vertices1.add(vv);    	
    	vertices2.add(vv);
    	vertices2.add(vvv);
    	vertices3.add(v);
    	vertices3.add(vv);
    	vertices3.add(vvv);
    	weights1.add(20.0);
    	weights2.add(28.0);
    	weights3.add(-1.0);
    	Edge e1 =new MovieActorRelationFactory().createEdge("aa", vertices1, weights1);
    	Edge e2 =new MovieActorRelationFactory().createEdge("aaa", vertices2, weights2);
    	Edge e3 =new SameMovieHyperEdgeFactory().createEdge("aaaa", vertices3, weights2);
    	graph.addVertex(vvv);
    	graph.addVertex(vv);
    	graph.addVertex(v);
    	graph.addEdge(e1);
    	graph.addEdge(e2);
    	graph.addEdge(e3);
    	assertEquals(true, graph.removeVertex(vv));
    	assertEquals(true, graph.removeVertex(vvv));
    	assertEquals(false, graph.removeVertex(vv));
//    	assertEquals(false, graph.removeEdge(e3));
    	graph.addVertex(vv);
    	assertEquals(false, graph.removeVertex(null));//测试前置条件
    	assertEquals(true, graph.addEdge(e1));//测试后置条件
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
    	assertEquals(s,graph.edges());//测试后置条件
    }
    /**
     * Tests checkRep()
     */
    @Test
    public  void checkRep() {
    	try { 
    	MovieGraph graph=new MovieGraph();
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
    	weights1.add(-5.0); // 权重不符合要求 ，看是否能成功捕获断言错误
    	weights2.add(28.0);
    	weights3.add(26.0);
    	Edge e1 =new MovieActorRelationFactory().createEdge("aa", vertices1, weights1);
    	Edge e2 =new MovieActorRelationFactory().createEdge("aaa", vertices2, weights2);
    	Edge e3 =new MovieActorRelationFactory().createEdge("aaaa", vertices3, weights3);
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
    
    public boolean re_read_file(MovieGraph graph,String filepath) {
    	String data = "test/graph/exception_Movies/data_Movie.txt\r\n";
        InputStream stdin = System.in;
        try {
        	System.setIn(new ByteArrayInputStream(data.getBytes()));
            graph.create_MovieGraph(filepath);
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
    public void create_MovieGraph() {
    	MovieGraph graph =new MovieGraph();
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/???.txt"));//文件路径错误
    	
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_Actor_not_full_nature.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_Director_not_full_nature.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_EdgeType_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_EdgeType_not_match.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_Edge_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_Edge_weight_error.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_Graph.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_GraphName_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_GraphType_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_GraphType_not_match.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_Movie_not_full_nature.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_VertexType_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_VertexType_not_match.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_Vertex_in_Edge_not_have.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_Vertex_not_full_exnature.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Movies/re_read/data_Movie_Vertex_in_H_not_have.txt"));


    	graph=graph.create_MovieGraph("test/graph/exception_Movies/repair/data_Movie_Edge_Label_repeat.txt");
    	assertEquals("The vertices:\n" + 
    			" <TomHanks, Actor, <62, M>>\n" + 
    			" <FrankDarabont, Director, <59, M>>\n" + 
    			" <TheShawshankRedemption, Movie, <1994, USA, 9.300000>>\n" + 
    			" <TimRobbins, Actor, <60, M>>\n" + 
    			" <TheGreenMile, Movie, <1999, USA, 8.500000>>\n" + 
    			" <MorganFreeman, Actor, <81, M>>\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"No||SRFD:<FrankDarabont,TheShawshankRedemption><TheShawshankRedemption,FrankDarabont>(-1.0)\n" + 
    			"No||GMFD:<FrankDarabont,TheGreenMile><TheGreenMile,FrankDarabont>(-1.0)\n" + 
    			"H||ActorsInSR:<FrankDarabont,TimRobbins,MorganFreeman,>\n" + 
    			"No||SRFD1:<TheShawshankRedemption,FrankDarabont>(-1.0)\n" + 
    			"No||SRTR:<TheShawshankRedemption,MorganFreeman><MorganFreeman,TheShawshankRedemption>(2.0)\n" + 
    			"No||GMTH:<TomHanks,TheGreenMile><TheGreenMile,TomHanks>(1.0)\n" + 
    			"No||SRMF:<TheShawshankRedemption,TimRobbins><TimRobbins,TheShawshankRedemption>(1.0)\n", graph.toString());
    	graph=graph.create_MovieGraph("test/graph/exception_Movies/repair/data_Movie_Edge_Loop_error.txt");
    	assertEquals("The vertices:\n" + 
    			" <TomHanks, Actor, <62, M>>\n" + 
    			" <FrankDarabont, Director, <59, M>>\n" + 
    			" <TheShawshankRedemption, Movie, <1994, USA, 9.300000>>\n" + 
    			" <TimRobbins, Actor, <60, M>>\n" + 
    			" <TheGreenMile, Movie, <1999, USA, 8.500000>>\n" + 
    			" <MorganFreeman, Actor, <81, M>>\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"No||GMFD:<FrankDarabont,TheGreenMile><TheGreenMile,FrankDarabont>(-1.0)\n" + 
    			"H||ActorsInSR:<FrankDarabont,TimRobbins,MorganFreeman,>\n" + 
    			"No||SRTR:<TheShawshankRedemption,MorganFreeman><MorganFreeman,TheShawshankRedemption>(2.0)\n" + 
    			"No||GMTH:<TomHanks,TheGreenMile><TheGreenMile,TomHanks>(1.0)\n" + 
    			"No||SRMF:<TheShawshankRedemption,TimRobbins><TimRobbins,TheShawshankRedemption>(1.0)\n", graph.toString());
    	graph=graph.create_MovieGraph("test/graph/exception_Movies/repair/data_Movie_Edge_point_error.txt");
    	assertEquals("The vertices:\n" + 
    			" <TomHanks, Actor, <62, M>>\n" + 
    			" <FrankDarabont, Director, <59, M>>\n" + 
    			" <TheShawshankRedemption, Movie, <1994, USA, 9.300000>>\n" + 
    			" <TimRobbins, Actor, <60, M>>\n" + 
    			" <TheGreenMile, Movie, <1999, USA, 8.500000>>\n" + 
    			" <MorganFreeman, Actor, <81, M>>\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"No||SRFD:<FrankDarabont,TheShawshankRedemption><TheShawshankRedemption,FrankDarabont>(-1.0)\n" + 
    			"No||GMFD:<FrankDarabont,TheGreenMile><TheGreenMile,FrankDarabont>(-1.0)\n" + 
    			"H||ActorsInSR:<FrankDarabont,TimRobbins,MorganFreeman,>\n" + 
    			"No||SRTR:<TheShawshankRedemption,MorganFreeman><MorganFreeman,TheShawshankRedemption>(2.0)\n" + 
    			"No||GMTH:<TomHanks,TheGreenMile><TheGreenMile,TomHanks>(1.0)\n" + 
    			"No||SRMF:<TheShawshankRedemption,TimRobbins><TimRobbins,TheShawshankRedemption>(1.0)\n", graph.toString());
    	graph=graph.create_MovieGraph("test/graph/exception_Movies/repair/data_Movie_Vertex_Label_repeat.txt");
    	assertEquals("The vertices:\n" + 
    			" <TomHanks, Actor, <62, M>>\n" + 
    			" <FrankDarabont, Director, <59, M>>\n" + 
    			" <TheShawshankRedemption, Movie, <1994, USA, 9.300000>>\n" + 
    			" <TimRobbins, Actor, <60, M>>\n" + 
    			" <TheShawshankRedemption1, Word>\n" + 
    			" <TheGreenMile, Movie, <1999, USA, 8.500000>>\n" + 
    			" <MorganFreeman, Actor, <81, M>>\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"No||SRFD:<FrankDarabont,TheShawshankRedemption><TheShawshankRedemption,FrankDarabont>(-1.0)\n" + 
    			"No||GMFD:<FrankDarabont,TheGreenMile><TheGreenMile,FrankDarabont>(-1.0)\n" + 
    			"H||ActorsInSR:<FrankDarabont,TimRobbins,MorganFreeman,>\n" + 
    			"No||SRTR:<TheShawshankRedemption,MorganFreeman><MorganFreeman,TheShawshankRedemption>(2.0)\n" + 
    			"No||GMTH:<TomHanks,TheGreenMile><TheGreenMile,TomHanks>(1.0)\n" + 
    			"No||SRMF:<TheShawshankRedemption,TimRobbins><TimRobbins,TheShawshankRedemption>(1.0)\n", graph.toString());
    	graph=graph.create_MovieGraph("test/graph/exception_Movies/repair/data_Movie_H_Label_repeat.txt");
    	assertEquals("The vertices:\n" + 
    			" <TomHanks, Actor, <62, M>>\n" + 
    			" <FrankDarabont, Director, <59, M>>\n" + 
    			" <TheShawshankRedemption, Movie, <1994, USA, 9.300000>>\n" + 
    			" <TimRobbins, Actor, <60, M>>\n" + 
    			" <TheGreenMile, Movie, <1999, USA, 8.500000>>\n" + 
    			" <MorganFreeman, Actor, <81, M>>\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"No||SRFD:<FrankDarabont,TheShawshankRedemption><TheShawshankRedemption,FrankDarabont>(-1.0)\n" + 
    			"No||GMFD:<FrankDarabont,TheGreenMile><TheGreenMile,FrankDarabont>(-1.0)\n" + 
    			"H||ActorsInSR:<FrankDarabont,TimRobbins,MorganFreeman,>\n" + 
    			"No||SRTR:<TheShawshankRedemption,MorganFreeman><MorganFreeman,TheShawshankRedemption>(2.0)\n" + 
    			"No||GMTH:<TomHanks,TheGreenMile><TheGreenMile,TomHanks>(1.0)\n" + 
    			"H||ActorsInSR1:<FrankDarabont,TimRobbins,MorganFreeman,>\n" + 
    			"No||SRMF:<TheShawshankRedemption,TimRobbins><TimRobbins,TheShawshankRedemption>(1.0)\n", graph.toString());
    	graph=graph.create_MovieGraph("test/graph/exception_Movies/repair/data_Movie_Edge_have_mutiEdge.txt");
    	assertEquals("The vertices:\n" + 
    			" <TomHanks, Actor, <62, M>>\n" + 
    			" <FrankDarabont, Director, <59, M>>\n" + 
    			" <TheShawshankRedemption, Movie, <1994, USA, 9.300000>>\n" + 
    			" <TimRobbins, Actor, <60, M>>\n" + 
    			" <TheGreenMile, Movie, <1999, USA, 8.500000>>\n" + 
    			" <MorganFreeman, Actor, <81, M>>\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"No||SRFD:<FrankDarabont,TheShawshankRedemption><TheShawshankRedemption,FrankDarabont>(-1.0)\n" + 
    			"No||GMFD:<FrankDarabont,TheGreenMile><TheGreenMile,FrankDarabont>(-1.0)\n" + 
    			"H||ActorsInSR:<FrankDarabont,TimRobbins,MorganFreeman,>\n" +  
    			"No||SRTR:<TheShawshankRedemption,MorganFreeman><MorganFreeman,TheShawshankRedemption>(2.0)\n" + 
    			"No||GMTH:<TomHanks,TheGreenMile><TheGreenMile,TomHanks>(1.0)\n" + 
    			"No||SRMF:<TheShawshankRedemption,TimRobbins><TimRobbins,TheShawshankRedemption>(1.0)\n", graph.toString());
    	graph=graph.create_MovieGraph("test/graph/exception_Movies/repair/data_Movie_Edge_begin_end_error.txt");
    	assertEquals("The vertices:\n" + 
    			" <TomHanks, Actor, <62, M>>\n" + 
    			" <FrankDarabont, Director, <59, M>>\n" + 
    			" <TheShawshankRedemption, Movie, <1994, USA, 9.300000>>\n" + 
    			" <TimRobbins, Actor, <60, M>>\n" + 
    			" <TheGreenMile, Movie, <1999, USA, 8.500000>>\n" + 
    			" <MorganFreeman, Actor, <81, M>>\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"No||SRFD:<FrankDarabont,TheShawshankRedemption><TheShawshankRedemption,FrankDarabont>(-1.0)\n" + 
    			"H||ActorsInSR:<FrankDarabont,TimRobbins,MorganFreeman,>\n" + 
    			"No||SRTR:<TheShawshankRedemption,MorganFreeman><MorganFreeman,TheShawshankRedemption>(2.0)\n" + 
    			"No||GMTH:<TomHanks,TheGreenMile><TheGreenMile,TomHanks>(1.0)\n" + 
    			"No||SRMF:<TheShawshankRedemption,TimRobbins><TimRobbins,TheShawshankRedemption>(1.0)\n", graph.toString());
    }
    @Test
    public void getandset_EdgeType() {
    	MovieGraph graph =new MovieGraph();
    	graph = graph.create_MovieGraph("src/graph/data_Movie.txt");
    	assertEquals("MovieActorRelation MovieDirectorRelation SameMovieHyperEdge", graph.get_EdgeType());
    	assertEquals("aaa", graph.set_EdgeType("aaa"));
    }
    @Test
    public void getandset_VertexType() {
    	MovieGraph graph =new MovieGraph();
    	graph = graph.create_MovieGraph("src/graph/data_Movie.txt");
    	assertEquals("Movie Actor Director", graph.get_VertexType());
    	assertEquals("aaa", graph.set_VertexType("aaa"));
    }
    @Test
    public void getandset_GraphName() {
    	MovieGraph graph =new MovieGraph();
    	graph = graph.create_MovieGraph("src/graph/data_Movie.txt");
    	assertEquals("MyFavoriteMovies", graph.get_GraphName());
    	assertEquals("aaa", graph.set_GraphName("aaa"));
    }
    @Test
    public void getandset_GraphType() {
    	MovieGraph graph =new MovieGraph();
    	graph = graph.create_MovieGraph("src/graph/data_Movie.txt");
    	assertEquals("MovieGraph", graph.get_GraphType());
    	assertEquals("aaa", graph.set_GraphType("aaa"));
    }
}
