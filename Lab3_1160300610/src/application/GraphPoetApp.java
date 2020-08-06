package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import edge.Edge;
import edge.WordNeighborhood;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import graph.GraphPoet;
import graph.MovieGraph;
import graph.SocialNetwork;
import helper.Context_degree;
import helper.GraphMetrics;
import helper.GraphVisualizationHelper;
import helper.ParseCommandHelper;
import helper.calculate_betweennessCentrality;
import helper.calculate_closenessCentrality;
import helper.calculate_degreeCentrality;
import vertex.Vertex;
import vertex.Word;

public class GraphPoetApp {
	
	
	/*
	 * 打印菜单
	 * 
	 * @param  无
	 * @return  打印菜单
	 */
	public int Menu() {
		Scanner in = new Scanner(System.in);
		System.out.println("---------------------------------------");
		System.out.println("Management for GraphPoetApp");
		System.out.println("1.Read_file");
		System.out.println("2.print the imformation of the graph");
		System.out.println("3.version of GUI");
		System.out.println("4.operations of Vertex");
		System.out.println("5.operations of Edge");
		System.out.println("6.calculating of degrees");
		System.out.println("7.calculating of graph_degrees");
		System.out.println("8.calculating the distance of two Vertex");
		System.out.println("9.execute the commands");
		System.out.println("10.exit the program!");
		System.out.println("---------------------------------------");
		int itemSelected=in.nextInt();
		return itemSelected;
	}
	
