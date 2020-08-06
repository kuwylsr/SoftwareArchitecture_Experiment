package factory;

import vertex.Movie;

public class MovieVertexFactory extends VertexFactory{
	
	@Override
	public  Movie createVertex(String label, String[] args) {
		Movie v =new Movie(label);
		v.fillVertexInfo(args);
		return v;
	}
}
