package edge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import vertex.Vertex;

public abstract class HyperEdge extends Edge{
	
	private Collection<Vertex> vertices=new ArrayList<>();
	
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
	
	public boolean remove_vertex(Vertex x) {
		if(vertices.size()<=2) {
			return false;
		}
		Iterator<Vertex> it = vertices.iterator();
		while(it.hasNext()) {
			if(it.next().equals(x)) {
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public  void set_point() {
		
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
