package factory;

import java.io.File;
import java.io.IOException;

import graph.MovieGraph;

public class MovieGraphFactory extends GraphFactory{
	
	@Override
	public  MovieGraph createGraph(String filePath) throws IOException {
		MovieGraph graph =new MovieGraph();
		graph.Read_file(new File(filePath), graph);
		return graph;
	}
}
