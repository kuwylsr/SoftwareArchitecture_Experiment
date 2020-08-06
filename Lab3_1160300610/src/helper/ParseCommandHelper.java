package helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import edge.Edge;
import edge.SameMovieHyperEdge;
import edge.WordNeighborhood;
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

	
	public static void parseAndExecuteCommand(ConcreteGraph graph,String command) {
		
		if(first_String(command).equals("vertex")&&second_String(command).equals("--add")) {
			String v[]=pattern(command).split(" ");
			if(v[1].equals("Word")) {
				if((graph instanceof GraphPoet)) {
					Vertex V=new Word(v[0]);
					graph.addVertex(V);
				}else {
					System.out.println("加入顶点失败，顶点类型不匹配！");
				}
			}else if(v[1].equals("Actor")) {
				if(graph instanceof MovieGraph) {
					Vertex V=new Actor(v[0]);
					graph.addVertex(V);
				}else {
					System.out.println("加入顶点失败，顶点类型不匹配！");
				}	
			}else if(v[1].equals("Director")) {
				if(graph instanceof MovieGraph) {
					Vertex V=new Director(v[0]);
					graph.addVertex(V);
				}else {
					System.out.println("加入顶点失败，顶点类型不匹配！");
				}	
			}else if(v[1].equals("Movie")) {
				if(graph instanceof MovieGraph) {
					Vertex V=new Movie(v[0]);
					graph.addVertex(V);
				}else {
					System.out.println("加入顶点失败，顶点类型不匹配！");
				}
				Vertex V=new Movie(v[0]);
				graph.addVertex(V);					
			}else if(v[1].equals("Computer")) {
				if(graph instanceof NetworkTopology) {
					Vertex V=new Computer(v[0]);
					graph.addVertex(V);
				}else {
					System.out.println("加入顶点失败，顶点类型不匹配！");
				}
				Vertex V=new Computer(v[0]);
				graph.addVertex(V);					
			}else if(v[1].equals("Computer")) {
				if(graph instanceof NetworkTopology) {
					Vertex V=new Computer(v[0]);
					graph.addVertex(V);
				}else {
					System.out.println("加入顶点失败，顶点类型不匹配！");
				}
				Vertex V=new Computer(v[0]);
				graph.addVertex(V);					
			}else if(v[1].equals("Router")) {
				if(graph instanceof NetworkTopology) {
					Vertex V=new Router(v[0]);
					graph.addVertex(V);
				}else {
					System.out.println("加入顶点失败，顶点类型不匹配！");
				}
				Vertex V=new Router(v[0]);
				graph.addVertex(V);
			}else if(v[1].equals("Server")) {
				if(graph instanceof NetworkTopology) {
					Vertex V=new Server(v[0]);
					graph.addVertex(V);
				}else {
					System.out.println("加入顶点失败，顶点类型不匹配！");
				}
			}else if(v[1].equals("Person")) {
				if(graph instanceof SocialNetwork) {
					Vertex V=new Person(v[0]);
					graph.addVertex(V);
				}else {
					System.out.println("加入顶点失败，顶点类型不匹配！");
				}		
			}
		}else if(first_String(command).equals("edge")&&second_String(command).equals("--add")) {
			String v1[]=command.split(" ");
			String v[]=pattern(command).split(" ");
			if(v[1].equals("WordNeighborhood")) {
				if((graph instanceof GraphPoet)) {
					List<Vertex> vertices=new ArrayList<>();
					for(int i=2;i<v.length;i++) {
						if(label_v(graph,v[i]) instanceof Word) {
							vertices.add(label_v(graph,v[i]));
						}else {
							System.out.println("加入边失败，顶点类型不匹配！");
						}

					}
					Edge E=new WordNeighborhood(v[0], Double.valueOf(v1[5]),label_v(graph,v[3]),label_v(graph,v[4]),"Yes",vertices);
					if(!graph.addEdge(E)) {
						System.out.println("已存在同类型的边！");
					}
				}else {
					System.out.println("加入边失败，边类型不匹配！");
				}				
			}else if(v[1].equals("MovieActorRelation")) {
				if((graph instanceof MovieGraph)) {
					List<Vertex> vertices=new ArrayList<>();
					for(int i=2;i<v.length;i++) {
						if((label_v(graph,v[3]) instanceof Movie&&(label_v(graph,v[4]) instanceof Actor))||(label_v(graph,v[4]) instanceof Movie&&(label_v(graph,v[3]) instanceof Actor))) {
							vertices.add(label_v(graph,v[i]));
						}else {
							System.out.println("加入边失败，顶点类型不匹配！");
						}

					}
					Edge E=new WordNeighborhood(v[0], Double.valueOf(v1[5]),label_v(graph,v[3]),label_v(graph,v[4]),"NO",vertices);
					if(!graph.addEdge(E)) {
						System.out.println("已存在同类型的边！");
					}
				}else {
					System.out.println("添加边失败，边类型不匹配！");
				}
			}else if(v[1].equals("MovieDirectorRelation")) {
				if((graph instanceof MovieGraph)) {
					List<Vertex> vertices=new ArrayList<>();
					for(int i=2;i<v.length;i++) {
						if((label_v(graph,v[3]) instanceof Movie&&(label_v(graph,v[4]) instanceof Director))||(label_v(graph,v[4]) instanceof Movie&&(label_v(graph,v[3]) instanceof Director))) {
							vertices.add(label_v(graph,v[i]));
						}else {
							System.out.println("加入边失败，顶点类型不匹配！");
						}

					}
					Edge E=new WordNeighborhood(v[0], Double.valueOf(v1[5]),label_v(graph,v[3]),label_v(graph,v[4]),"NO",vertices);
					if(!graph.addEdge(E)) {
						System.out.println("已存在同类型的边！");
					}
				}else {
					System.out.println("添加边失败，边类型不匹配！");
				}					
			}else if(v[1].equals("NetworkConnection")) {
				if((graph instanceof NetworkTopology)) {
					List<Vertex> vertices=new ArrayList<>();
					for(int i=2;i<v.length;i++) {
						if(label_v(graph,v[i]) instanceof Computer||label_v(graph,v[i]) instanceof Router||label_v(graph,v[i]) instanceof Server) {
							vertices.add(label_v(graph,v[i]));
						}else {
							System.out.println("加入边失败，顶点类型不匹配！");
						}

					}
					Edge E=new WordNeighborhood(v[0], Double.valueOf(v1[5]),label_v(graph,v[3]),label_v(graph,v[4]),"NO",vertices);
					if(!graph.addEdge(E)) {
						System.out.println("已存在同类型的边！");
					}
				}else {
					System.out.println("添加边失败，边类型不匹配！");
				}
			}else if(v[1].equals("FriendTie")) {
				if((graph instanceof SocialNetwork)) {
					List<Vertex> vertices=new ArrayList<>();
					for(int i=2;i<v.length;i++){
						if(label_v(graph,v[i]) instanceof Person) {
							vertices.add(label_v(graph,v[i]));
						}else {
							System.out.println("加入边失败，顶点类型不匹配！");
						}

					}
					Edge E=new WordNeighborhood(v[0], Double.valueOf(v1[5]),label_v(graph,v[3]),label_v(graph,v[4]),"YES",vertices);
					if(!graph.addEdge(E)) {
						System.out.println("已存在同类型的边！");
					}
				}else {
					System.out.println("添加边失败，边类型不匹配！");
				}
			}else if(v[1].equals("ForwardTie")) {
				if((graph instanceof SocialNetwork)) {
					List<Vertex> vertices=new ArrayList<>();
					for(int i=2;i<v.length;i++) {
						if(label_v(graph,v[i]) instanceof Person) {
							vertices.add(label_v(graph,v[i]));
						}else {
							System.out.println("加入边失败，顶点类型不匹配！");
						}

					}
					Edge E=new WordNeighborhood(v[0], Double.valueOf(v1[5]),label_v(graph,v[3]),label_v(graph,v[4]),"YES",vertices);
					if(!graph.addEdge(E)) {
						System.out.println("已存在同类型的边！");
					}
				}else {
					System.out.println("添加边失败，边类型不匹配！");
				}
			}else if(v[1].equals("CommentTie")) {
				if((graph instanceof SocialNetwork)) {
					List<Vertex> vertices=new ArrayList<>();
					for(int i=2;i<v.length;i++) {
						if(label_v(graph,v[i]) instanceof Person) {
							vertices.add(label_v(graph,v[i]));
						}else {
							System.out.println("加入边失败，顶点类型不匹配！");
						}

					}
					Edge E=new WordNeighborhood(v[0], Double.valueOf(v1[5]),label_v(graph,v[3]),label_v(graph,v[4]),"YES",vertices);
					if(!graph.addEdge(E)) {
						System.out.println("已存在同类型的边！");
					}
				}else {
					System.out.println("添加边失败，边类型不匹配！");
				}
			}
		}else if(first_String(command).equals("HyperEdge")) {
			if((graph instanceof MovieGraph)) {
				String v[]=pattern(command).split(" ");
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
				System.out.println("添加边失败，边类型不匹配！");
			}		
		}else if(first_String(command).equals("vertex")&&second_String(command).equals("--dalate")) {
			String v[]=pattern(command).split(" ");
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
