package edge;

import java.util.Collection;
import java.util.Map;

import vertex.Vertex;

public class MovieDirectorRelation extends UndirectedEdge{
	
	// Abstraction function:
    //point 映射为边是有向边还是 无向边
	
    // Representation invariant（表示 不变性）:
	// point 只能有两种取值 YES 和 NO
    
    // Safety from rep exposure（表示暴露的安全性）:
	//  所有的区域块都是私有的。
	//总之不存在表示暴露
	
	private String point;

	/*
	 * MovieDirectorRelation边的构造函数
	 * 
	 * @param 边的标签，边的权重，边的起点，边的终点，边所包含的点的集合
	 * @return 无
	 */
	public MovieDirectorRelation(String label, double weight, Vertex x1, Vertex x2,String point,Collection<Vertex> vertices) {
		super(label, weight, x1, x2,vertices);
		this.point=point;
		// TODO 自动生成的构造函数存根
	}

	@Override
	public String toString() {	
		String a=this.point+"||"+get_label()+":";
		for(Map.Entry<Vertex, Vertex> entry: get_map().entrySet()) {
			a=a+"<"+entry.getKey().getLabel()+","+entry.getValue().getLabel()+">";
		}
		return a+"("+this.get_weight()+")"+"\n";
	}

}
