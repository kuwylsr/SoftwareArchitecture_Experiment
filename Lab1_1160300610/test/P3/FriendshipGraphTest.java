package P3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import P3.FriendshipGraph;


/*assertEquals([String message,]Object expected,Object actual);
判断是否相等，可以指定输出错误信息。
第一个参数是期望值，第二个参数是实际的值。
这个方法对各个变量有多种实现。在JDK1.5中基本一样。
但是需要主意的是float和double最后面多一个delta的值，可能是误差范围
*/

public class FriendshipGraphTest {
	/**
     * 断言已启用的测试
     */
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false;
    }
     
    /**
     * Tests addVertex.
     */
    @Test
    public void addVertex() {
    	FriendshipGraph graph =new FriendshipGraph();
    	Person a =new Person("A");
    	Person b =new Person("B");
    	Person c =new Person("C");
    	Person d =new Person("D");
    	Person e =new Person("E");
    	Person f =new Person("F");
    	Person g =new Person("G");
    	Person h =new Person("H");
    	Person i =new Person("I");
        assertEquals(1,graph.addVertex(a));
        assertEquals(2,graph.addVertex(b));
        assertEquals(3,graph.addVertex(c));
        assertEquals(4,graph.addVertex(d));
        assertEquals(5,graph.addVertex(e));
        assertEquals(6,graph.addVertex(f));
        assertEquals(7,graph.addVertex(g));
        assertEquals(8,graph.addVertex(h));
        assertEquals(9,graph.addVertex(i));
    }
    /**
     * Tests addEdge.
     */
    @Test
    public void addEdge() {
    	FriendshipGraph graph =new FriendshipGraph();
    	Person a =new Person("A");
    	Person b =new Person("B");
    	Person c =new Person("C");
    	Person d =new Person("D");
    	Person e =new Person("E");
    	Person f =new Person("F");
    	Person g =new Person("G");
    	Person h =new Person("H");
    	Person i =new Person("I");
        assertEquals(1,graph.addEdge(a, b));
        assertEquals(2,graph.addEdge(b, a));
        assertEquals(3,graph.addEdge(a, e));
        assertEquals(4,graph.addEdge(e, a));
        assertEquals(5,graph.addEdge(b, e));
        assertEquals(6,graph.addEdge(e, b));
        assertEquals(7,graph.addEdge(e, f));
        assertEquals(8,graph.addEdge(f, e));
        assertEquals(9,graph.addEdge(d, e));
        assertEquals(10,graph.addEdge(e, d));
        assertEquals(11,graph.addEdge(d, g));
        assertEquals(12,graph.addEdge(g, d));
        assertEquals(13,graph.addEdge(d, h));
        assertEquals(14,graph.addEdge(h, d));
        assertEquals(15,graph.addEdge(f, i));
        assertEquals(16,graph.addEdge(i, f));
    }
    /**
     * Tests getDistance.
     */
    @Test
    public void getDistance() {
    	FriendshipGraph graph =new FriendshipGraph();
    	Person a =new Person("A");
    	Person b =new Person("B");
    	Person c =new Person("C");
    	Person d =new Person("D");
    	Person e =new Person("E");
    	Person f =new Person("F");
    	Person g =new Person("G");
    	Person h =new Person("H");
    	Person i =new Person("I");
    	assertEquals(1,graph.addVertex(a));
        assertEquals(2,graph.addVertex(b));
        assertEquals(3,graph.addVertex(c));
        assertEquals(4,graph.addVertex(d));
        assertEquals(5,graph.addVertex(e));
        assertEquals(6,graph.addVertex(f));
        assertEquals(7,graph.addVertex(g));
        assertEquals(8,graph.addVertex(h));
        assertEquals(9,graph.addVertex(i));
        assertEquals(1,graph.addEdge(a, b));
        assertEquals(2,graph.addEdge(b, a));
        assertEquals(3,graph.addEdge(a, e));
        assertEquals(4,graph.addEdge(e, a));
        assertEquals(5,graph.addEdge(b, e));
        assertEquals(6,graph.addEdge(e, b));
        assertEquals(7,graph.addEdge(e, f));
        assertEquals(8,graph.addEdge(f, e));
        assertEquals(9,graph.addEdge(d, e));
        assertEquals(10,graph.addEdge(e, d));
        assertEquals(11,graph.addEdge(d, g));
        assertEquals(12,graph.addEdge(g, d));
        assertEquals(13,graph.addEdge(d, h));
        assertEquals(14,graph.addEdge(h, d));
        assertEquals(15,graph.addEdge(f, i));
        assertEquals(16,graph.addEdge(i, f));
        assertEquals(3,graph.getDistance(a, i));
        assertEquals(2,graph.getDistance(b, d));
        assertEquals(0,graph.getDistance(e, e));
        assertEquals(-1,graph.getDistance(g, c));
    }
}
