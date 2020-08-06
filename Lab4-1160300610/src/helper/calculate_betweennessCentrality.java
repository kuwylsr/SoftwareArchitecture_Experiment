package helper;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public class calculate_betweennessCentrality implements Degree_vertex{
	

		// Abstraction function:
		//如果表示属于g，则映射为一张图
		//如果表示属于v，则映射为顶点
		    
		// Representation invariant（表示 不变性）:
		// 无
		    
		// Safety from rep exposure（表示暴露的安全性）:
		//  所有的区域块都是私有的。
		//总之不存在表示暴露。
	
	private final Graph< Vertex, Edge> g;  
    private final Vertex v;
	
    
    /*
	 * 构造函数
	 * 
	 * @param 图g，顶点v
	 * @return 无
	 */
    public calculate_betweennessCentrality(Graph< Vertex, Edge> g,Vertex v) {
    	this.g=g;
    	this.v=v;
    }
	
	@Override
	public double V_degree() {
		return GraphMetrics.betweennessCentrality(g, v);
	}

}
