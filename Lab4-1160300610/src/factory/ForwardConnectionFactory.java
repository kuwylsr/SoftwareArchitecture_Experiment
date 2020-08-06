package factory;

import java.util.List;

import edge.ForwardTie;
import vertex.Vertex;

public class ForwardConnectionFactory extends EdgeFactory{
	
	
	@Override
	public  ForwardTie createEdge(String label, List<Vertex> vertices,List<Double> weights) {
		
		return new ForwardTie(label, weights.get(0), vertices.get(0), vertices.get(1), "Yes", vertices);
		
	}
}
