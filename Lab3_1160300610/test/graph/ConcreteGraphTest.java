package graph;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public abstract class ConcreteGraphTest {

	
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
    public void pattern() {
    	ConcreteGraph graph=new GraphPoet();
    	assertEquals("lisirui",graph.pattern("\"lisirui\""));
    }
    
    /**
     * Tests add.
     */
    @Test
    public void first_String() {
    	ConcreteGraph graph=new GraphPoet();
    	assertEquals("lisirui",graph.first_String("lisirui llll"));
    }
    
}
