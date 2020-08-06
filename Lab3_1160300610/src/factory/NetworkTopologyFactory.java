package factory;

import java.io.File;
import java.io.IOException;

import graph.NetworkTopology;

public class NetworkTopologyFactory extends GraphFactory{
	
	@Override
	public  NetworkTopology createGraph(String filePath) throws IOException {
		NetworkTopology graph =new NetworkTopology();
		graph.Read_file(new File(filePath), graph);
		return graph;
	}
}
