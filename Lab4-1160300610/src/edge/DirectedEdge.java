package edge;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import vertex.Vertex;

public abstract class DirectedEdge extends Edge {

	// Abstraction function:
	//将D_edge映射为一条边，key为边的起点，value为边的终点
    
    // Representation invariant（表示 不变性）:
	//无
    
    // Safety from rep exposure（表示暴露的安全性）:
	//  所有的区域块都是私有的。
	//总之不存在表示暴露。
	private Map<Vertex,Vertex> D_edge=new HashMap<>();
	

	/*
	 * 有向边的构造函数
	 * 
	 * @param 边的标签，边的权重，边的起点，边的终点，边所包含的点的集合
	 * @return 无
	 */
	public DirectedEdge(String label, double weight,Vertex source,Vertex target,Collection<Vertex> vertices) {
		super(label, weight, vertices);
		this.D_edge.put(source, target);
	}

	/*
	 * 获取有向边的map
	 * 
	 * @param 无
	 * @return 返回有向边的map
	 */
	public Map<Vertex,Vertex> get_map(){
		Map<Vertex,Vertex> D_edge1=new HashMap<>();
		for(Map.Entry<Vertex, Vertex> entry : D_edge.entrySet()) {
			D_edge1.put(entry.getKey(), entry.getValue());
		}
		return D_edge1;
	}
	
	@Override
	public boolean addVertices(List<Vertex> vertices) {
		if(vertices.size()==2) {
			D_edge.put(vertices.get(0), vertices.get(1));
			return true;
		}else if(vertices.size()==1){
			D_edge.put(vertices.get(0), vertices.get(0));
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public Set<Vertex> sourceVertices() {
		return D_edge.keySet();
	}

	@Override
	public Set<Vertex> targetVertices() {
		Set<Vertex> s=new HashSet<>();
		s.addAll(D_edge.values());
		return s;
	}
	
	@Override
	public void set_point() {
		Vertex x1=null;
		Vertex x2 =null;
		for(Entry<Vertex, Vertex> entry : this.D_edge.entrySet()) {
			x1=entry.getKey();
			x2 =entry.getValue();
			break;
		}
		this.D_edge.remove(x1);
		this.D_edge.put(x2, x1);
	}
		
	
	

}
