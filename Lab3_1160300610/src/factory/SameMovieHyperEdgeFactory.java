package factory;

import java.util.List;

import edge.SameMovieHyperEdge;
import vertex.Vertex;

public class SameMovieHyperEdgeFactory extends EdgeFactory{
	
	@Override
	public  SameMovieHyperEdge createEdge(String label, List<Vertex> vertices,List<Double> weights) {
		
		return new SameMovieHyperEdge(label, weights.get(0), vertices);
		
	}
}
