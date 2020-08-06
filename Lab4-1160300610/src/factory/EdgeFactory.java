package factory;

import java.util.List;

import edge.Edge;
import vertex.Vertex;

public abstract class EdgeFactory {

	/*
	 * 创建边对象的工厂方法
	 * 
	 * @param 边的label，边所包含点的集合vertices，边的权重集合weights
	 * @return 返回图的实例化对象
	 */
	public abstract Edge createEdge(String label, List<Vertex> vertices, List<Double> weights);
}
