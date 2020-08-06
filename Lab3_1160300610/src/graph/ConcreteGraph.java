package graph;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edge.CommentTie;
import edge.Edge;
import edge.ForwardTie;
import edge.FriendTie;
import edge.MovieActorRelation;
import edge.MovieDirectorRelation;
import edge.NetworkConnection;
import edge.SameMovieHyperEdge;
import edge.WordNeighborhood;
import vertex.Actor;
import vertex.Computer;
import vertex.Director;
import vertex.Movie;
import vertex.Person;
import vertex.Router;
import vertex.Server;
import vertex.Vertex;
import vertex.WirelessRouter;
import vertex.Word;


public abstract class ConcreteGraph implements Graph<Vertex, Edge>{
	
	// Abstraction function:
	//  如果表示属于vertices，则映射为一系列在vertices中的L类型的点
    //  如果表示属于edges，则映射为一系列在edges中的Edge类型的边
	//	 如果表示属于GraphType，则映射为图的类型
	//	 如果表示属于GraphName，则映射为图的名字
	//	 如果表示属于VertexType，则映射为图中顶点的类型
	//	 如果表示属于EdgeType，则映射为图中边的类型
    
    // Representation invariant（表示 不变性）:
	// edges中的元素中的source和target必须存在在vertices集合中,且edges中的元素中的weight必须大于零
    
    // Safety from rep exposure（表示暴露的安全性）:
	//  所有的区域块都是私有的。
   //   尽管vertices以及edges分别是可变数据类型Set和List，
   //但类中重写的方法并没有以他们作为参数，并没有多个变量指向一个相同的单元。
   //并且将其有关键词final定义，使得其引用不可变.
   //总之不存在表示暴露。
	
	private final Set<Vertex> vertices = new HashSet<>();
    private final Set<Edge> edges = new HashSet<>();
    
    private String GraphType;
	private String GraphName;
	private String VertexType;
	private String EdgeType;
	
	

	protected void checkRep(){
    	for(Edge e:edges) {
    		for(Vertex x: e.sourceVertices()) {
    			assert contain(x, vertices);
    		}for(Vertex x: e.targetVertices()) {
    			assert contain(x, vertices);
    		}
    		assert e.get_weight()>=0||e.get_weight()==-1;
    	}
    }
	
	@Override
	public boolean addVertex(Vertex v) {
		checkRep();
		for(Vertex x:vertices) {
        	if(x.equals(v)) {
        		return false;
        	}else {
        		continue;
        	}
        }
        vertices.add(v);
        checkRep();
		return true;
	}

	@Override
	public Set<Vertex> vertices() {
		checkRep();
		return vertices;
	}

	@Override
	public Map<Vertex, List<Double>> sources(Vertex target) {
		checkRep();
		Map<Vertex, List<Double>> map=new HashMap<>();
		for(Edge e:edges) {
			Set<Vertex> s1=e.targetVertices();
			Set<Vertex> s2=e.sourceVertices();
			List<Double> l=new ArrayList<>();
			Iterator<Vertex> it=s2.iterator();
			if(s1.contains(target)) {
				Vertex x=it.next();
				if(map.containsKey(x)) {
					map.get(x).add(e.get_weight());
				}else {
					l.add(e.get_weight());
					map.put(x, l);
				}
			}
		}
		checkRep();
		return map;
	}

	@Override
	public Map<Vertex, List<Double>> targets(Vertex source) {
		checkRep();
		Map<Vertex, List<Double>> map=new HashMap<>();
		for(Edge e:edges) {
			Set<Vertex> s1=e.sourceVertices();
			Set<Vertex> s2=e.targetVertices();			
			List<Double> l=new ArrayList<>();
			Iterator<Vertex> it=s2.iterator();
			while(it.hasNext()) {
				Vertex x=it.next();
				if(s1.contains(source)&&!source.equals(x)) {					
					if(map.containsKey(x)) {
						map.get(x).add(e.get_weight());
					}else {
						l.add(e.get_weight());
						map.put(x, l);
					}
				}
			}	
		}
		checkRep();
		return map;
	}

	public boolean contain(Vertex x,Set<Vertex> s) {
		for(Vertex v: s) {
			v.equals(x);
			return true;
		}
		return false;
	}
	@Override
	public boolean addEdge(Edge edge) {
		for(Edge e:edges) {
			if(e.get_label().equals(edge.get_label())) {
				checkRep();
				return false;
			}else {
				checkRep();
				continue;
			}	
		}
		Set<Vertex> s1=edge.sourceVertices();
		Set<Vertex> s2 =edge.targetVertices();
		Iterator<Vertex> it1=s1.iterator();
		Iterator<Vertex> it2=s2.iterator();
		while(it1.hasNext()&&it2.hasNext()) {
			if(!contain(it1.next(), vertices())||!contain(it2.next(), vertices())) {
				checkRep();
				return false;
			}
		}
		edges.add(edge);
		checkRep();
		return true;
	}

