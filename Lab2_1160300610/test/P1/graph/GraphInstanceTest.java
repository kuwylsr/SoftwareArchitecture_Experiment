/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    // Testing strategy
    //   TODO
	
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    
    /**
     * Tests add.
     */
    @Test
    public void add() {
    	Graph<String> edge_graph=emptyInstance();
        assertEquals(true,edge_graph.add("siri"));// 新加入的点 不存在
        assertEquals(false,edge_graph.add("siri"));// 新加入的点 存在
    }
    
    /**
     * Tests set.
     */
    @Test
    public void set() {
    	Graph<String> edge_graph=emptyInstance();
    	assertEquals(0,edge_graph.set("a", "b", 20));// 权重不为零，边不存在
    	assertEquals(0,edge_graph.set("a", "c", 30));
    	assertEquals(0,edge_graph.set("b", "c", 10));
    	assertEquals(0,edge_graph.set("b", "d", 0));// 权重为零，边不存在
    	assertEquals(20,edge_graph.set("a", "b", 50));// 权重不为零，边存在
    	assertEquals(50,edge_graph.set("a", "b", 0));// 权重为零，边存在
    }
    /**
     * Tests remove.
     */
    @Test
    public void remove() {
    	Graph<String> edge_graph=emptyInstance();
    	edge_graph.set("a", "b", 20);
    	edge_graph.set("a", "c", 30);
    	edge_graph.set("b", "c", 10);
    	edge_graph.set("a", "b", 50);
    	assertEquals(true, edge_graph.remove("a"));//之前 点不存在
    	assertEquals(false, edge_graph.remove("a"));//之前 点存在
    }
    
    /**
     * Tests vertices.
     */
    @Test
    public void vertices() {
    	Graph<String> edge_graph=emptyInstance();
    	Set<String> s=new HashSet<>();
    	edge_graph.add("siri");
    	edge_graph.add("sir");
    	edge_graph.add("si");
    	s.add("siri");
    	s.add("sir");
    	s.add("si");
    	assertEquals(s, edge_graph.vertices());
    }
    
    /**
     * Tests sources.
     */
    @Test
    public void sources() {
    	Map<String, Integer> map=new HashMap<>();
    	Graph<String> edge_graph=emptyInstance();
    	edge_graph.set("a", "b", 20);
    	edge_graph.set("a", "c", 30);
    	edge_graph.set("b", "c", 10);
    	edge_graph.set("d", "b", 50);
    	edge_graph.set("e", "b", 5);
    	map.put("a", 20);
    	map.put("d", 50);
    	map.put("e", 5);
    	assertEquals(map, edge_graph.sources("b"));
    }
    
    /**
     * Tests targets.
     */
    @Test
    public void targets() {
    	Map<String, Integer> map=new HashMap<>();
    	Graph<String> edge_graph=emptyInstance();
    	edge_graph.set("a", "b", 20);
    	edge_graph.set("a", "c", 30);
    	edge_graph.set("b", "c", 10);
    	edge_graph.set("d", "b", 50);
    	edge_graph.set("e", "b", 5);
    	map.put("b", 20);
    	map.put("c", 30);
    	assertEquals(map, edge_graph.targets("a"));
    }
    
}
