package factory;

import java.util.List;

import edge.MovieActorRelation;
import vertex.Vertex;

public class MovieActorRelationFactory extends EdgeFactory{
	
	@Override
	public  MovieActorRelation createEdge(String label, List<Vertex> vertices,List<Double> weights) {
		
		return new MovieActorRelation(label, weights.get(0), vertices.get(0), vertices.get(1), "No", vertices);
		
	}
}