	@Override
	public boolean removeEdge(Edge edge) {
		Iterator<Edge> it=edges.iterator();
		while(it.hasNext()) {
			Edge e=it.next();
			if(e.equals(edge)) {
				it.remove();
				checkRep();
				return true;
			}else {
				continue;
			}
		}
		checkRep();
		return false;
	}
		
	@Override
	public boolean removeVertex(Vertex v) {
    	Iterator<Edge> it=edges().iterator();
        if(addVertex(v)) {
        	vertices().remove(v);
        	checkRep();
        	return false;
        }else {
        	while(it.hasNext()){
        		Edge e=it.next();
        		if(e.containVertex(v)) {
        			it.remove();
        		}else {
        			continue;
        		}
        	}
        	vertices().remove(v);
        	checkRep();
        	return true;
        }
	}
	
	@Override
	public boolean Read_file(File file,ConcreteGraph graph) throws IOException{
		checkRep();
		BufferedReader reader =null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String s =null;
			while((s=reader.readLine())!=null) {
				if(first_String(s).equals("GraphType")) {
					graph.GraphType=pattern(s);
				}
				if(first_String(s).equals("GraphName")) { 
					graph.GraphName=pattern(s);				
				}else if(first_String(s).equals("VertexType")) {
					graph.VertexType=pattern(s);
				}else if(first_String(s).equals("Vertex")) {
					String v[]=pattern(s).split(" ");
					if(v[1].equals("Word")) {
						if((graph instanceof GraphPoet)) {
							Vertex V=new Word(v[0]);
							V.fillVertexInfo(Args(v));
							graph.addVertex(V);
						}else {
							System.out.println("加入顶点失败，顶点类型不匹配！");
						}		
					}else if(v[1].equals("Actor")) {
						if(graph instanceof MovieGraph) {
							Vertex V=new Actor(v[0]);
							V.fillVertexInfo(Args(v));
							graph.addVertex(V);
						}else {
							System.out.println("加入顶点失败，顶点类型不匹配！");
						}		
					}else if(v[1].equals("Director")) {
						if(graph instanceof MovieGraph) {
							Vertex V=new Director(v[0]);
							V.fillVertexInfo(Args(v));
							graph.addVertex(V);
						}else {
							System.out.println("加入顶点失败，顶点类型不匹配！");
						}		
					}else if(v[1].equals("Movie")) {
						if(graph instanceof MovieGraph) {
							Vertex V=new Movie(v[0]);
							V.fillVertexInfo(Args(v));
							graph.addVertex(V);	
						}else {
							System.out.println("加入顶点失败，顶点类型不匹配！");
						}							
					}else if(v[1].equals("Computer")) {
						if(graph instanceof NetworkTopology) {
							Vertex V=new Computer(v[0]);
							V.fillVertexInfo(Args(v));
							graph.addVertex(V);
						}else {
							System.out.println("加入顶点失败，顶点类型不匹配！");
						}							
					}else if(v[1].equals("WirelessRouter")) {
						if(graph instanceof NetworkTopology) {
							Vertex V=new WirelessRouter(v[0]);
							V.fillVertexInfo(Args(v));
							graph.addVertex(V);
						}else {
							System.out.println("加入顶点失败，顶点类型不匹配！");
						}							
					}else if(v[1].equals("Router")) {
						if(graph instanceof NetworkTopology) {
							Vertex V=new Router(v[0]);
							V.fillVertexInfo(Args(v));
							graph.addVertex(V);
						}else {
							System.out.println("加入顶点失败，顶点类型不匹配！");
						}					
					}else if(v[1].equals("Server")) {
						if(graph instanceof NetworkTopology) {
							Vertex V=new Server(v[0]);
							V.fillVertexInfo(Args(v));
							graph.addVertex(V);
						}else {
							System.out.println("加入顶点失败，顶点类型不匹配！");
						}			
					}else if(v[1].equals("Person")) {
						if(graph instanceof SocialNetwork) {
							Vertex V=new Person(v[0]);
							
							V.fillVertexInfo(Args(v));
							graph.addVertex(V);
						}else {
							System.out.println("加入顶点失败，顶点类型不匹配！");
						}							
					}
				}else if(first_String(s).equals("EdgeType")) {
					graph.EdgeType=pattern(s);
				}else if(first_String(s).equals("Edge")) {
					String v[]=pattern(s).split(" ");
					if(v[1].equals("WordNeighborhood")) {
						if((graph instanceof GraphPoet)) {
							List<Vertex> vertices=new ArrayList<>();
							for(int i=3;i<v.length-1;i++) {
								vertices.add(label_v(v[i]));
							}
							Edge E=new WordNeighborhood(v[0], Double.valueOf(v[2]),label_v(v[3]),label_v(v[4]),v[5],vertices);
							graph.addEdge(E);
						}	
					}else if(v[1].equals("MovieActorRelation")) {
						if((graph instanceof MovieGraph)) {
							List<Vertex> vertices=new ArrayList<>();
							for(int i=3;i<v.length-1;i++) {
								vertices.add(label_v(v[i]));
							}
							Edge E=new MovieActorRelation(v[0], Double.valueOf(v[2]),label_v(v[3]),label_v(v[4]),v[5],vertices);
							graph.addEdge(E);
						}
						
					}else if(v[1].equals("MovieDirectorRelation")) {
						if((graph instanceof MovieGraph)) {
							List<Vertex> vertices=new ArrayList<>();
							for(int i=3;i<v.length-1;i++) {
								vertices.add(label_v(v[i]));
							}
							Edge E=new MovieDirectorRelation(v[0], Double.valueOf(v[2]),label_v(v[3]),label_v(v[4]),v[5],vertices);
							graph.addEdge(E);	
						}
											
					}else if(v[1].equals("NetworkConnection")) {
						if((graph instanceof NetworkTopology)) {
							List<Vertex> vertices=new ArrayList<>();
							for(int i=3;i<v.length-1;i++) {
								vertices.add(label_v(v[i]));
							}
							Edge E=new NetworkConnection(v[0], Double.valueOf(v[2]),label_v(v[3]),label_v(v[4]),v[5],vertices);
							graph.addEdge(E);
						}
						
					}else if(v[1].equals("FriendTie")) {
						if((graph instanceof SocialNetwork)) {
							List<Vertex> vertices=new ArrayList<>();
							for(int i=3;i<v.length-1;i++) {
								vertices.add(label_v(v[i]));
							}
							Edge E=new FriendTie(v[0], Double.valueOf(v[2]),label_v(v[3]),label_v(v[4]),v[5],vertices);
							graph.addEdge(E);
						}
						
					}else if(v[1].equals("ForwardTie")) {
						if((graph instanceof SocialNetwork)) {
							List<Vertex> vertices=new ArrayList<>();
							for(int i=3;i<v.length-1;i++) {
								vertices.add(label_v(v[i]));
							}
							Edge E=new ForwardTie(v[0], Double.valueOf(v[2]),label_v(v[3]),label_v(v[4]),v[5],vertices);
							graph.addEdge(E);
						}
						
					}else if(v[1].equals("CommentTie")) {
						if((graph instanceof SocialNetwork)) {
							List<Vertex> vertices=new ArrayList<>();
							for(int i=3;i<v.length-1;i++) {
								vertices.add(label_v(v[i]));
							}
							Edge E=new CommentTie(v[0], Double.valueOf(v[2]),label_v(v[3]),label_v(v[4]),v[5],vertices);
							graph.addEdge(E);
						}
						
					}
				}else if(first_String(s).equals("HyperEdge")) {
					if((graph instanceof MovieGraph)) {
						String v[]=pattern(s).split(" ");
						List<Vertex> vertices=new ArrayList<>();
						for(int i=2;i<v.length;i++) {
							vertices.add(label_v(v[i]));
						}
						Edge E=new SameMovieHyperEdge(v[0], -1, vertices);
						graph.addEdge(E);
					}
					
				}
				
			}
			reader.close();
			checkRep();
			return true;
		}catch (Exception e) {
			checkRep();
			return false;
		}
	}
	

		
	@Override
	public Set<Edge> edges() {
		checkRep();
		return edges;
	}
	
	
	/*
	 * 将语句中英文双引号中的内容提取出来
	 * 
	 * @param  待提取的字符串
	 * @return  双引号中的内容
	 */
	public String pattern(String s) {
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
	public String first_String(String s) {
		String a[]=s.split(" ");
		return a[0];
	}
	
	public String[] Args(String v[]) {
		String args[]=new String[v.length-2];
		for(int i=0;i<v.length-2;i++) {
			args[i]=v[i+2];
		}
		return args;
	}
	
	/*
	 * 返回顶点为label的顶点
	 * 
	 * @param 顶点的标签label
	 * @return 返回顶点为label的顶点
	 */
	public Vertex label_v(String label) {
		for(Vertex v:vertices) {
			if(v.getLabel().equals(label)) {
				return v;
			}else {
				continue;
			}
		}
		return null;
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
	

	

}
