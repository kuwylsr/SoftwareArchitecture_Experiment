/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<String>();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    /**
     * 断言已启用的测试
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
    
    /*
     * Testing strategy for ConcreteVerticesGraph.toString()
     * 
     *Partition the graph.set as follows:
     *图中的点：有多条边指向同一点，有一个点指向多个点，一个点自己指向自己 
     * 
     */
    
    // TODO tests for ConcreteVerticesGraph.toString()
    @Test
    public void ConcreteVerticesGraph() {
    	Graph<String> edge_graph=emptyInstance();
    	edge_graph.set("a", "b", 20);
    	edge_graph.set("a", "c", 30);
    	edge_graph.set("b", "c", 10);
    	edge_graph.set("b", "d", 70);
    	edge_graph.set("a", "a", 10);
    	assertEquals("[title:a  (a->a)(a->a)(a->b)(a->c), title:b  (a->b)(b->d)(b->c), title:c  (a->c)(b->c), title:d  (b->d)]",edge_graph.toString());
    }    
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO
    
    // TODO tests for operations of Vertex
    @Test
    public void Vertex() {
    	Vertex<String> A= new Vertex<String>("a");
    	Vertex<String> B= new Vertex<String>("b");
    	Vertex<String> C= new Vertex<String>("c");
    	A.get_v_target().put(B,20);
    	A.get_v_target().put(C,30);
    	assertEquals("title:a  (a->b)(a->c)",A.toString());
    }
}
