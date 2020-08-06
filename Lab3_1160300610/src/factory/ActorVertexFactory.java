package factory;

import vertex.Actor;

public class ActorVertexFactory extends VertexFactory{
	
	@Override
	public  Actor createVertex(String label, String[] args) {
		Actor v =new Actor(label);
		v.fillVertexInfo(args);
		return v;
	}
}
