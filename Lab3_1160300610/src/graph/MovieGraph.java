package graph;


import edge.Edge;
import vertex.Vertex;

public class MovieGraph extends ConcreteGraph {
	

	
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
