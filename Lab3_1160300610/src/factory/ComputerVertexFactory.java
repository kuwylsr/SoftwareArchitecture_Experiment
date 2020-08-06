package factory;

import vertex.Computer;

public class ComputerVertexFactory extends VertexFactory{
	
	@Override
	public  Computer createVertex(String label, String[] args) {
		Computer v =new Computer(label);
		v.fillVertexInfo(args);
		return v;
	}
}
