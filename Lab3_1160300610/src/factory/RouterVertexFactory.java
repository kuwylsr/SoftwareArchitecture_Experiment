package factory;

import vertex.Router;

public class RouterVertexFactory extends VertexFactory{
	
	@Override
	public  Router createVertex(String label, String[] args) {
		Router v =new Router(label);
		v.fillVertexInfo(args);
		return v;
	}
}
