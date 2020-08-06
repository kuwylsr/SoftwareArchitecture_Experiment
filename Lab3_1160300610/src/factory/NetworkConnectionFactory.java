package factory;

import java.util.List;

import edge.NetworkConnection;
import vertex.Vertex;

public class NetworkConnectionFactory extends EdgeFactory{
	

	@Override
	public  NetworkConnection createEdge(String label, List<Vertex> vertices,List<Double> weights) {
		
		return new NetworkConnection(label, weights.get(0), vertices.get(0), vertices.get(1), "NO", vertices);
		
	}
}
