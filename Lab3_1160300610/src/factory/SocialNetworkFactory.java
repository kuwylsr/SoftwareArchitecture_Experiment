package factory;

import java.io.File;
import java.io.IOException;

import graph.SocialNetwork;

public class SocialNetworkFactory extends GraphFactory{
	
	@Override
	public  SocialNetwork createGraph(String filePath) throws IOException {
		SocialNetwork graph =new SocialNetwork();
		graph.Read_file(new File(filePath), graph);
		return graph;
	}
}
