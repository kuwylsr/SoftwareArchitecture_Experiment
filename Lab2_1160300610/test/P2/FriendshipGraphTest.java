package P2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;




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
     * 
     * 加入多个不同顶点，加入相同顶点。
     * 
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
        assertEquals(1,graph.addVertex(a));//假如相同的点
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
     * 
     * 加入双向边，加入单向边
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
        assertEquals(1,graph.addEdge(b, a));
        assertEquals(2,graph.addEdge(a, e));
        assertEquals(1,graph.addEdge(e, a));
        assertEquals(2,graph.addEdge(b, e));
        assertEquals(2,graph.addEdge(e, b));
        assertEquals(3,graph.addEdge(e, f));
        assertEquals(1,graph.addEdge(f, e));
        assertEquals(1,graph.addEdge(d, e));
        assertEquals(4,graph.addEdge(e, d));
        assertEquals(2,graph.addEdge(d, g));
        assertEquals(1,graph.addEdge(g, d));
        assertEquals(3,graph.addEdge(d, h));
        assertEquals(1,graph.addEdge(h, d));
        assertEquals(2,graph.addEdge(f, i));
        assertEquals(1,graph.addEdge(i, f));
    }
    /**
     * Tests getDistance.
     * 
     * 
     * 起点和终点相同，找不到终点，普遍情况
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
        assertEquals(1,graph.addEdge(b, a));
        assertEquals(2,graph.addEdge(a, e));
        assertEquals(1,graph.addEdge(e, a));
        assertEquals(2,graph.addEdge(b, e));
        assertEquals(2,graph.addEdge(e, b));
        assertEquals(3,graph.addEdge(e, f));
        assertEquals(1,graph.addEdge(f, e));
        assertEquals(1,graph.addEdge(d, e));
        assertEquals(4,graph.addEdge(e, d));
        assertEquals(2,graph.addEdge(d, g));
        assertEquals(1,graph.addEdge(g, d));
        assertEquals(3,graph.addEdge(d, h));
        assertEquals(1,graph.addEdge(h, d));
        assertEquals(2,graph.addEdge(f, i));
        assertEquals(1,graph.addEdge(i, f));
        assertEquals(3,graph.getDistance(a, i));
        assertEquals(2,graph.getDistance(b, d));
        assertEquals(0,graph.getDistance(e, e));
        assertEquals(-1,graph.getDistance(g, c));
    }
}
