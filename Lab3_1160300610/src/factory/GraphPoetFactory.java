package factory;

import java.io.File;
import java.io.IOException;

import graph.GraphPoet;

public class GraphPoetFactory extends GraphFactory{
	
	@Override
	public  GraphPoet createGraph(String filePath) throws IOException {
		GraphPoet graph =new GraphPoet();
		graph.Read_file(new File(filePath), graph);
		return graph;
	}
}
