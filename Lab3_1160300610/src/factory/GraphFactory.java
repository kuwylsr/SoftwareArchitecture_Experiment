package factory;

import java.io.IOException;

import edge.Edge;
import graph.Graph;
import vertex.Vertex;

public abstract class GraphFactory {

	/*
	 * 创建图对象的工厂方法
	 * 
	 * @param 文件路径字符串
	 * @return 返回图的实例化对象
	 */
	public abstract Graph<Vertex, Edge> createGraph(String filePath)throws IOException;
}
