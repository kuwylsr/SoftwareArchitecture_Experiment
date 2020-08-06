package graph;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vertex.Vertex;


public interface Graph<L,E> {
	
	
	/*
	 * 向图中增加一个节点
	 * 
	 * @param 所要增加的顶点
	 * @return 返回是否添加了一个节点，是为true，否为false
	 */
	public boolean addVertex(L v);
	
	
	/*
	 * 从图中删除一个节点v。如果v是某条边的两端之一，则该边被删除；
	 * 如果某节点属于某条超边，若该节点删除后该条超边仍可合法存在，则该超边保留，否则就删除之
	 * 
	 * @param 所要删除的顶点
	 * @return 返回节点是否删除成功，是为true，否为false
	 */
	public boolean removeVertex(L v);
	
	/*
	 * 返回图的节点集合
	 * 
	 * @param 无
	 * @return 返回图的节点集合
	 */
	public Set<L> vertices();
	
	/*
	 * 返回Map中Key为source节点，List<Double>为当前节点与该source节点之间的所有边的权值；
	 * 如果与target相连的边包括无向边，则无向边的另一端节点也需包含在返回值Map中；
	 * 不需考虑超边。
	 * 
	 * @param 边的终点
	 * @return 返回一个Map，满足返回Map中Key为source节点，List<Double>为当前节点与该source节点之间的所有边的权值；
	 * 如果与target相连的边包括无向边，则无向边的另一端节点也需包含在返回值Map中；
	 * 不需考虑超边。
	 */
	public Map<L, List<Double>> sources(L target);
	
	/*
	 * 如果与source相连的边包括无向边，则无向边的另一端节点也需包含在返回值Map中；
	 * 不需考虑超边。
	 * 
	 * @param 边的起点
	 * @return 返回一个Map，满足如果与source相连的边包括无向边，则无向边的另一端节点也需包含在返回值Map中；
	 * 不需考虑超边。
	 */
	public Map<L,List<Double>> targets(L source);
	
	/*
	 * 增加一条边（包括超边）
	 * 
	 * @param 所要增加的边
	 * @return 返回是否增加成功，是为true，否为false
	 */
	public boolean addEdge(E edge);
	
	/*
	 * 删除一条边（包括超边）
	 * 
	 * @param 所要删除的边
	 * @return 返回是否删除成功，是为true，否为false
	 */
	public boolean removeEdge(E edge);
	
	/*
	 * 返回边的集合（包括超边）
	 * 
	 * @param 无
	 * @return 返回边的集合（包括超边）
	 */
	public Set<E> edges();
	
	
	/*
	 * 返回图的类型
	 * 
	 * @param 无
	 * @return 返回图的类型
	 */
	public String get_GraphType();
	
	/*
	 * 读取文件信息，并根据信息创建图
	 * 
	 * @param 文件file，空图graph
	 * @return 返回是否正确读取文件，并创建图
	 */
	public boolean Read_file(File file,ConcreteGraph graph) throws IOException;
}
