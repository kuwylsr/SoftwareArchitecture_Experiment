package edge;

import java.util.List;

import vertex.Vertex;

public class SameMovieHyperEdge extends HyperEdge{

	/*
	 * 超边的构造函数
	 * 
	 * @param 边的标签，边的权重，边所包含的点的集合
	 * @return 无
	 */
	public SameMovieHyperEdge(String label, double weight, List<Vertex> vertices) {
		super(label, weight, vertices);
		// TODO 自动生成的构造函数存根
	}

	@Override
	public String toString() {	
		String a="H"+"||"+get_label()+":"+"<";
		for(Vertex v :vertices) {
			a=a+v.getLabel()+",";
		}
		return a+">"+"\n";
	}


	@Override
	public void set_point() {

	}
	
	@Override
	public boolean addVertices(List<Vertex> vertices) {
		for(Vertex x: vertices) {
			super.vertices.add(x);
			return true;
		}
		return false;
		
	}

}
