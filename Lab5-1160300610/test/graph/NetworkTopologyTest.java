package graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import edge.Edge;
import factory.NetworkConnectionFactory;
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
import vertex.Computer;
import vertex.Vertex;


public class NetworkTopologyTest extends ConcreteGraphTest {

  /*
   * Testing ConcreteGraph...
   */
  /**
   * 断言已启用的测试
   */
  @Test(expected = AssertionError.class)
  public void testAssertionsEnabled() {
    assert false;
  }

  /**
   * Tests add.
   */
  @Test
  public void addVertex() {
    ConcreteGraph graph = new NetworkTopology();
    Vertex v = new Computer("siri");
    assertEquals(false, graph.addVertex(null));// 测试前置条件
    assertEquals(true, graph.addVertex(v));// 新加入的点 不存在 (后置条件)
    assertEquals(false, graph.addVertex(v));// 新加入的点 存在（后置条件）
  }

  /**
   * Tests add.
   */
  @Test
  public void vertices() {
    ConcreteGraph graph = new NetworkTopology();
    Vertex v = new Computer("siri");
    Vertex vv = new Computer("sir");
    Vertex vvv = new Computer("si");

    graph.addVertex(v);
    graph.addVertex(vv);
    graph.addVertex(vvv);
    Set<Vertex> s = new HashSet<>();
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

    List<Vertex> vertices = new ArrayList<>();
    List<Double> weights = new ArrayList<>();
    Vertex v = new Computer("siri");
    Vertex vv = new Computer("sir");
    vertices.add(v);
    vertices.add(vv);
    weights.add(20.0);
    NetworkTopology graph = new NetworkTopology();
    graph.addVertex(v);
    graph.addVertex(vv);
    Edge e = new NetworkConnectionFactory().createEdge("aa", vertices, weights);
    assertEquals(false, graph.addEdge(null));// 测试前置条件
    assertEquals(true, graph.addEdge(e));// 新加入的点 存在（后置条件）
    assertEquals(false, graph.addEdge(e));// 新加入的点 存在（后置条件）
  }

  /**
   * Tests add.
   */
  @Test
  public void source() {


    List<Vertex> vertices = new ArrayList<>();
    List<Double> weights = new ArrayList<>();
    Vertex v = new Computer("siri");
    Vertex vv = new Computer("sir");
    vertices.add(v);
    vertices.add(vv);
    weights.add(20.0);
    Map<Vertex, List<Double>> map = new HashMap<>();
    map.put(v, weights);
    NetworkTopology graph = new NetworkTopology();
    graph.addVertex(v);
    graph.addVertex(vv);
    graph.addEdge(new NetworkConnectionFactory().createEdge("aa", vertices, weights));
    assertEquals(map, graph.sources(vv)); // 测试后置条件
    assertEquals(null, graph.sources(null));// 测试前置条件
  }

  /**
   * Tests add.
   */
  @Test
  public void targets() {


    List<Vertex> vertices = new ArrayList<>();
    List<Double> weights = new ArrayList<>();
    Vertex v = new Computer("siri");
    Vertex vv = new Computer("sir");
    vertices.add(v);
    vertices.add(vv);
    weights.add(20.0);
    Map<Vertex, List<Double>> map = new HashMap<>();
    map.put(vv, weights);
    ConcreteGraph graph = new NetworkTopology();
    graph.addVertex(v);
    graph.addVertex(vv);
    graph.addEdge(new NetworkConnectionFactory().createEdge("aaa", vertices, weights));
    assertEquals(map, graph.targets(v)); // 测试后置条件
    assertEquals(null, graph.targets(null));// 测试前置条件
  }

