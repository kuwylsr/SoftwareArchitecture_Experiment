package factory;

import java.util.List;

import edge.MovieDirectorRelation;
import vertex.Vertex;

public class MovieDirectorRelationFactory extends EdgeFactory{
	
	@Override
	public  MovieDirectorRelation createEdge(String label, List<Vertex> vertices,List<Double> weights) {
		
		return new MovieDirectorRelation(label, weights.get(0), vertices.get(0), vertices.get(1), "No", vertices);
		
	}
}