	/*
	 * 修改顶点的label
	 * 
	 * @param  图graph，原顶点s_label，现顶点e_label
	 * @return  是否成功修改
	 */
	public boolean set_vertex_Label(GraphPoet graph,String s_Label,String e_Label) {
		for(Vertex v: graph.vertices()) {
			if(v.getLabel().equals(s_Label)) {
				v.set_Label(e_Label);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 修改边的label
	 * 
	 * @param  图graph，原边s_label，现边e_label
	 * @return  是否成功修改
	 */
	public boolean set_edge_Label(GraphPoet graph,String s_Label,String e_Label) {
		for(Edge e: graph.edges()) {
			if(e.get_label().equals(s_Label)) {
				e.set_label(e_Label);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 修改边的权重
	 * 
	 * @param  图graph，原边s_label，现边权重e_weight）
	 * @return  是否成功修改
	 */
	public boolean set_edge_weight(GraphPoet graph,String s_Label,double e_weigth) {
		for(Edge e: graph.edges()) {
			if(e.get_label().equals(s_Label)) {
				e.set_weight(e_weigth);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 修改边方向
	 * 
	 * @param  图graph，边的标签s_label
	 * @return  是否成功修改
	 */
	public boolean set_edge_point(GraphPoet graph,String s_Label) {
		for(Edge e: graph.edges()) {
			if(e.get_label().equals(s_Label)) {
				e.set_point();
				return true;
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) throws IOException {
		GraphPoetApp app=new GraphPoetApp();
		GraphPoet graph=new GraphPoet();
		int ch;
		while(true) {
			ch=app.Menu();
			switch (ch) {
			case 1:
				System.out.println("请输入单词网络中边的最小权重：");
				Scanner in = new Scanner(System.in);
				double Min_weight=Double.valueOf(in.nextLine());
				System.out.println("请输入文件路径：");
				String path = in.nextLine();
				graph.Read_file(new File(path),graph);
				Iterator<Edge> it =graph.edges().iterator();  //变化一
				while(it.hasNext()) {
					Edge e =it.next();
					if(e.get_weight()<Min_weight) {
						it.remove();
					}else {
						continue;
					}
				}
				break;
			case 2:
				System.out.println(graph.get_GraphName());
				System.out.println(graph.get_GraphType());
				System.out.println(graph.get_VertexType());
				System.out.println(graph.get_EdgeType());
				System.out.println(graph.toString());
				break;
			case 3:
				DirectedSparseGraph<Vertex, Edge> G =new DirectedSparseGraph<>();
				GraphVisualizationHelper.visualize(graph,G);
				break;
			case 4:
				Scanner in1 = new Scanner(System.in);
				System.out.println("请输入你要对点进行的操作：增加节点--add_vertex、删除节点--delate_vertex、修改节点--change_vertex");
				String choice=in1.nextLine();
				if(choice.equals("add_vertex")) {
					System.out.println("请输入所要加入的节点信息：");
					System.out.println("类型：");
					String type=in1.nextLine();
					System.out.println("标签：");
					String Label=in1.nextLine();
					System.out.println("无属性");
					Vertex v =new Word(Label);
					if(graph.addVertex(v)) {
						System.out.println("加入点成功！");
					}else {
						System.out.println("加入失败，该点已经存在！");
					}
				}else if(choice.equals("delate_vertex")) {
					System.out.println("请输入所要删除的节点信息：");
					System.out.println("类型：");
					String type=in1.nextLine();
					System.out.println("标签：");
					String Label=in1.nextLine();
					int flag=0;
					for(Vertex v : graph.vertices()) {
						if(v.getLabel().equals(Label)) {
							if(graph.removeVertex(v)) {
								System.out.println("删除该点成功！");
								flag=1;
								break;
							}else {
								System.out.println("删除失败，该点不存在！");
								break;
							}
						}
					}
					if(flag==0) {
						System.out.println("删除失败，该点不存在！");
					}		
				}else if(choice.equals("change_vertex")) {
					System.out.println("请输入所要修改的节点信息：");
					System.out.println("标签：");
					String Label=in1.nextLine();
					System.out.println("输入要修改该节点的哪项信息：");
					System.out.println("输入修改信息的内容：");
					String re_Label=in1.nextLine();
					app.set_vertex_Label(graph, Label, re_Label);
				}
				break;
			case 5:
				Scanner in2 = new Scanner(System.in);
				System.out.println("请输入你要对边进行的操作：增加边--add_edge、删除边--delate_edge、修改边--change_edge");
				String choice2=in2.nextLine();
				if(choice2.equals("add_edge")) {
					System.out.println("请输入所要加入的边信息：");
					System.out.println("类型：");
					String type=in2.nextLine();
					System.out.println("标签：");
					String Label=in2.nextLine();
					System.out.println("权重：");
					double weight=Double.valueOf(in2.nextLine());
					System.out.println("第一个点（有向图默认为起点）的标签：");
					String s_Label=in2.nextLine();				
					System.out.println("第二个点（有向图默认为终点）的标签：");
					String e_Label=in2.nextLine();
					Vertex source=null;
					Vertex target=null;
					List<Vertex> list=new ArrayList<>();
					for(Vertex v :graph.vertices()) {
						if(v.getLabel().equals(s_Label)) {
							source = v;
						}
						if(v.getLabel().equals(e_Label)) {
							 target = v;
						}			
					}
					if(source.equals(target)) {
						list.add(source);
					}else {
						list.add(target);
						list.add(source);
					}
					Edge e= new WordNeighborhood(Label, weight, source, target, "Yes", list);
					if(graph.addEdge(e)) {
						System.out.println("加入边成功！");
					}else {
						System.out.println("加入失败，起点或者终点不存在！");
					}
				}else if(choice2.equals("delate_edge")) {
					System.out.println("请输入所要删除的边信息：");
					System.out.println("类型：");
					String type=in2.nextLine();
					System.out.println("标签：");
					String Label=in2.nextLine();
					int flag=0;
					for(Edge e  : graph.edges()) {
						if(e.get_label().equals(Label)) {
							if(graph.removeEdge(e)) {
								System.out.println("删除该边成功！");
								flag=1;
								break;
							}else {
								System.out.println("删除失败，该边不存在！");
								break;
							}
						}
					}
					if(flag==0) {
						System.out.println("删除失败，该边不存在！");
					}	
				}else if(choice2.equals("change_edge")) {
					System.out.println("请输入所要修改的边信息：");
					System.out.println("标签：");
					String Label=in2.nextLine();
					System.out.println("输入要修改该节点的哪项信息：标签--Label，权重--weight，方向--point");
					String re_imformation=in2.nextLine();
					System.out.println("输入修改信息的内容：");			
					if(re_imformation.equals("Label")) {
						String re_Label=in2.nextLine();
						app.set_edge_Label(graph, Label, re_Label);
					}else if(re_imformation.equals("weight")){
						double re_weight=in2.nextDouble();
						app.set_edge_weight(graph, Label, re_weight);
					}else if(re_imformation.equals("point")){
						app.set_edge_point(graph, Label);
					}
				}
				break;
			case 6:
				Context_degree context =new Context_degree();
				System.out.println("请输入顶点的标签：");
				Scanner in3 =new Scanner(System.in);
				String Label=in3.nextLine();
				for(Vertex v : graph.vertices()) {
					if(v.getLabel().equals(Label)) {
						System.out.println("该顶点的点度中心性："+context.calculate_degree(new calculate_degreeCentrality(graph, v)));
						System.out.println("该顶点的接近中心性："+context.calculate_degree(new calculate_closenessCentrality(graph, v)));
						System.out.println("该顶点的中介中心性："+context.calculate_degree(new calculate_betweennessCentrality(graph, v)));
						System.out.println("该顶点的偏心度："+GraphMetrics.eccentricity(graph, v));
						System.out.println("该顶点的入度："+GraphMetrics.inDegreeCentrality(graph, v));
						System.out.println("该顶点的出度："+GraphMetrics.outDegreeCentrality(graph, v));
						break;
					}
				}
				break;
			case 7:
				System.out.println("该图的中心度："+GraphMetrics.degreeCentrality(graph));
				System.out.println("该图的半径："+GraphMetrics.radius(graph));
				System.out.println("该图的直径："+GraphMetrics.diameter(graph));	
				break;
			case 8:
				Scanner in8 =new Scanner(System.in);
				System.out.println("请输入起点标签：");
				String s_Label =in8.nextLine();
				System.out.println("请输入终点标签：");
				String e_Label =in8.nextLine();
				Vertex s=null;
				Vertex e=null;
				for(Vertex v : graph.vertices()) {
					if(v.getLabel().equals(s_Label)) {
						s=v;
					}
					if(v.getLabel().equals(e_Label)) {
						e=v;
					}
				}
				System.out.println(s_Label+"到"+e_Label+"的最短路径为："+GraphMetrics.distance(graph, s, e));
				break;
			case 9:
				Scanner in9 =new Scanner(System.in);
				System.out.println("请输入指令：");
				String s_Label1 =in9.nextLine();
				ParseCommandHelper.parseAndExecuteCommand(graph, s_Label1);
				break;
			case 10:
				System.out.println("Bye~~");
				System.exit(0);
			}
			
		}
	}
}
