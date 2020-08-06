package graph;

import edge.Edge;
import factory.NetworkTopologyFactory;
import factory.SocialNetworkFactory;
import vertex.Vertex;

public    class NetworkTopology extends ConcreteGraph{
	
	private String GraphType;
	private String GraphName;
	private String VertexType;
	private String EdgeType;

	
	
	public NetworkTopology create_NetworkTopology(String filepath) {		
		NetworkTopology graph =new NetworkTopologyFactory().createGraph(filepath);
		checkRep();
		return graph;
	}
	
	/*
	 * 设置图的类型
	 * 
	 * @param 要设置的GraphType
	 * @return 无
	 */
	public String set_GraphType(String a) {
		this.GraphType=a;
		return this.GraphType;
	}
	
	/*
	 *  返回图的名字
	 * 
	 * @param 要设置的GraphName
	 * @return 无
	 */
	public String set_GraphName(String a) {
		this.GraphName=a;
		return this.GraphName;
	}
	
	/*
	 * 返回图中边的类型
	 * 
	 * @param 要设置的EdgeType
	 * @return 无
	 */
	public String set_EdgeType(String a) {
		this.EdgeType=a;
		return this.EdgeType;
	}
	
	/*
	 * 设置图中顶点的类型
	 * 
	 * @param 要设置的VertexType
	 * @return 无
	 */
	public String set_VertexType(String a) {
		this.VertexType=a;
		return this.VertexType;
	}
	/*
	 * 返回图的类型
	 * 
	 * @param 无
	 * @return 返回图的类型
	 */
	public String get_GraphType() {
		return this.GraphType;
	}
	
	/*
	 *  返回图的名字
	 * 
	 * @param 无
	 * @return  返回图的名字
	 */
	public String get_GraphName() {
		return this.GraphName;
	}
	
	/*
	 * 返回图中边的类型
	 * 
	 * @param 无
	 * @return 返回图中边的类型
	 */
	public String get_EdgeType() {
		return this.EdgeType;
	}
	
	/*
	 * 返回图中顶点的类型
	 * 
	 * @param 无
	 * @return 返回图中顶点的类型
	 */
	public String get_VertexType() {
		return this.VertexType;
	}
	@Override
	public String toString() {
		String a = "The vertices:"+"\n";
		for(Vertex v :vertices()) {
			a=a+v.toString();
		}
    	a=a+"\n"+"_________________________________________________________________"+"\n"+"\n"+"The edge:"+"\n";
    	for(Edge e: edges()) {
    		a=a+e.toString();
    	}
    	return a;
	}

	@Override
	protected void checkRep() {
		for(Edge e:edges()) {
//    		for(Vertex x: e.sourceVertices()) {
//    			assert contain(x, vertices());
//    		}for(Vertex x: e.targetVertices()) {
//    			assert contain(x, vertices());
//    		}
    		assert e.get_weight()>=0;
    	}
		
	}

}
