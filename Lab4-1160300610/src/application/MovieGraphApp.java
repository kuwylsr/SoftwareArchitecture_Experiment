package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import edge.Edge;
import edge.MovieActorRelation;
import edge.MovieDirectorRelation;
import edge.Movie_movieRelation;
import edge.SameMovieHyperEdge;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import factory.GraphPoetFactory;
import factory.LogFactory;
import factory.MovieGraphFactory;
import graph.MovieGraph;
import helper.Context_degree;
import helper.GraphMetrics;
import helper.GraphVisualizationHelper;
import helper.ParseCommandHelper;
import helper.calculate_betweennessCentrality;
import helper.calculate_closenessCentrality;
import helper.calculate_degreeCentrality;
import vertex.Actor;
import vertex.Director;
import vertex.Movie;
import vertex.Vertex;

public class MovieGraphApp {
	
	/*
	 * 打印菜单
	 * 
	 * @param  无
	 * @return  打印菜单
	 */
	public int Menu() {
		Scanner in = new Scanner(System.in);
		System.out.println("-------------------菜单-------------------");
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
		System.out.println("10.inquire the log");
		System.out.println("11.exit the program!");
		System.out.println("------------------------------------------");
		int itemSelected=in.nextInt();
		return itemSelected;
	}
	
	/*
	 * 修改顶点的label
	 * 
	 * @param  图graph，原顶点s_label，现顶点e_label
	 * @return  是否成功修改
	 */
	public boolean set_vertex_Label(MovieGraph graph,String s_Label,String e_Label) {
		for(Vertex v: graph.vertices()) {
			if(v.getLabel().equals(s_Label)) {
				v.set_Label(e_Label);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 修改顶点的属性-年龄
	 * 
	 * @param  图graph，原顶点s_label，现顶点属性年龄age
	 * @return  是否成功修改
	 */
	public boolean set_vertex_age(MovieGraph graph,String s_Label,int age) {
		for(Vertex v: graph.vertices()) {
			if(v.getLabel().equals(s_Label)) {
				String args[]=v.get_VertexInfo();
				args[0]=String.valueOf(age);
				v.fillVertexInfo(args);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 修改顶点属性-性别
	 * 
	 * @param  图graph，原顶点s_label，现顶点属性-性别gender
	 * @return  是否成功修改
	 */
	public boolean set_vertex_gender(MovieGraph graph,String s_Label,String gender) {
		for(Vertex v: graph.vertices()) {
			if(v.getLabel().equals(s_Label)) {
				String args[]=v.get_VertexInfo();
				args[1]=String.valueOf(gender);
				v.fillVertexInfo(args);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 修改顶点的属性-发行年份
	 * 
	 * @param  图graph，原顶点s_label，现顶点属性-发行年份year_Release
	 * @return  是否成功修改
	 */
	public boolean set_vertex_year_Release(MovieGraph graph,String s_Label,String year_Release) {
		for(Vertex v: graph.vertices()) {
			if(v.getLabel().equals(s_Label)) {
				String args[]=v.get_VertexInfo();
				args[0]=String.valueOf(year_Release);
				v.fillVertexInfo(args);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 修改顶点的属性-发行国家
	 * 
	 * @param  图graph，原顶点s_label，现顶点属性-发行国家country_Photographing
	 * @return  是否成功修改
	 */
	public boolean set_vertex_country_Photographing(MovieGraph graph,String s_Label,String country_Photographing) {
		for(Vertex v: graph.vertices()) {
			if(v.getLabel().equals(s_Label)) {
				String args[]=v.get_VertexInfo();
				args[1]=String.valueOf(country_Photographing);
				v.fillVertexInfo(args);
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 修改顶点的属性-评分
	 * 
	 * @param  图graph，原顶点s_label，现顶点属性-评分IMDb
	 * @return  是否成功修改
	 */
	public boolean set_vertex_IMDb(MovieGraph graph,String s_Label,String IMDb) {
		for(Vertex v: graph.vertices()) {
			if(v.getLabel().equals(s_Label)) {
				String args[]=v.get_VertexInfo();
				args[2]=String.valueOf(IMDb);
				v.fillVertexInfo(args);
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
	public boolean set_edge_Label(MovieGraph graph,String s_Label,String e_Label) {
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
	public boolean set_edge_weight(MovieGraph graph,String s_Label,double e_weight) {
		double w_a=0;
		for(Edge e: graph.edges()) {
			if(e.get_label().equals(s_Label)) {
				w_a=e.get_weight();
				e.set_weight(e_weight);
				return true;
			}
		}
		for(Edge e: graph.edges()) {
			if(!e.get_label().equals(s_Label)) {
				double w_c =e.get_weight();
				e.set_weight((w_c*(1-e_weight))/(1-w_a));
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
	public boolean set_edge_point(MovieGraph graph,String s_Label) {
		for(Edge e: graph.edges()) {
			if(e.get_label().equals(s_Label)) {
				e.set_point();
				return true;
			}
		}
		return false;
	}
	/*
	 *  获取字符串的第一个按空格分割的第一个字符串
	 * 
	 * @param 待处理的字符串
	 * @return  返回按空格分割的第一个字符串
	 */
	public static String first_String(String s) {
		String a[]=s.split(" ");
		return a[0];
	}
	/*
	 *  获取字符串的第二个按空格分割的第一个字符串
	 * 
	 * @param 待处理的字符串
	 * @return  返回按空格分割的第二个字符串
	 */
	public static String secondandthird_String(String s) {
		String a[]=s.split(" ");
		return a[1]+a[2];
	}
	
	/*
	 *  获取字符串的第二个按空格分割的第一个字符串
	 * 
	 * @param 待处理的字符串
	 * @return  返回按空格分割的第二个字符串
	 */
	public static String firstandsecond_String(String s) {
		String a[]=s.split(" ");
		return a[0]+a[1];
	}
	
	public static void main(String[] args) throws IOException {
		
//		String name = GraphPoetFactory.class.getName();
//		Logger log2 = LogFactory.Get_Logger(2,2,name+"o");//记录操作的日志
		
		MovieGraphApp app=new MovieGraphApp();
		MovieGraph graph=new MovieGraph();
		int ch;
		while(true) {
			String name1 = MovieGraphFactory.class.getName();
			Logger log1 = LogFactory.Get_Logger(1,2,name1+"e");//记录异常/错误的日志
			ch=app.Menu();
			switch (ch) {
			case 1:
				Scanner in = new Scanner(System.in);
				System.out.println("请输入文件路径：");
				String path = in.nextLine();
				log1.info("读取文件： "+path+"！");
				LogFactory.close();
//				log2.info("读取文件："+path+"！");
				graph = new MovieGraphFactory().createGraph(path);
				for(Vertex x:graph.vertices()) { //变化四
					if(x instanceof Director||x instanceof Actor) {
						for(Edge e: graph.edges()) {
							if(e.sourceVertices().contains(x)) {
								Vertex arr[]=e.targetVertices().toArray(new Vertex[0]);
								for(int i=0;i<arr.length-1;i++) {
									List<Vertex> list =new ArrayList<>();
									list.add(arr[i]);
									list.add(arr[i+1]);
									Movie_movieRelation e1 = new Movie_movieRelation(e.get_label(), e.get_weight(), arr[i], arr[i+1], "NO", list); 
									graph.addEdge(e1);
								}
							}
						}
					}
				}
				break;
			case 2:
				log1.info("打印图的信息！");
				LogFactory.close();
//				log2.info("打印图的信息！");
				System.out.println(graph.get_GraphName());
				System.out.println(graph.get_GraphType());
				System.out.println(graph.get_VertexType());
				System.out.println(graph.get_EdgeType());
				System.out.println(graph.toString());
				break;
			case 3:
				log1.info("可视化图结构！");
				LogFactory.close();
//				log2.info("可视化图结构！");
				UndirectedSparseMultigraph<Vertex, Edge> G =new UndirectedSparseMultigraph<>();
				GraphVisualizationHelper.visualize(graph,G);
				break;
			case 4:
				Scanner in1 = new Scanner(System.in);
				System.out.println("-----请输入你要对点进行的操作-----"+"\n"
						+"增加节点--add_vertex"+"\n"
						+"删除节点--delate_vertex"+"\n"
						+"修改节点--change_vertex"+"\n");				String choice=in1.nextLine();
				if(choice.equals("add_vertex")) {
					System.out.println("请输入所要加入的节点信息：");
					System.out.println("类型：");
					String type=in1.nextLine();
					System.out.println("标签：");
					String Label=in1.nextLine();					
					Vertex v=null;
					if(type.equals("Movie")) {
						System.out.println("输入属性：上映年份（四位正整数，范围为[1900, 2018]）（空格）拍摄国家（字符串）（空格）IMDb 评分（0-10 范围内的数值，最多 2 位小数）");
						String Args[] =in1.nextLine().split(" "); 
						v =new Movie(Label);
						v.fillVertexInfo(Args);
					}else if(type.equals("Actor")) {
						System.out.println("输入属性：年龄（正整数）（空格）性别（M/F）");
						String Args[] =in1.nextLine().split(" "); 
						v =new Actor(Label);
						v.fillVertexInfo(Args);
					}else if(type.equals("Director")) {
						System.out.println("输入属性：年龄（正整数）（空格）性别（M/F）");
						String Args[] =in1.nextLine().split(" "); 
						v =new Director(Label);
						v.fillVertexInfo(Args);
					}
					if(graph.addVertex(v)) {
						log1.info("添加了节点： "+v.getLabel());
						LogFactory.close();
//						log2.info("添加了节点："+v.getLabel());
						System.out.println("加入点成功！");
					}else {
						System.out.println("加入失败，该点已经存在！");
					}
				}else if(choice.equals("delate_vertex")) {
					System.out.println("请输入所要删除的节点信息：");
					System.out.println("标签：");
					String Label=in1.nextLine();
					int flag=0;
					for(Vertex v : graph.vertices()) {
						if(v.getLabel().equals(Label)) {
							if(graph.removeVertex(v)) {
								log1.info("删除了节点： "+v.getLabel());
								LogFactory.close();
//								log2.info("删除了节点："+v.getLabel());
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
					System.out.println("类型：");
					String type=in1.nextLine();
					System.out.println("标签：");
					String Label=in1.nextLine();
					if(type.equals("Movie")) {
						System.out.println("输入要修改该节点的哪项信息：标签--Label，上映年份--year_Release，拍摄国家--country_Photographing，IMDb 评分--IMDb");
						String imformation =in1.nextLine();
						System.out.println("输入修改信息的内容：");
						String re_Label=in1.nextLine();
						if(imformation.equals("year_Release")) {
							log1.info("修改了点： "+Label+"的发行年。"+"由："+Label+"变为："+re_Label);
							LogFactory.close();
//							log2.info("修改了点："+Label+"的发行年。"+"由："+Label+"变为："+re_Label);
							app.set_vertex_year_Release(graph, Label, re_Label);
						}else if(imformation.equals("country_Photographing")) {
//							log2.info("修改了点："+Label+"的发行城市。"+"由："+Label+"变为："+re_Label);
							log1.info("修改了点： "+Label+"的发行城市。"+"由："+Label+"变为："+re_Label);
							LogFactory.close();
							app.set_vertex_country_Photographing(graph, Label,re_Label);
						}else if(imformation.equals("IMDb")) {
//							log2.info("修改了点："+Label+"的IMDb。"+"由："+Label+"变为："+re_Label);
							log1.info("修改了点： "+Label+"的IMDb。"+"由："+Label+"变为："+re_Label);
							LogFactory.close();
							app.set_vertex_IMDb(graph, Label, re_Label);
						}else if(imformation.equals("Label")){
//							log2.info("修改了点："+Label+"的标签。"+"由："+Label+"变为："+re_Label);
							log1.info("修改了点： "+Label+"的标签。"+"由："+Label+"变为："+re_Label);
							LogFactory.close();
							app.set_vertex_Label(graph, Label, re_Label);
						}
					}else {
						System.out.println("输入要修改该节点的哪项信息：标签--Label，年龄--age，性别--gender");
						String imformation =in1.nextLine();
						System.out.println("输入修改信息的内容：");
						String re_Label=in1.nextLine();
						if(imformation.equals("Label")) {
//							log2.info("修改了点："+Label+"的标签。"+"由："+Label+"变为："+re_Label);
							log1.info("修改了点： "+Label+"的标签。"+"由："+Label+"变为："+re_Label);
							LogFactory.close();
							app.set_vertex_Label(graph, Label, re_Label);
						}else if(imformation.equals("age")) {
//							log2.info("修改了点："+Label+"的年龄。"+"由："+Label+"变为："+re_Label);
							log1.info("修改了点： "+Label+"的年龄。"+"由："+Label+"变为："+re_Label);
							LogFactory.close();
							app.set_vertex_age(graph, Label, Integer.valueOf(re_Label));
						}else if(imformation.equals("gender")) {
//							log2.info("修改了点："+Label+"的性别。"+"由："+Label+"变为："+re_Label);
							log1.info("修改了点： "+Label+"的性别。"+"由："+Label+"变为："+re_Label);
							LogFactory.close();
							app.set_vertex_gender(graph, Label, re_Label);
						}
					}
					
				}
				break;
			case 5:
				Scanner in2 = new Scanner(System.in);
				System.out.println("-----请输入你要对边进行的操作-----"+"\n"
						+"增加边：add_edge"+"\n"
						+"删除边：delate_edge"+"\n"
						+"修改边：change_edge"+"\n");
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
					Edge e =null;
					if(type.equals("MovieActorRelation")) {
						 e= new MovieActorRelation(Label, weight, source, target, "NO", list);
					}else if(type.equals("SameMovieHyperEdge")) {
						e =new SameMovieHyperEdge(Label, weight, list);
					}else if(type.equals("MovieDirectorRelation")) {
						e = new MovieDirectorRelation(Label, weight, source, target, "NO", list);
					}
					if(graph.addEdge(e)) {
						log1.info("添加了边： "+e.get_label());
						LogFactory.close();
//						log2.info("添加了边："+e.get_label());
						System.out.println("加入边成功！");
					}else {
						System.out.println("加入失败，起点或者终点不存在！");
					}
				}else if(choice2.equals("delate_edge")) {
					System.out.println("请输入所要删除的边信息：");
					System.out.println("标签：");
					String Label=in2.nextLine();
					int flag=0;
					for(Edge e  : graph.edges()) {
						if(e.get_label().equals(Label)) {
							if(graph.removeEdge(e)) {
								log1.info("删除了边： "+e.get_label());
								LogFactory.close();
//								log2.info("删除了边："+e.get_label());
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
					System.out.println("-----请输入你要改变的信息-----"+"\n"
							+"标签：Label"+"\n"
							+"权重：weight"+"\n"
							+"方向：point"+"\n");
					String re_imformation=in2.nextLine();						
					if(re_imformation.equals("label")) {
						System.out.println("输入修改信息的内容：");	
						String re_Label=in2.nextLine();
						log1.info("修改了边： "+Label+"的标签。"+"由："+Label+"变为："+re_Label);
						LogFactory.close();
//						log2.info("修改了边："+Label+"的标签。"+"由："+Label+"变为："+re_Label);
						app.set_edge_Label(graph, Label, re_Label);
					}else if(re_imformation.equals("weight")){
						System.out.println("输入修改信息的内容：");	
						double re_weight=in2.nextDouble();
						log1.info("修改了边： "+Label+"的权重。"+"由："+Label+"变为："+re_weight);
						LogFactory.close();
//						log2.info("修改了边："+Label+"的权重。"+"由："+Label+"变为："+re_weight);
						app.set_edge_weight(graph, Label, re_weight);
					}
				}else if(choice2.equals("add_HyperEdge")) {
					Vertex x=null;
					int flag=0;
					System.out.println("请输入超边的Label：");
					String e_Label=in2.nextLine();
					System.out.println("请输入顶点的Label：");
					String v_Label=in2.nextLine();
					for(Vertex v: graph.vertices()) {
						if(v.getLabel().equals(v_Label)) {
							x=v;
							flag=1;
						}else {
							continue;
						}
					}
					if(flag==0) {
						x=new Actor(v_Label);
					}
					List<Vertex> list = new ArrayList<>();
					list.add(x);
					for(Edge e : graph.edges()) {
						if(e.get_label().equals(e_Label)) {
							e.addVertices(list);
//							log2.info("向超边："+e.get_label()+"加入了顶点集："+list);
							log1.info("向超边： "+e.get_label()+"加入了顶点集："+list);
							LogFactory.close();
						}
					}
				}else if(choice2.equals("delate_HyperEdge")) {
					System.out.println("请输入超边的Label：");
					String e_Label=in2.nextLine();
					System.out.println("请输入顶点的Label：");
					String v_Label=in2.nextLine();
					Iterator<Edge> it =graph.edges().iterator();
					Vertex x=null;
					for(Vertex v: graph.vertices()) {
						if(v.getLabel().equals(v_Label)) {
							x=v;
						}else {
							continue;
						}
					}
					while(it.hasNext()) {
						Edge e=it.next();
						if(e.containVertex(x)&&e.get_label().equals(e_Label)) {
							e.vertices().remove(x);
//							log2.info("移除了超边："+e.get_label()+"中的顶点："+x.getLabel());
							log1.info("移除了超边： "+e.get_label()+"中的顶点："+x.getLabel());
							LogFactory.close();
						}
						if(e.vertices().size()==0) {
							it.remove();
//							log2.info("移除了超边："+e.get_label());
							log1.info("移除了超边： "+e.get_label());
							LogFactory.close();
						}
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
						log1.info("查看了顶点： "+v.getLabel()+"的度。");
						LogFactory.close();
//						log2.info("查看了顶点："+v.getLabel()+"的度。");
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
				log1.info("查看了图： "+graph.get_GraphName()+"的度。");
				LogFactory.close();
//				log2.info("查看了图："+graph.get_GraphName()+"的度。");
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
//				log2.info("查看了点："+s_Label+"到点："+e_Label+"的最短路径。");
				log1.info("查看了点： "+s_Label+"到点："+e_Label+"的最短路径。");
				LogFactory.close();
				System.out.println(s_Label+"到"+e_Label+"的最短路径为："+GraphMetrics.distance(graph, s, e));
				break;
			case 9:
				System.out.println("-----请输入基于语法的图操作指令-----"+"\n"
						+"加入顶点：vertex --add label type"+"\n"
						+"删除顶点：vertex --delete regex"+"\n"
						+"加入边：edge --add label type [weighted=Y|N] [weight] [directed=Y|N] v1, v2"+"\n"
						+"删除边：edge --delete regex"+"\n"
						+"添加超边：hyperedge --add label type vertex1, ...,vertexn"+"\n");
				Scanner in9 =new Scanner(System.in);
				System.out.println("请输入指令：");
				String s_Label1 =in9.nextLine();
				ParseCommandHelper.parseAndExecuteCommand(graph, s_Label1);
				LogFactory.close();
				break;
			case 10:
				Scanner in4 = new Scanner(System.in);
				System.out.println("-----请输入你的过滤日志的条件-----"+"\n"
												+"时间段：time_between"+"\n"
												+"异常/错误类型：type_wrong"+"\n"
												+"异常/错误发生的类：class_wrong"+"\n"
												+"异常/错误发生的方法：method_wrong"+"\n"
												+"对图的操作：operator"+"\n");
				System.out.println("请输入你的过滤条件：");
				String choice1=in4.nextLine();				
				if(choice1.equals("time_between")) {
					System.out.println("请输入起始时间：(格式：xxxx-xx-xx xx:xx:xx)");
					String beginTime =in4.nextLine();
					System.out.println("请输入终止时间：(格式：xxxx-xx-xx xx:xx:xx)");
					String endTime =in4.nextLine();
					try (BufferedReader reader =new BufferedReader(new FileReader("src/graph/factory.GraphPoetFactorye_e.txt"))){
						String ss =null;
						while((ss=reader.readLine())!=null) {
							if(first_String(ss).equals("操作的时间：")) {
								if(Long.valueOf(beginTime.replaceAll("[-\\s:]",""))<Long.valueOf(secondandthird_String(ss).replaceAll("[-\\s:]",""))&&
										Long.valueOf(endTime.replaceAll("[-\\s:]",""))>Long.valueOf(secondandthird_String(ss).replaceAll("[-\\s:]",""))) {
									System.out.println(ss);
									System.out.println(reader.readLine());
									System.out.println(reader.readLine());
								}								
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}else if(choice1.equals("type_wrong")) {
					System.out.println("-----请输入错误/异常的类型-----"+"\n"
							+"文件异常：file_exception"+"\n"
							+"命令异常：command_exception"+"\n");
					System.out.println("请输入你的错误/异常的类型：");
					String error =in4.nextLine();

					try (BufferedReader reader =new BufferedReader(new FileReader("src/graph/factory.GraphPoetFactorye_e.txt"))){
						String ss =null;
						String file_e[]= new String[9];
						String command_e[]=new String[8];
						while((ss=reader.readLine())!=null) {
							if(first_String(ss).equals("发生文件错误的时间：")) {
								file_e[0]=ss;
								for(int i=1;i<9;i++) {
									file_e[i]=reader.readLine();
								}
								if(file_e[5].equals("发生错误的类型： 文件错误！")&&error.equals("file_exception")) {
									for(int i=0;i<9;i++) {
										System.out.println(file_e[i]);
									}
								}
							}else if(first_String(ss).equals("发生图操作指令错误的的时间：")) {
								command_e[0]=ss;
								for(int i=1;i<8;i++) {
									command_e[i]=reader.readLine();
								}
								if(file_e[4].equals("发生错误的类型： 图的语法解析操作错误！")&&error.equals("command_exception")) {
									for(int i=0;i<8;i++) {
										System.out.println(command_e[i]);
									}
								}
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}else if(choice1.equals("class_wrong")) {
					
					System.out.println("-----请输入错误的类名-----"+"\n"
							+"命令异常：helper.ParseCommandHelper"+"\n"
							+"文件异常：factory.GraphPoetFactory"+"\n");
					System.out.println("请输入错误的类名：");
					String error =in4.nextLine();
					
					try (BufferedReader reader =new BufferedReader(new FileReader("src/graph/factory.GraphPoetFactorye_e.txt"))){
						String ss =null;
						String file_e[]= new String[9];
						String command_e[]=new String[8];
						while((ss=reader.readLine())!=null) {
							if(first_String(ss).equals("发生文件错误的时间：")) {
								file_e[0]=ss;
								for(int i=1;i<9;i++) {
									file_e[i]=reader.readLine();
								}
								if(file_e[3].equals("发生错误的类名： "+error)) {
									for(int i=0;i<9;i++) {
										System.out.println(file_e[i]);
									}
								}
							}else if(first_String(ss).equals("发生图操作指令错误的的时间：")) {
								command_e[0]=ss;
								for(int i=1;i<8;i++) {
									command_e[i]=reader.readLine();
								}
								if(file_e[2].equals("发生错误的类名： "+error)) {
									for(int i=0;i<8;i++) {
										System.out.println(command_e[i]);
									}
								}
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}else if(choice1.equals("method_wrong")) {				
					System.out.println("-----请输入错误的方法名-----"+"\n"
							+"命令异常：createGraph"+"\n"
							+"文件异常：parseAndExecuteCommand"+"\n");
					System.out.println("请输入错误的方法名：");
					String error =in4.nextLine();
					
					try (BufferedReader reader =new BufferedReader(new FileReader("src/graph/factory.GraphPoetFactorye_e.txt"))){
						String ss =null;
						String file_e[]= new String[9];
						String command_e[]=new String[8];
						while((ss=reader.readLine())!=null) {
							if(first_String(ss).equals("发生文件错误的时间：")) {
								file_e[0]=ss;
								for(int i=1;i<9;i++) {
									file_e[i]=reader.readLine();
								}
								if(file_e[4].equals("发生错误的方法名： "+error)) {
									for(int i=0;i<9;i++) {
										System.out.println(file_e[i]);
									}
								}
							}else if(first_String(ss).equals("发生图操作指令错误的的时间：")) {
								command_e[0]=ss;
								for(int i=1;i<8;i++) {
									command_e[i]=reader.readLine();
								}
								if(file_e[3].equals("发生错误的方法名： "+error)) {
									for(int i=0;i<8;i++) {
										System.out.println(command_e[i]);
									}
								}
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}else if(choice1.equals("operator")) {					
					System.out.println("-----请输入操作的名称-----"+"\n"
							+"读取文件："+"\n"
							+"打印图的信息！"+"\n"
							+"可视化图结构！"+"\n"
							+"添加了节点："+"\n"
							+"删除了节点："+"\n"
							+"修改了点："+"\n"
							+"添加了边："+"\n"
							+"删除了边："+"\n"
							+"修改了边："+"\n"
							+"向超边："+"\n"
							+"移除了超边："+"\n"
							+"查看了顶点："+"\n"
							+"查看了图："+"\n"
							+"使用图语句添加了节点："+"\n"
							+"使用图语句添加了边："+"\n"
							+"使用图语句添加了超边："+"\n"
							+"使用图语句删除了顶点："+"\n"
							+"使用图语句删除了边："+"\n");
					System.out.println("请输入操作名：");
					String error =in4.nextLine();
					
					try (BufferedReader reader =new BufferedReader(new FileReader("src/graph/factory.GraphPoetFactorye_e.txt"))){
						String ss =null;
						String operator[]= new String[2];
						while((ss=reader.readLine())!=null) {
							if(first_String(ss).equals("操作的时间：")) {
								operator[0]=ss;
								for(int i=1;i<3;i++) {
									operator[i]=reader.readLine();
								}
								System.err.println(operator[1]);
								System.err.println(firstandsecond_String(operator[1]));
								if(firstandsecond_String(operator[1]).equals("操作的内容："+error)) {
									for(int i=0;i<3;i++) {
										System.out.println(operator[i]);
									}
								}
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				break;
			case 11:
//				log2.info("结束了程序！");
				log1.info("结束了程序！");
				LogFactory.close();
				System.out.println("Bye~~");
				System.exit(0);
			default :
				System.out.println("服务序号输入错误，请重新输入！");
			}
			
		}
	}
}
