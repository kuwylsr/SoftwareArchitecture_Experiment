package helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import edge.Edge;
import edge.SameMovieHyperEdge;
import edge.WordNeighborhood;
import factory.GraphPoetFactory;
import factory.LogFactory;
import factory.MovieGraphFactory;
import factory.NetworkTopologyFactory;
import factory.SocialNetworkFactory;
import graph.ConcreteGraph;
import graph.GraphPoet;
import graph.MovieGraph;
import graph.NetworkTopology;
import graph.SocialNetwork;
import vertex.Actor;
import vertex.Computer;
import vertex.Director;
import vertex.Movie;
import vertex.Person;
import vertex.Router;
import vertex.Server;
import vertex.Vertex;
import vertex.Word;

public class ParseCommandHelper {
	
//	vertex --add "NewVertex1" "Word"
	
	public static void parseAndExecuteCommand(ConcreteGraph graph,String command) {	
		
		String name = null;
		
		if(graph instanceof GraphPoet) {
			name = GraphPoetFactory.class.getName();
		}else if(graph instanceof MovieGraph) {
			name = MovieGraphFactory.class.getName();
		}else if(graph instanceof NetworkTopology) {
			name = NetworkTopologyFactory.class.getName();
		}else {
			name = SocialNetworkFactory.class.getName();
		}

		try {
			if(first_String(command).equals("vertex")&&second_String(command).equals("--add")) {			
				String v[]=pattern(command).split(" ");
				LogFactory.close();
				Logger log2 = LogFactory.Get_Logger(1,4,name+"e");//记录图解析操作的日志
				log2.info("使用图语句添加了节点： "+v[0]);
				LogFactory.close();
				if(v.length!=2) {
					throw new CommandException(command,"图的语法解析操作错误！","加点的操作指令不正确！","重新输入语法操作！");
				}
				if(v[1].equals("Word")) {
					if((graph instanceof GraphPoet)) {
						Vertex V=new Word(v[0]);
						graph.addVertex(V);
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
					}
				}else if(v[1].equals("Actor")) {
					if(graph instanceof MovieGraph) {
						Vertex V=new Actor(v[0]);
						graph.addVertex(V);
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
					}	
				}else if(v[1].equals("Director")) {
					if(graph instanceof MovieGraph) {
						Vertex V=new Director(v[0]);
						graph.addVertex(V);
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
					}	
				}else if(v[1].equals("Movie")) {
					if(graph instanceof MovieGraph) {
						Vertex V=new Movie(v[0]);
						graph.addVertex(V);
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
					}
					Vertex V=new Movie(v[0]);
					graph.addVertex(V);					
				}else if(v[1].equals("Computer")) {
					if(graph instanceof NetworkTopology) {
						Vertex V=new Computer(v[0]);
						graph.addVertex(V);
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
					}
					Vertex V=new Computer(v[0]);
					graph.addVertex(V);					
				}else if(v[1].equals("Computer")) {
					if(graph instanceof NetworkTopology) {
						Vertex V=new Computer(v[0]);
						graph.addVertex(V);
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
					}
					Vertex V=new Computer(v[0]);
					graph.addVertex(V);					
				}else if(v[1].equals("Router")) {
					if(graph instanceof NetworkTopology) {
						Vertex V=new Router(v[0]);
						graph.addVertex(V);
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
					}
					Vertex V=new Router(v[0]);
					graph.addVertex(V);
				}else if(v[1].equals("Server")) {
					if(graph instanceof NetworkTopology) {
						Vertex V=new Server(v[0]);
						graph.addVertex(V);
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
					}
				}else if(v[1].equals("Person")) {
					if(graph instanceof SocialNetwork) {
						Vertex V=new Person(v[0]);
						graph.addVertex(V);
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
					}		
				}
			}else if(first_String(command).equals("edge")&&second_String(command).equals("--add")) {			
				String v1[]=command.split(" ");
				String v[]=pattern(command).split(" ");
				LogFactory.close();
				Logger log2 = LogFactory.Get_Logger(1,4,name+"e");//记录图解析操作的日志
				log2.info("使用图语句添加了边： "+v[0]);
				LogFactory.close();
				if(v1.length!=9||v.length!=4) {
					throw new CommandException(command,"图的语法解析操作错误！","加边的操作指令不正确！","重新输入语法操作！");
				}
				if(v[1].equals("WordNeighborhood")) {
					if((graph instanceof GraphPoet)) {
						List<Vertex> vertices=new ArrayList<>();
						for(int i=2;i<v.length;i++) {
							if(label_v(graph,v[i]) instanceof Word) {
								vertices.add(label_v(graph,v[i]));
							}else {
								throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
							}

						}
						Edge E=new WordNeighborhood(v[0], Double.valueOf(v1[5]),label_v(graph,v[3]),label_v(graph,v[4]),"Yes",vertices);
						if(!graph.addEdge(E)) {
							throw new CommandException(command,"图的语法解析操作错误！","该边已存在！","重新输入语法操作！");
						}
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","边类型不匹配！","重新输入语法操作！");
					}				
				}else if(v[1].equals("MovieActorRelation")) {
					if((graph instanceof MovieGraph)) {
						List<Vertex> vertices=new ArrayList<>();
						for(int i=2;i<v.length;i++) {
							if((label_v(graph,v[3]) instanceof Movie&&(label_v(graph,v[4]) instanceof Actor))||(label_v(graph,v[4]) instanceof Movie&&(label_v(graph,v[3]) instanceof Actor))) {
								vertices.add(label_v(graph,v[i]));
							}else {
								throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
							}

						}
						Edge E=new WordNeighborhood(v[0], Double.valueOf(v1[5]),label_v(graph,v[3]),label_v(graph,v[4]),"NO",vertices);
						if(!graph.addEdge(E)) {
							throw new CommandException(command,"图的语法解析操作错误！","该边已存在！","重新输入语法操作！");
						}
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","边类型不匹配！","重新输入语法操作！");
					}
				}else if(v[1].equals("MovieDirectorRelation")) {
					if((graph instanceof MovieGraph)) {
						List<Vertex> vertices=new ArrayList<>();
						for(int i=2;i<v.length;i++) {
							if((label_v(graph,v[3]) instanceof Movie&&(label_v(graph,v[4]) instanceof Director))||(label_v(graph,v[4]) instanceof Movie&&(label_v(graph,v[3]) instanceof Director))) {
								vertices.add(label_v(graph,v[i]));
							}else {
								throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
							}
						}
						Edge E=new WordNeighborhood(v[0], Double.valueOf(v1[5]),label_v(graph,v[3]),label_v(graph,v[4]),"NO",vertices);
						if(!graph.addEdge(E)) {
							throw new CommandException(command,"图的语法解析操作错误！","该边已存在！","重新输入语法操作！");
						}
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","边类型不匹配！","重新输入语法操作！");
					}					
				}else if(v[1].equals("NetworkConnection")) {
					if((graph instanceof NetworkTopology)) {
						List<Vertex> vertices=new ArrayList<>();
						for(int i=2;i<v.length;i++) {
							if(label_v(graph,v[i]) instanceof Computer||label_v(graph,v[i]) instanceof Router||label_v(graph,v[i]) instanceof Server) {
								vertices.add(label_v(graph,v[i]));
							}else {
								throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
							}

						}
						Edge E=new WordNeighborhood(v[0], Double.valueOf(v1[5]),label_v(graph,v[3]),label_v(graph,v[4]),"NO",vertices);
						if(!graph.addEdge(E)) {
							throw new CommandException(command,"图的语法解析操作错误！","该边已存在！","重新输入语法操作！");
						}
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","边类型不匹配！","重新输入语法操作！");
					}
				}else if(v[1].equals("FriendTie")) {
					if((graph instanceof SocialNetwork)) {
						List<Vertex> vertices=new ArrayList<>();
						for(int i=2;i<v.length;i++){
							if(label_v(graph,v[i]) instanceof Person) {
								vertices.add(label_v(graph,v[i]));
							}else {
								throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");

							}
							Edge E=new WordNeighborhood(v[0], Double.valueOf(v1[5]),label_v(graph,v[3]),label_v(graph,v[4]),"YES",vertices);
							if(!graph.addEdge(E)) {
								throw new CommandException(command,"图的语法解析操作错误！","该边已存在！","重新输入语法操作！");
							}
						}
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","边类型不匹配！","重新输入语法操作！");
					}
				}else if(v[1].equals("ForwardTie")) {
					if((graph instanceof SocialNetwork)) {
						List<Vertex> vertices=new ArrayList<>();
						for(int i=2;i<v.length;i++) {
							if(label_v(graph,v[i]) instanceof Person) {
								vertices.add(label_v(graph,v[i]));
							}else {
								throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
							}

						}
						Edge E=new WordNeighborhood(v[0], Double.valueOf(v1[5]),label_v(graph,v[3]),label_v(graph,v[4]),"YES",vertices);
						if(!graph.addEdge(E)) {
							throw new CommandException(command,"图的语法解析操作错误！","该边已存在！","重新输入语法操作！");
						}
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","边类型不匹配！","重新输入语法操作！");
					}
				}else if(v[1].equals("CommentTie")) {
					if((graph instanceof SocialNetwork)) {
						List<Vertex> vertices=new ArrayList<>();
						for(int i=2;i<v.length;i++) {
							if(label_v(graph,v[i]) instanceof Person) {
								vertices.add(label_v(graph,v[i]));
							}else {
								throw new CommandException(command,"图的语法解析操作错误！","顶点类型不匹配！","重新输入语法操作！");
							}
							
						}
						Edge E=new WordNeighborhood(v[0], Double.valueOf(v1[5]),label_v(graph,v[3]),label_v(graph,v[4]),"YES",vertices);
						if(!graph.addEdge(E)) {
							throw new CommandException(command,"图的语法解析操作错误！","该边已存在！","重新输入语法操作！");
						}
					}else {
						throw new CommandException(command,"图的语法解析操作错误！","边类型不匹配！","重新输入语法操作！");
					}
				}
			}else if(first_String(command).equals("HyperEdge")) {
				if((graph instanceof MovieGraph)) {
					String v[]=pattern(command).split(" ");
					LogFactory.close();
					Logger log2 = LogFactory.Get_Logger(1,4,name+"e");//记录图解析操作的日志
					log2.info("使用图语句添加了超边： "+v[0]);
					LogFactory.close();
					if(v.length<=2) {
						throw new CommandException(command,"图的语法解析操作错误！","加边的操作指令不正确！","重新输入语法操作！");
					}
					List<Vertex> vertices=new ArrayList<>();	
					for(int i=2;i<v.length;i++) {
						vertices.add(label_v(graph,v[i]));
					}
					Edge E=new SameMovieHyperEdge(v[0], -1, vertices);
					if(!graph.addEdge(E)) {
						for(Edge e : graph.edges()) {
							if(e.get_label().equals(v[0])) {
								for(int i=2;i<v.length;i++) {
									e.vertices().add(label_v(graph,v[i]));
								}						
							}
						}			
					}
				}else {
					throw new CommandException(command,"图的语法解析操作错误！","边的类型不匹配！","重新输入语法操作！");
				}		
			}else if(first_String(command).equals("vertex")&&second_String(command).equals("--dalate")) {
				String v[]=pattern(command).split(" ");
				LogFactory.close();
				Logger log2 = LogFactory.Get_Logger(1,4,name+"e");//记录图解析操作的日志
				log2.info("使用图语句删除了顶点： "+v[0]);
				LogFactory.close();
				if(v.length!=1) {
					throw new CommandException(command,"图的语法解析操作错误！","删点的操作指令不正确！","重新输入语法操作！");
				}
				String pattern= v[0];
				Pattern r =Pattern.compile(pattern);
				List<Vertex> list =new ArrayList<>();
				Set<Vertex> s=graph.vertices();
				Iterator<Vertex> it =s.iterator();
				while(it.hasNext()) {
					Vertex re =it.next();
					Matcher m=r.matcher(re.getLabel());
					while(m.find()) {
						list.add(re);
					}
				}
				Iterator<Vertex> it2 =list.iterator();
				while(it2.hasNext()) {
					graph.removeVertex(it2.next());
				}
				System.out.println("删除顶点成功！");
			}else if(first_String(command).equals("edge")&&second_String(command).equals("--dalate")) {
				String v[]=pattern(command).split(" ");
				LogFactory.close();
				Logger log2 = LogFactory.Get_Logger(1,4,name+"e");//记录图解析操作的日志
				log2.info("使用图语句删除了边： "+v[0]);
				LogFactory.close();
				if(v.length!=1) {
					throw new CommandException(command,"图的语法解析操作错误！","删边的操作指令不正确！","重新输入语法操作！");
				}
				String pattern= v[0];
				Pattern r =Pattern.compile(pattern);
				List<Edge> list =new ArrayList<>();
				Set<Edge> s=graph.edges();
				Iterator<Edge> it =s.iterator();
				while(it.hasNext()) {
					Edge re =it.next();
					Matcher m=r.matcher(re.get_label());
					while(m.find()) {
						list.add(re);
					}
				}
				Iterator<Edge> it2 =list.iterator();
				while(it2.hasNext()) {
					graph.removeEdge(it2.next());
				}
				System.out.println("删除边成功！");
			}
		}catch(CommandException e) {
			Logger log1 = LogFactory.Get_Logger(1,3,name+"e");//记录图解析异常的日志
			log1.info(e.get_content()+"\t"+e.getMessage()+"\t"+e.get_con_type_exception()+"\t"+e.get_result_handle());
			LogFactory.close();
			System.out.println(e.getMessage());
			System.out.println("请重新输入语法操作：");
			Scanner in = new Scanner(System.in);
			ParseCommandHelper.parseAndExecuteCommand(graph, in.nextLine());
		}finally {
			LogFactory.close();
		}
	}
		

	
	
	/*
	 * 将语句中英文双引号中的内容提取出来
	 * 
	 * @param  待提取的字符串
	 * @return  双引号中的内容
	 */
	public static String pattern(String s) {
		String pattern= "\"(.*?)\"";
		Pattern r =Pattern.compile(pattern);
		Matcher m=r.matcher(s);
		String a=" ";
		while(m.find()) {
			a=a+" "+m.group(1);
		}
		return a.trim();
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
	public static String second_String(String s) {
		String a[]=s.split(" ");
		return a[1];
	}
	
	/*
	 * 返回顶点为label的顶点
	 * 
	 * @param 顶点的标签label
	 * @return 返回顶点为label的顶点
	 */
	public static Vertex label_v(ConcreteGraph graph,String label) {
		for(Vertex v:graph.vertices()) {
			if(v.getLabel().equals(label)) {
				return v;
			}else {
				continue;
			}
		}
		return null;
	}
	
}