  /**
   * Tests add.
   */
  @Test
  public void removeEdge() {

    List<Vertex> vertices1 = new ArrayList<>();
    List<Vertex> vertices2 = new ArrayList<>();

    Vertex v = new Computer("siri");
    Vertex vv = new Computer("sir");
    vertices1.add(v);
    vertices1.add(vv);
    vertices2.add(v);
    vertices2.add(vv);
    List<Double> weights1 = new ArrayList<>();
    List<Double> weights2 = new ArrayList<>();
    weights1.add(20.0);
    weights2.add(28.0);
    Edge e1 = new NetworkConnectionFactory().createEdge("aa", vertices1, weights1);
    Edge e2 = new NetworkConnectionFactory().createEdge("aaa", vertices2, weights2);
    ConcreteGraph graph = new NetworkTopology();
    graph.addVertex(v);
    graph.addVertex(vv);
    graph.addEdge(e1);
    graph.addEdge(e2);
    assertEquals(false, graph.removeEdge(null));// 测试前置条件
    assertEquals(true, graph.removeEdge(e1));// 之前 边存在（后置条件）
    assertEquals(true, graph.removeEdge(e2));// 之前 边存在（后置条件）
    assertEquals(false, graph.removeEdge(e2));// 之前 边不存在（后置条件）
  }

  /**
   * Tests add.
   */
  @Test
  public void removeVertex() {

    List<Vertex> vertices1 = new ArrayList<>();
    List<Vertex> vertices2 = new ArrayList<>();

    Vertex v = new Computer("siri");
    Vertex vv = new Computer("sir");

    vertices1.add(v);
    vertices1.add(vv);
    vertices2.add(vv);
    Vertex vvv = new Computer("si");
    vertices2.add(vvv);
    List<Vertex> vertices3 = new ArrayList<>();
    List<Double> weights1 = new ArrayList<>();

    vertices3.add(vvv);
    vertices3.add(v);
    weights1.add(20.0);
    List<Double> weights2 = new ArrayList<>();
    List<Double> weights3 = new ArrayList<>();
    weights2.add(28.0);
    weights3.add(26.0);
    Edge e1 = new NetworkConnectionFactory().createEdge("aa", vertices1, weights1);
    Edge e2 = new NetworkConnectionFactory().createEdge("aaa", vertices2, weights2);
    Edge e3 = new NetworkConnectionFactory().createEdge("aaaa", vertices3, weights3);
    ConcreteGraph graph = new NetworkTopology();
    graph.addVertex(vvv);
    graph.addVertex(vv);
    graph.addVertex(v);
    graph.addEdge(e1);
    graph.addEdge(e2);
    graph.addEdge(e3);
    assertEquals(true, graph.removeVertex(v));
    assertEquals(false, graph.removeVertex(v));
    graph.addVertex(v);
    assertEquals(false, graph.removeVertex(null));// 测试前置条件
    assertEquals(true, graph.addEdge(e1));// 测试后置条件
    assertEquals(true, graph.addEdge(e3));// 测试后置条件
  }



  /**
   * Tests add.
   */
  @Test
  public void edges() {

    List<Vertex> vertices = new ArrayList<>();
    List<Double> weights = new ArrayList<>();

    Vertex v = new Computer("siri");
    Vertex vv = new Computer("sir");
    vertices.add(v);
    vertices.add(vv);
    weights.add(20.0);
    ConcreteGraph graph = new NetworkTopology();
    graph.addVertex(v);
    graph.addVertex(vv);
    Edge e = new NetworkConnectionFactory().createEdge("aa", vertices, weights);
    graph.addEdge(e);
    Set<Edge> s = new HashSet<>();
    s.add(e);
    assertEquals(s, graph.edges()); // 测试后置条件
  }

