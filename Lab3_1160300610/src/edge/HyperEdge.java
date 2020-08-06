package edge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vertex.Vertex;

public abstract class HyperEdge extends Edge{
	
	// Abstraction function:
    
    // Representation invariant（表示 不变性）:
    
    // Safety from rep exposure（表示暴露的安全性）:
	Collection<Vertex> vertices=new ArrayList<>();
	
	
	/*
	 * 
	 * 
	 * @param 
	 * @return 
	 */
	public HyperEdge(String label, double weight,List<Vertex> vertices) {
		super(label, -1,vertices);
		this.vertices.addAll(vertices);
	}

	@Override
	public boolean addVertices(List<Vertex> vertices) {
		if(vertices.size()>2) {
			this.vertices.addAll(vertices);
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Set<Vertex> sourceVertices() {
		Set<Vertex> s=new HashSet<>();
		s.addAll(vertices);
		return s;
	}

	@Override
	public Set<Vertex> targetVertices() {
		Set<Vertex> s=new HashSet<>();
		s.addAll(vertices);
		return s;
	}

}
