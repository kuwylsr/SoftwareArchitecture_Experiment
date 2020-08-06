/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import P1.graph.Graph;


/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   查看读取文件之后，建图的顶点和边是否正确即可
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TODO tests
    /*
     * Testing GraphPoet.toString().
     */
    @Test
    public void GraphPoet() throws IOException {
    	File file=new File("test/P1/poet/poem.txt");
    	assertEquals("The vertices:[new, worlds, explore, and, to, civilizations, seek, strange, life, out]  The edge:[(to,explore)--1, (explore,strange)--1, (strange,new)--1, (new,worlds)--1, (worlds,to)--1, (to,seek)--1, (seek,out)--1, (out,new)--1, (new,life)--1, (life,and)--1, (and,new)--1, (new,civilizations)--1]",new GraphPoet(file).toString());
    }
    
    /*
     * Testing poem.
     */
    @Test
    public void poem() throws IOException {
    	File file=new File("test/P1/poet/poem.txt");
    	GraphPoet poet=new GraphPoet(file);
    	assertEquals("Seek to explore strange new life and exciting synergies!",poet.poem("Seek to explore new and exciting synergies!"));
    }

}