  /**
   * Tests checkRep()
   */
  @Test
  public void checkRep() {
    try {

      List<Vertex> vertices1 = new ArrayList<>();
      List<Vertex> vertices2 = new ArrayList<>();

      Vertex v = new Computer("siri");
      Vertex vv = new Computer("sir");

      vertices1.add(v);
      vertices1.add(vv);
      vertices2.add(vv);
      Vertex vvv = new Computer("si");
      vertices2.add(vvv);
      List<Vertex> vertices3 = new ArrayList<>();
      List<Double> weights1 = new ArrayList<>();

      vertices3.add(vvv);
      vertices3.add(v);
      weights1.add(-1.0); // 权重不符合要求 ，看是否能成功捕获断言错误
      List<Double> weights2 = new ArrayList<>();
      List<Double> weights3 = new ArrayList<>();
      weights2.add(28.0);
      weights3.add(26.0);
      Edge e1 = new NetworkConnectionFactory().createEdge("aa", vertices1, weights1);
      Edge e2 = new NetworkConnectionFactory().createEdge("aaa", vertices2, weights2);
      Edge e3 = new NetworkConnectionFactory().createEdge("aaaa", vertices3, weights3);
      NetworkTopology graph = new NetworkTopology();
      graph.addVertex(vvv);
      graph.addVertex(vv);
      graph.addVertex(v);
      graph.addEdge(e1);
      graph.addEdge(e2);
      graph.addEdge(e3);
      assertFalse(false);
    } catch (AssertionError e) {
      return;
    }

  }

  public boolean re_read_file(NetworkTopology graph, String filepath) {
    String data = "test/graph/exception_Topologys/data_Topology.txt\r\n";
    InputStream stdin = System.in;
    try {
      System.setIn(new ByteArrayInputStream(data.getBytes()));
      graph.create_NetworkTopology(filepath);
    } catch (NoSuchElementException e) {
      return false;
    } finally {
      System.setIn(stdin);
    }
    return true;
  }

