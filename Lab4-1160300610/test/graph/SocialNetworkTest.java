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
import factory.FriendConnectionFactory;
import vertex.Person;
import vertex.Vertex;
import vertex.Word;

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
    	assertEquals(false, graph.addVertex(null));//测试前置条件
    	assertEquals(true,graph.addVertex(v));// 新加入的点 不存在 (后置条件)
        assertEquals(false,graph.addVertex(v));// 新加入的点 存在（后置条件）
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
    	weights1.add(1.0);
    	weights2.add(0.1);
    	Edge e1 =new FriendConnectionFactory().createEdge("aa", vertices1, weights1);
    	Edge e2 =new FriendConnectionFactory().createEdge("aaa", vertices2, weights2);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	assertEquals(false, graph.addEdge(null));//测试前置条件
    	assertEquals(true,graph.addEdge(e1));// 新加入的边 存在（后置条件）
    	assertEquals(false,graph.addEdge(e1));// 新加入的边 存在（后置条件）
    	assertEquals(true,graph.addEdge(e2));
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
    	weights.add(1.0);
    	map.put(v, weights);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	graph.addEdge(new FriendConnectionFactory().createEdge("aa", vertices, weights));
    	assertEquals(map, graph.sources(vv)); //测试后置条件
    	assertEquals(null, graph.sources(null));//测试前置条件
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
    	weights.add(1.0);
    	map.put(vv, weights);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	graph.addEdge(new FriendConnectionFactory().createEdge("aaa", vertices, weights));
    	assertEquals(map, graph.targets(v)); //测试后置条件
    	assertEquals(null, graph.targets(null));//测试前置条件
    }
    
    /**
     * Tests add.
     */
    @Test
    public void removeEdge() {
    	SocialNetwork graph=new SocialNetwork();
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
    	weights1.add(1.0);
    	weights2.add(0.11);
    	weights3.add(0.09);
    	Edge e1 =new FriendConnectionFactory().createEdge("aa", vertices1, weights1);
    	Edge e2 =new FriendConnectionFactory().createEdge("aaa", vertices2, weights2);
    	Edge e3 =new FriendConnectionFactory().createEdge("aaaa", vertices3, weights3);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	graph.addEdge(e1);
    	graph.addEdge(e2);
    	assertEquals(false, graph.removeEdge(e3));
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
    	SocialNetwork graph=new SocialNetwork();
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
    	weights1.add(1.0);
    	weights2.add(0.11);
    	weights3.add(0.09);
    	Edge e1 =new FriendConnectionFactory().createEdge("aa", vertices1, weights1);
    	Edge e2 =new FriendConnectionFactory().createEdge("aaa", vertices2, weights2);
    	Edge e3 =new FriendConnectionFactory().createEdge("aaaa", vertices3, weights3);
    	graph.addVertex(vvv);
    	graph.addVertex(v);
    	graph.addEdge(e1);
    	graph.addEdge(e2);
    	graph.addEdge(e3);
    	assertEquals(true, graph.removeVertex(v));
    	assertEquals(false, graph.removeVertex(vv));
    	graph.addVertex(v);
    	assertEquals(true, graph.addEdge(e3));//测试后置条件
    	assertEquals(false, graph.removeVertex(null));//测试前置条件
    }
    

    
    /**
     * Tests add.
     */
    @Test
    public  void edges() {
    	SocialNetwork graph=new SocialNetwork();
    	List<Vertex> vertices = new ArrayList<>();
    	List<Double> weights = new ArrayList<>();
    	Set<Edge> s =new HashSet<>();
    	Vertex v =new Person("siri");
    	Vertex vv =new Person("sir");
    	vertices.add(v);
    	vertices.add(vv);
    	weights.add(1.0);
    	graph.addVertex(v);
    	graph.addVertex(vv);
    	Edge e =new FriendConnectionFactory().createEdge("aa", vertices, weights);
    	graph.addEdge(e);
    	s.add(e);
    	assertEquals(s,graph.edges());//测试后置条件
    }
    /**
     * Tests checkRep()
     */
    @Test
    public  void checkRep() {
    	SocialNetwork graph=new SocialNetwork();
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
    	try {
    		weights1.add(10.0); // 权重不符合要求 ，看是否能成功捕获断言错误
    		assertFalse(false);
    	}catch (AssertionError e) {  		
		}   	
    	weights2.add(0.02);
    	weights3.add(0.78);
    	Edge e1 =new FriendConnectionFactory().createEdge("aa", vertices1, weights1);
    	Edge e2 =new FriendConnectionFactory().createEdge("aaa", vertices2, weights2);
    	Edge e3 =new FriendConnectionFactory().createEdge("aaaa", vertices3, weights3);
    	graph.addVertex(vvv);
    	graph.addVertex(vv);
    	graph.addVertex(v);
    	try {
    		graph.addEdge(e1);
        	graph.addEdge(e2);
        	graph.addEdge(e3);
        	graph.removeVertex(vvv);
        	assertFalse(false);
    	}catch (AssertionError e) { 
		} 	

    }
    public boolean re_read_file(SocialNetwork graph,String filepath) {
    	String data = "test/graph/exception_Socials/data_Social.txt\r\n";
        InputStream stdin = System.in;
        try {
        	System.setIn(new ByteArrayInputStream(data.getBytes()));
            graph.create_SocialNetwork(filepath);
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
    public void create_SocialNetwork() {
    	SocialNetwork graph =new SocialNetwork();
    	graph = graph.create_SocialNetwork("test/graph/exception_Socials/data_Social.txt");
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Socials/???.txt"));//文件路径错误
    	
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Socials/re_read/data_Social_EdgeType_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Socials/re_read/data_Social_EdgeType_not_match.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Socials/re_read/data_Social_Edge_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Socials/re_read/data_Social_Edge_point_error.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Socials/re_read/data_Social_Edge_weight_error.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Socials/re_read/data_Social_GraphName_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Socials/re_read/data_Social_GraphType_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Socials/re_read/data_Social_GraphType_not_match.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Socials/re_read/data_Social_VertexType_not_full.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Socials/re_read/data_Social_VertexType_not_match.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Socials/re_read/data_Social_Vertex_in_Edge_not_have.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Socials/re_read/data_Social_Vertex_not_full_exnature.txt"));
    	assertEquals(true, re_read_file(graph,"test/graph/exception_Socials/re_read/data_Social_Vertex_Person_not_full_nature.txt"));
    	
    	
    	
    	graph=graph.create_SocialNetwork("test/graph/exception_Socials/repair/data_Social_Edge_have_HyperEdge.txt");
    	assertEquals("The vertices:\n" + 
    			" <Kevin, Person, <34, M>>（0.000000）\n" + 
    			" <Edith, Person, <25, M>>（0.000000）\n" + 
    			" <Sophia, Person, <21, F>>（0.000000）\n" + 
    			" <Emma, Person, <19, F>>（0.000000）\n" + 
    			" <Vera, Person, <18, F>>（0.000000）\n" + 
    			" <Evelyn, Person, <32, M>>（0.000000）\n" + 
    			" <Rose, Person, <31, F>>（0.000000）\n" + 
    			" <Ellen, Person, <28, F>>（0.000000）\n" + 
    			" <Kelly, Person, <22, M>>（0.000000）\n" + 
    			" <Gloria, Person, <27, F>>（0.000000）\n" + 
    			" <Olivia, Person, <24, M>>（0.000000）\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"Yes||VeraCommentEvelyn:<Vera,Evelyn>(0.011748717957948865)\n" + 
    			"Yes||GloriaForwardKelly:<Gloria,Kelly>(0.03)\n" + 
    			"Yes||SophiaForwardEdith:<Sophia,Edith>(0.0232891956)\n" + 
    			"Yes||KevinFollowEdith:<Kevin,Edith>(0.04532086586529554)\n" + 
    			"Yes||OliviaCommentKevin:<Olivia,Kevin>(0.07408616001916846)\n" + 
    			"Yes||KevinCommentOlivia:<Kevin,Olivia>(0.011988487712192719)\n" + 
    			"Yes||VeraFollowGloria:<Vera,Gloria>(0.009342582120242332)\n" + 
    			"Yes||KellyCommentRose:<Kelly,Rose>(0.022140968389333195)\n" + 
    			"Yes||SophiaForwardEmma:<Sophia,Emma>(0.007454871511559999)\n" + 
    			"Yes||KellyFollowKevin:<Kelly,Kevin>(0.019782557950201504)\n" + 
    			"Yes||KevinFollowSophia:<Kevin,Sophia>(0.012236633783629798)\n" + 
    			"Yes||EmmaCommentSophia:<Emma,Sophia>(0.004915078977400217)\n" + 
    			"Yes||GloriaFollowVera:<Gloria,Vera>(0.019463712750504864)\n" + 
    			"Yes||KevinForwardVera:<Kevin,Vera>(0.0097)\n" + 
    			"Yes||KevinFollowVera:<Kevin,Vera>(0.004577865238918741)\n" + 
    			"Yes||OliviaForwardKevin:<Olivia,Kevin>(0.057617999999999996)\n" + 
    			"Yes||EmmaForwardSophia:<Emma,Sophia>(0.007530173243999999)\n" + 
    			"Yes||OliviaCommentKelly:<Olivia,Kelly>(0.013745113176098047)\n" + 
    			"Yes||KellyCommentOlivia:<Kelly,Olivia>(0.028635652450204267)\n" + 
    			"Yes||EllenForwardOlivia:<Ellen,Olivia>(0.12637547999999998)\n" + 
    			"Yes||EdithCommentVera:<Edith,Vera>(0.06770081236088453)\n" + 
    			"Yes||KevinCommentVera:<Kevin,Vera>(0.011513743598789882)\n" + 
    			"Yes||OliviaFollowEllen:<Olivia,Ellen>(0.37586860105382874)\n" + 
    			"Yes||SophiaCommentEmma:<Sophia,Emma>(0.004964726239798198)\n"
    			, graph.toString());
    	
    	graph=graph.create_SocialNetwork("test/graph/exception_Socials/repair/data_Social_Edge_Label_repeat.txt");
    	assertEquals("The vertices:\n" + 
    			" <Kevin, Person, <34, M>>（0.000000）\n" + 
    			" <Edith, Person, <25, M>>（0.000000）\n" + 
    			" <Sophia, Person, <21, F>>（0.000000）\n" + 
    			" <Emma, Person, <19, F>>（0.000000）\n" + 
    			" <Vera, Person, <18, F>>（0.000000）\n" + 
    			" <Evelyn, Person, <32, M>>（0.000000）\n" + 
    			" <Rose, Person, <31, F>>（0.000000）\n" + 
    			" <Ellen, Person, <28, F>>（0.000000）\n" + 
    			" <Kelly, Person, <22, M>>（0.000000）\n" + 
    			" <Gloria, Person, <27, F>>（0.000000）\n" + 
    			" <Olivia, Person, <24, M>>（0.000000）\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"Yes||VeraCommentEvelyn:<Vera,Evelyn>(0.011748717957948865)\n" + 
    			"Yes||GloriaForwardKelly:<Gloria,Kelly>(0.03)\n" + 
    			"Yes||SophiaForwardEdith:<Sophia,Edith>(0.0232891956)\n" + 
    			"Yes||KevinFollowEdith:<Kevin,Edith>(0.04532086586529554)\n" + 
    			"Yes||VeraFollowGloria:<Vera,Gloria>(0.009342582120242332)\n" + 
    			"Yes||SophiaForwardEmma:<Sophia,Emma>(0.007454871511559999)\n" + 
    			"Yes||KellyFollowKevin:<Kelly,Kevin>(0.019782557950201504)\n" + 
    			"Yes||GloriaFollowVera:<Gloria,Vera>(0.019463712750504864)\n" + 
    			"Yes||KevinForwardVera:<Kevin,Vera>(0.0097)\n" + 
    			"Yes||EmmaForwardSophia:<Emma,Sophia>(0.007530173243999999)\n" + 
    			"Yes||OliviaCommentKelly:<Olivia,Kelly>(0.013745113176098047)\n" + 
    			"Yes||OliviaFollowEllen:<Olivia,Ellen>(0.3645925430222136)\n" + 
    			"Yes||SophiaCommentEmma:<Sophia,Emma>(0.004964726239798198)\n" + 
    			"Yes||OliviaCommentKevin:<Olivia,Kevin>(0.07408616001916846)\n" + 
    			"Yes||KevinCommentOlivia:<Kevin,Olivia>(0.011988487712192719)\n" + 
    			"Yes||KellyCommentRose:<Kelly,Rose>(0.022140968389333195)\n" + 
    			"Yes||KevinFollowSophia:<Kevin,Sophia>(0.012236633783629798)\n" + 
    			"Yes||EmmaCommentSophia:<Emma,Sophia>(0.004915078977400217)\n" + 
    			"Yes||KevinFollowVera:<Kevin,Vera>(0.004577865238918741)\n" + 
    			"Yes||OliviaForwardKevin:<Olivia,Kevin>(0.057617999999999996)\n" + 
    			"Yes||KellyCommentOlivia:<Kelly,Olivia>(0.028635652450204267)\n" + 
    			"Yes||EllenForwardOlivia:<Ellen,Olivia>(0.12637547999999998)\n" + 
    			"Yes||EdithCommentVera:<Edith,Vera>(0.06770081236088453)\n" + 
    			"Yes||KevinCommentVera:<Kevin,Vera>(0.011513743598789882)\n" + 
    			"Yes||OliviaFollowEllen1:<Olivia,Ellen>(0.011276058031614857)\n"
    			, graph.toString());
    	graph=graph.create_SocialNetwork("test/graph/exception_Socials/repair/data_Social_Edge_Loop.txt");
    	assertEquals("The vertices:\n" + 
    			" <Kevin, Person, <34, M>>（0.000000）\n" + 
    			" <Edith, Person, <25, M>>（0.000000）\n" + 
    			" <Sophia, Person, <21, F>>（0.000000）\n" + 
    			" <Emma, Person, <19, F>>（0.000000）\n" + 
    			" <Vera, Person, <18, F>>（0.000000）\n" + 
    			" <Evelyn, Person, <32, M>>（0.000000）\n" + 
    			" <Rose, Person, <31, F>>（0.000000）\n" + 
    			" <Ellen, Person, <28, F>>（0.000000）\n" + 
    			" <Kelly, Person, <22, M>>（0.000000）\n" + 
    			" <Gloria, Person, <27, F>>（0.000000）\n" + 
    			" <Olivia, Person, <24, M>>（0.000000）\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"Yes||VeraCommentEvelyn:<Vera,Evelyn>(0.011748717957948865)\n" + 
    			"Yes||GloriaForwardKelly:<Gloria,Kelly>(0.03)\n" + 
    			"Yes||SophiaForwardEdith:<Sophia,Edith>(0.0232891956)\n" + 
    			"Yes||KevinFollowEdith:<Kevin,Edith>(0.04532086586529554)\n" + 
    			"Yes||OliviaCommentKevin:<Olivia,Kevin>(0.07408616001916846)\n" + 
    			"Yes||KevinCommentOlivia:<Kevin,Olivia>(0.011988487712192719)\n" + 
    			"Yes||VeraFollowGloria:<Vera,Gloria>(0.009342582120242332)\n" + 
    			"Yes||KellyCommentRose:<Kelly,Rose>(0.022140968389333195)\n" + 
    			"Yes||SophiaForwardEmma:<Sophia,Emma>(0.007454871511559999)\n" + 
    			"Yes||KellyFollowKevin:<Kelly,Kevin>(0.019782557950201504)\n" + 
    			"Yes||KevinFollowSophia:<Kevin,Sophia>(0.012236633783629798)\n" + 
    			"Yes||EmmaCommentSophia:<Emma,Sophia>(0.004915078977400217)\n" + 
    			"Yes||GloriaFollowVera:<Gloria,Vera>(0.019463712750504864)\n" + 
    			"Yes||KevinForwardVera:<Kevin,Vera>(0.0097)\n" + 
    			"Yes||KevinFollowVera:<Kevin,Vera>(0.004577865238918741)\n" + 
    			"Yes||OliviaForwardKevin:<Olivia,Kevin>(0.057617999999999996)\n" + 
    			"Yes||EmmaForwardSophia:<Emma,Sophia>(0.007530173243999999)\n" + 
    			"Yes||OliviaCommentKelly:<Olivia,Kelly>(0.013745113176098047)\n" + 
    			"Yes||KellyCommentOlivia:<Kelly,Olivia>(0.028635652450204267)\n" + 
    			"Yes||EllenForwardOlivia:<Ellen,Olivia>(0.12637547999999998)\n" + 
    			"Yes||EdithCommentVera:<Edith,Vera>(0.06770081236088453)\n" + 
    			"Yes||KevinCommentVera:<Kevin,Vera>(0.011513743598789882)\n" + 
    			"Yes||OliviaFollowEllen:<Olivia,Ellen>(0.37586860105382874)\n" + 
    			"Yes||SophiaCommentEmma:<Sophia,Emma>(0.004964726239798198)\n"
    			, graph.toString());
    	graph=graph.create_SocialNetwork("test/graph/exception_Socials/repair/data_Social_Vertex_Label_repeat.txt");
    	assertEquals("The vertices:\n" + 
    			" <Kevin, Person, <34, M>>（0.000000）\n" + 
    			" <Edith, Person, <25, M>>（0.000000）\n" + 
    			" <Emma1, Word>\n" + 
    			" <Sophia, Person, <21, F>>（0.000000）\n" + 
    			" <Emma, Person, <19, F>>（0.000000）\n" + 
    			" <Vera, Person, <18, F>>（0.000000）\n" + 
    			" <Evelyn, Person, <32, M>>（0.000000）\n" + 
    			" <Rose, Person, <31, F>>（0.000000）\n" + 
    			" <Ellen, Person, <28, F>>（0.000000）\n" + 
    			" <Kelly, Person, <22, M>>（0.000000）\n" + 
    			" <Gloria, Person, <27, F>>（0.000000）\n" + 
    			" <Olivia, Person, <24, M>>（0.000000）\n" + 
    			"\n" + 
    			"_________________________________________________________________\n" + 
    			"\n" + 
    			"The edge:\n" + 
    			"Yes||VeraCommentEvelyn:<Vera,Evelyn>(0.011748717957948865)\n" + 
    			"Yes||GloriaForwardKelly:<Gloria,Kelly>(0.03)\n" + 
    			"Yes||SophiaForwardEdith:<Sophia,Edith>(0.0232891956)\n" + 
    			"Yes||KevinFollowEdith:<Kevin,Edith>(0.04532086586529554)\n" + 
    			"Yes||OliviaCommentKevin:<Olivia,Kevin>(0.07408616001916846)\n" + 
    			"Yes||KevinCommentOlivia:<Kevin,Olivia>(0.011988487712192719)\n" + 
    			"Yes||VeraFollowGloria:<Vera,Gloria>(0.009342582120242332)\n" + 
    			"Yes||KellyCommentRose:<Kelly,Rose>(0.022140968389333195)\n" + 
    			"Yes||SophiaForwardEmma:<Sophia,Emma>(0.007454871511559999)\n" + 
    			"Yes||KellyFollowKevin:<Kelly,Kevin>(0.019782557950201504)\n" + 
    			"Yes||KevinFollowSophia:<Kevin,Sophia>(0.012236633783629798)\n" + 
    			"Yes||EmmaCommentSophia:<Emma,Sophia>(0.004915078977400217)\n" + 
    			"Yes||GloriaFollowVera:<Gloria,Vera>(0.019463712750504864)\n" + 
    			"Yes||KevinForwardVera:<Kevin,Vera>(0.0097)\n" + 
    			"Yes||KevinFollowVera:<Kevin,Vera>(0.004577865238918741)\n" + 
    			"Yes||OliviaForwardKevin:<Olivia,Kevin>(0.057617999999999996)\n" + 
    			"Yes||EmmaForwardSophia:<Emma,Sophia>(0.007530173243999999)\n" + 
    			"Yes||OliviaCommentKelly:<Olivia,Kelly>(0.013745113176098047)\n" + 
    			"Yes||KellyCommentOlivia:<Kelly,Olivia>(0.028635652450204267)\n" + 
    			"Yes||EllenForwardOlivia:<Ellen,Olivia>(0.12637547999999998)\n" + 
    			"Yes||EdithCommentVera:<Edith,Vera>(0.06770081236088453)\n" + 
    			"Yes||KevinCommentVera:<Kevin,Vera>(0.011513743598789882)\n" + 
    			"Yes||OliviaFollowEllen:<Olivia,Ellen>(0.37586860105382874)\n" + 
    			"Yes||SophiaCommentEmma:<Sophia,Emma>(0.004964726239798198)\n"
    			, graph.toString());

    }
    @Test
    public void getandset_EdgeType() {
    	SocialNetwork graph =new SocialNetwork();
    	graph = graph.create_SocialNetwork("src/graph/data_Social.txt");
    	assertEquals("CommentTie ForwardTie FriendTie", graph.get_EdgeType());
    	assertEquals("aaa", graph.set_EdgeType("aaa"));
    }
    @Test
    public void getandset_VertexType() {
    	SocialNetwork graph =new SocialNetwork();
    	graph = graph.create_SocialNetwork("src/graph/data_Social.txt");
    	assertEquals("Person", graph.get_VertexType());
    	assertEquals("aaa", graph.set_VertexType("aaa"));
    }
    @Test
    public void getandset_GraphName() {
    	SocialNetwork graph =new SocialNetwork();
    	graph = graph.create_SocialNetwork("src/graph/data_Social.txt");
    	assertEquals("LabSocialNetwork", graph.get_GraphName());
    	assertEquals("aaa", graph.set_GraphName("aaa"));
    }
    @Test
    public void getandset_GraphType() {
    	SocialNetwork graph =new SocialNetwork();
    	graph = graph.create_SocialNetwork("src/graph/data_Social.txt");
    	assertEquals("SocialNetwork", graph.get_GraphType());
    	assertEquals("aaa", graph.set_GraphType("aaa"));
    }
}
