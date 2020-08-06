/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;


import org.junit.Test;


/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<String>();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    /**
     * 断言已启用的测试
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
   
    
    
    /*
     * Testing strategy for ConcreteEdgesGraph.toString()
     * 
     *Partition the graph.set as follows:
     *图中的边：有多条边指向同一点，有一个点指向多个点，一个点自己指向自己 
     * 
     */
    
    // TODO tests for ConcreteEdgesGraph.toString()
    @Test
    public void ConcreteEdgesGraph() {
    	Graph<String> edge_graph=emptyInstance();
    	edge_graph.set("a", "b", 20);//一个点指向多个点
    	edge_graph.set("a", "c", 30);
    	edge_graph.set("a", "a", 30);//自环
    	edge_graph.set("b", "c", 10);
    	edge_graph.set("b", "d", 70);
    	edge_graph.set("a", "b", 50);
    	assertEquals("The vertices:[a, b, c, d]  The edge:[(a,b)--50, (a,c)--30, (a,a)--30, (b,c)--10, (b,d)--70]", edge_graph.toString());
    }
    
    
    /*
     * Testing Edge...
     */
    
    /*
     * Testing strategy for Edge
     * 
     * 边的情况：一般的边，边的两个顶点相同
     * 
     */ 
    @Test
    public void Edge() {
    	assertEquals("(a,b)--20", new Edge<String>("a","b",20).toString());
    	assertEquals("(a,a)--20", new Edge<String>("a","a",20).toString());
    }
 // TODO tests for operations of Edge
    /*
     * Testing strategy for get_source()
     * 
     * 无测试方法的分类，函数较简单，直接测试找source即可
     * 
     */ 
    @Test
    public void get_source() {
    	Edge<String> e=new Edge<String>("a", "b", 76);
    	assertEquals("a",e.get_source());
    }
    /*
     * Testing strategy for get_target()
     * 
     * 无测试方法的分类，函数较简单，直接测试找target即可
     * 
     */ 
    @Test
    public void get_target() {
    	Edge<String> e=new Edge<String>("a", "b", 76);
    	assertEquals("b",e.get_target());
    }
    /*
     * Testing strategy for get_weight()
     * 
     * 无测试方法的分类，函数较简单，直接测试找weight即可
     * 
     */ 
    @Test
    public void get_weight() {
    	Edge<String> e=new Edge<String>("a", "b", 76);
    	assertEquals(76,e.get_weight());
    }
    /*
     * Testing strategy for set_weight()
     * 
     * 无测试方法的分类，函数较简单，直接测试更改weight即可
     * 
     */ 
    @Test
    public void set_weight() {
    	Edge<String> e=new Edge<String>("a", "b", 76);
    	assertEquals(30,e.set_weight(30));
    }
    
    
}