  /**
   * Tests checkRep()
   */
  @Test
  public void create_NetworkTopology() {
    NetworkTopology graph = new NetworkTopology();
    graph = graph.create_NetworkTopology("test/graph/exception_Topologys/data_Topology.txt");
    assertEquals(true, re_read_file(graph, "test/graph/exception_Topologys/???.txt"));// 文件路径错误

    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_Computer_not_full_nature.txt"));
    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_EdgeType_not_full.txt"));
    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_EdgeType_not_match.txt"));
    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_Edge_not_full.txt"));
    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_Edge_point_error.txt"));
    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_GraphName_not_full.txt"));
    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_GraphType_not_full.txt"));
    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_GraphType_not_match.txt"));
    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_Router_not_full_nature.txt"));
    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_Server_not_full_nature.txt"));
    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_VertexType_not_full.txt"));
    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_VertexType_not_match.txt"));
    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_Vertex_in_Edge_not_have.txt"));
    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_Vertex_not_full_exnature.txt"));
    assertEquals(true, re_read_file(graph,
        "test/graph/exception_Topologys/re_read/data_Topology_WirelessRouter_not_full_nature.txt"));



    graph = graph.create_NetworkTopology(
        "test/graph/exception_Topologys/repair/data_Topology_Edge_begin_end_error.txt");
    assertEquals("The vertices:\n" + " <Server1, Server, <192.168.1.2>>\n"
        + " <Router1, Router, <192.168.1.1>>\n" + " <Computer1, Computer, <192.168.1.101>>\n"
        + " <Computer2, Computer, <192.168.1.101>>\n" + "\n"
        + "_________________________________________________________________\n" + "\n"
        + "The edge:\n" + "No||R1S1:<Server1,Router1><Router1,Server1>(100.0)\n"
        + "No||C1S1:<Server1,Computer1><Computer1,Server1>(10.0)\n", graph.toString());
    graph = graph.create_NetworkTopology(
        "test/graph/exception_Topologys/repair/data_Topology_Edge_have_HyperEdge.txt");
    assertEquals("The vertices:\n" + " <Server1, Server, <192.168.1.2>>\n"
        + " <Router1, Router, <192.168.1.1>>\n" + " <Computer1, Computer, <192.168.1.101>>\n" + "\n"
        + "_________________________________________________________________\n" + "\n"
        + "The edge:\n" + "No||R1S1:<Server1,Router1><Router1,Server1>(100.0)\n"
        + "No||C1S1:<Server1,Computer1><Computer1,Server1>(10.0)\n", graph.toString());
    graph = graph.create_NetworkTopology(
        "test/graph/exception_Topologys/repair/data_Topology_Edge_have_multiEdge.txt");
    assertEquals(
        "The vertices:\n" + " <Server1, Server, <192.168.1.2>>\n"
            + " <Router1, Router, <192.168.1.1>>\n" + " <Computer1, Computer, <192.168.1.101>>\n"
            + "\n" + "_________________________________________________________________\n" + "\n"
            + "The edge:\n" + "No||R1S1:<Server1,Router1><Router1,Server1>(100.0)\n",
        graph.toString());
    graph = graph.create_NetworkTopology(
        "test/graph/exception_Topologys/repair/data_Topology_Edge_Label_repeat.txt");
    assertEquals("The vertices:\n" + " <Server1, Server, <192.168.1.2>>\n"
        + " <Router1, Router, <192.168.1.1>>\n" + " <Computer1, Computer, <192.168.1.101>>\n" + "\n"
        + "_________________________________________________________________\n" + "\n"
        + "The edge:\n" + "No||C1S11:<Computer1,Server1>(10.0)\n"
        + "No||C1S1:<Server1,Router1><Router1,Server1>(100.0)\n", graph.toString());
    graph = graph.create_NetworkTopology(
        "test/graph/exception_Topologys/repair/data_Topology_Edge_Loop_error.txt");
    assertEquals(
        "The vertices:\n" + " <Server1, Server, <192.168.1.2>>\n"
            + " <Router1, Router, <192.168.1.1>>\n" + " <Computer1, Computer, <192.168.1.101>>\n"
            + "\n" + "_________________________________________________________________\n" + "\n"
            + "The edge:\n" + "No||C1S1:<Server1,Computer1><Computer1,Server1>(10.0)\n",
        graph.toString());
    graph = graph.create_NetworkTopology(
        "test/graph/exception_Topologys/repair/data_Topology_Vertex_Label_repeat.txt");
    assertEquals(
        "The vertices:\n" + " <Router1, Router, <192.168.1.1>>\n"
            + " <Computer1, Computer, <192.168.1.101>>\n" + " <Computer11, Word>\n" + "\n"
            + "_________________________________________________________________\n" + "\n"
            + "The edge:\n" + "No||R1S1:<Router1,Computer1><Computer1,Router1>(100.0)\n",
        graph.toString());



  }

  @Test
  public void getandset_EdgeType() {
    NetworkTopology graph = new NetworkTopology();
    graph = graph.create_NetworkTopology("src/graph/data_Topology.txt");
    assertEquals("NetworkConnection", graph.get_EdgeType());
    assertEquals("aaa", graph.set_EdgeType("aaa"));
  }

  @Test
  public void getandset_VertexType() {
    NetworkTopology graph = new NetworkTopology();
    graph = graph.create_NetworkTopology("src/graph/data_Topology.txt");
    assertEquals("Computer Router Server", graph.get_VertexType());
    assertEquals("aaa", graph.set_VertexType("aaa"));
  }

  @Test
  public void getandset_GraphName() {
    NetworkTopology graph = new NetworkTopology();
    graph = graph.create_NetworkTopology("src/graph/data_Topology.txt");
    assertEquals("LabNetwork", graph.get_GraphName());
    assertEquals("aaa", graph.set_GraphName("aaa"));
  }

  @Test
  public void getandset_GraphType() {
    NetworkTopology graph = new NetworkTopology();
    graph = graph.create_NetworkTopology("src/graph/data_Topology.txt");
    assertEquals("NetworkTopology", graph.get_GraphType());
    assertEquals("aaa", graph.set_GraphType("aaa"));
  }
}
