package edge;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vertex.Vertex;

public abstract class UndirectedEdge extends Edge{
	
		// Abstraction function:
		//将D_edge映射为一条边，key为边的起点，value为边的终点
	    
	    // Representation invariant（表示 不变性）:
		//存在在UnD_edge中的key和其所对应的value必须成对出现
	    
	    // Safety from rep exposure（表示暴露的安全性）:
		//  所有的区域块都是私有的。
		//总之不存在表示暴露

	private Map<Vertex,Vertex> UnD_edge=new HashMap<>();
	
	/*
	 * 有向边的构造函数
	 * 
	 * @param 边的标签，边的权重，边的起点，边的终点，边所包含的点的集合
	 * @return 无
	 */
	public UndirectedEdge(String label, double weight,Vertex x1,Vertex x2,Collection<Vertex> vertices) {
		super(label, weight,vertices);
		this.UnD_edge.put(x1, x2);
		this.UnD_edge.put(x2, x1);
	}

	/*
	 * 获取无向边的map
	 * 
	 * @param 无
	 * @return 返回无向边的map
	 */
	public Map<Vertex,Vertex> get_map(){
		Map<Vertex,Vertex> D_edge1=new HashMap<>();
		for(Map.Entry<Vertex, Vertex> entry : UnD_edge.entrySet()) {
			D_edge1.put(entry.getKey(), entry.getValue());
		}
		return D_edge1;
	}
	
	@Override
	public boolean addVertices(List<Vertex> vertices) {
		if(vertices.size()==2) {
			UnD_edge.put(vertices.get(0), vertices.get(1));
			UnD_edge.put(vertices.get(1), vertices.get(0));
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Set<Vertex> sourceVertices() {
		return UnD_edge.keySet();
	}

	@Override
	public Set<Vertex> targetVertices() {
		Set<Vertex> s=new HashSet<>();
		s.addAll(UnD_edge.values());
		return s;
	}
	
	@Override
	public void set_point() {
		
	}
		
	

}
