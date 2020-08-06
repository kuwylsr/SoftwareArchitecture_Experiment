package graph;

import java.util.Iterator;


import edge.Edge;
import vertex.Vertex;

public   class SocialNetwork extends ConcreteGraph{
	

	
	@Override
	public boolean addEdge(Edge edge) {
		for(Edge e:edges()) {
			if(e.equals(edge)) {
				return false;
			}else {
				continue;
			}
		}
		double w_a=edge.get_weight();
		for(Edge e1 : edges()) {
			double w_b=e1.get_weight();
			e1.set_weight(w_b*(1-w_a));
		}
		edges().add(edge);
		return true;		
	}
	
	@Override
	public boolean removeEdge(Edge edge) {
		Iterator<Edge> it = edges().iterator();
		while(it.hasNext()) {
			Edge e =it.next();
			if(e.equals(edge)){
				double w_a = e.get_weight();				
				for(Edge e1 : edges()) {
					double w_b=e1.get_weight();
					e1.set_weight(w_b/(1-w_a));
				}
				it.remove();
				return true;
			}else {
				continue;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		String a = "The vertices:"+"\n";
		for(Vertex v :vertices()) {
			a=a+v.toString();
		}
    	a=a+"\n"+"_________________________________________________________________"+"\n"+"\n"+"The edge:"+"\n";
    	for(Edge e: edges()) {
    		a=a+e.toString();
    	}
    	return a;
	}
	

}
