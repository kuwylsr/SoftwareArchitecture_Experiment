package factory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edge.Edge;
import edge.WordNeighborhood;
import graph.GraphPoet;
import vertex.Vertex;
import vertex.Word;

public class GraphPoetFactory extends GraphFactory{




	@Override
	public  GraphPoet createGraph(String filePath) {

		//用close将文件控制流关闭后，只有再生成一个同文件路径名的log才能再次开启
		String name = GraphPoetFactory.class.getName();
		Logger log1 = LogFactory.Get_Logger(1,1,name+"e");//记录异常/错误的日志

		GraphPoet graph = new GraphPoet();	
		Map<String,Integer> v_map = new HashMap<>();
		Map<String,Integer> e_map = new HashMap<>();
		// 使用 Try-with-Resources 在try模块运行完成后，自动关闭文件流
		try (BufferedReader reader =new BufferedReader(new FileReader(filePath))){		
			String s =null;
			while((s=reader.readLine())!=null) {
				if(first_String(s).equals("GraphType")) {
					if(graph.getClass().getName().equals("graph."+pattern(filePath,s))) {// 判断图的类型是否匹配
						graph.set_GraphType(pattern(filePath,s));
					}else {
						throw new FileNotFormat_re_Exception(filePath,s,"文件错误！","图的类型不匹配！","重新读入新的文件！");
					}		
					
					
				}else if(first_String(s).equals("GraphName")) { 
					graph.set_GraphName(pattern(filePath,s));	
					
					
				}else if(first_String(s).equals("VertexType")) {
					if(pattern(filePath,s).equals("Word")) { //判断顶点类型是否匹配
						graph.set_VertexType(pattern(filePath,s));
					}else {
						throw new FileNotFormat_re_Exception(filePath,s,"文件错误！","顶点的类型不匹配！","重新读入新的文件！");
					}				
					
					
				}else if(first_String(s).equals("Vertex")) {
					String v[]=pattern(filePath,s).split(" ");
					try {        //判断点是否已经存在图中
						for(Vertex x :graph.vertices()) {
							if(x.getLabel().equals(v[0])) {
								throw new FileNotFormatException(filePath,s,"文件错误！","图中顶点Label重复！","系统自动处理！");	
							}	
						}
						v_map.put(v[0], 1);
					}catch(FileNotFormatException e) {
						int num=v_map.get(v[0]);//多个节点的Label重复
						v_map.put(v[0], num+1);
						Vertex new_v = new Word(v[0]+num);
						graph.addVertex(new_v);
						System.out.println(e.getMessage());
						System.out.println("错误文件路径："+e.get_file_name());
						System.out.println("错误文件内容："+e.get_content());
						log1.info(filePath+"\t"+e.get_content()+"\t"+e.getMessage()+"\t"+e.get_con_type_exception()+"\t"+"将顶点："+v[0]+"变为顶点："+new_v.getLabel());
						continue;
					}	
					if(v[1].equals("Word")) { //无属性问题
						Vertex V=new Word(v[0]);
						V.fillVertexInfo(Args(v));
						graph.addVertex(V);
					}

					
				}else if(first_String(s).equals("EdgeType")) {
					if(pattern(filePath,s).equals("WordNeighborhood")) { //判断边的类型是否匹配
						graph.set_EdgeType(pattern(filePath,s));
					}else {
						throw new FileNotFormat_re_Exception(filePath,s,"文件错误！","边的类型不匹配！","重新读入新的文件！");			
					}		
					
					
				}else if(first_String(s).equals("Edge")) {
					String v[]=pattern(filePath,s).split(" ");					
					if(!v[5].equals("Yes")) {//判断边的方向格式是否正确
						throw new FileNotFormat_re_Exception(filePath,s,"文件错误！","边的方向叙述错误！","重新读入新的文件！");	
					}
					for(int j=3;j<v.length-1;j++) {
						if(label_v(graph,v[j])==null) { //判断边中的顶点是否被定义
							throw new FileNotFormat_re_Exception(filePath,s,"文件错误！","边中的顶点未定义！","重新读入新的文件！");
						}
					}
					Pattern pattern = Pattern.compile("[0-9]+(.0)?0*");//判断该边的权重是否符合要求
					if(Double.valueOf(v[2])<=0||!pattern.matcher(v[2]).matches()) {
						throw new FileNotFormat_re_Exception(filePath,s,"文件错误！","该边的权重不符合要求！","重新读入新的文件！");
					}
					try {
						for(Edge x : graph.edges()) {
							if(x.get_label().equals(v[0])) {//判断边是否在图中已经存在
								throw new FileNotFormatException(filePath,s,"文件错误！","图中边的Label重复！","系统自动处理！");	
							}								
						}	
						e_map.put(v[0], 1);
					}catch(FileNotFormatException e) {
						int num=e_map.get(v[0]);//多个边的Label重复
						e_map.put(v[0], num+1);
						List<Vertex> vertices=new ArrayList<>();
						for(int i=3;i<v.length-1;i++) {
							vertices.add(label_v(graph,v[i]));
						}
						Edge new_e = new WordNeighborhood(v[0]+num, Double.valueOf(v[2]),label_v(graph,v[3]),label_v(graph,v[4]),v[5],vertices);
						graph.addEdge(new_e);
						System.out.println(e.getMessage());
						System.out.println("错误文件路径："+e.get_file_name());
						System.out.println("错误文件内容："+e.get_content());
						log1.info(filePath+"\t"+e.get_content()+"\t"+e.getMessage()+"\t"+e.get_con_type_exception()+"\t"+"将边："+v[0]+"变为边："+new_e.get_label());
						continue;
					}
					Iterator<Edge> it = graph.edges().iterator();
					try {//判断单重图中是否出现了多重边	
						while(it.hasNext()){ 
							Edge e2=it.next();
							if(!e2.get_label().equals(v[0])&&e2.sourceVertices().contains(label_v(graph,v[3]))&&e2.targetVertices().contains(label_v(graph,v[4]))&&!label_v(graph,v[3]).equals(label_v(graph,v[4]))) {										
								throw new FileNotFormatException(filePath,s,"文件错误！","图中出现多重边！","系统自动处理！");	
							}
						}				
					}catch(FileNotFormatException e) {
						System.out.println(e.getMessage());
						System.out.println("错误文件路径："+e.get_file_name());
						System.out.println("错误文件内容："+e.get_content());
						log1.info(filePath+"\t"+e.get_content()+"\t"+e.getMessage()+"\t"+e.get_con_type_exception()+"\t"+"去除边："+v[0]);
						continue;
					}			
					if(v[1].equals("WordNeighborhood")) {
						List<Vertex> vertices=new ArrayList<>();
						for(int i=3;i<v.length-1;i++) {
							vertices.add(label_v(graph,v[i]));
						}	
						Edge E=new WordNeighborhood(v[0], Double.valueOf(v[2]),label_v(graph,v[3]),label_v(graph,v[4]),v[5],vertices);
						graph.addEdge(E);		
					}
				}else if(first_String(s).equals("HyperEdge")) {
					try {
						throw new FileNotFormatException(filePath,s,"文件错误！","该图不能存在超边！","系统自动处理！");
					}catch (FileNotFormatException e) {						
						System.out.println(e.getMessage());
						System.out.println("错误文件路径："+e.get_file_name());
						System.out.println("错误文件内容："+e.get_content());	
						log1.info(filePath+"\t"+e.get_content()+"\t"+e.getMessage()+"\t"+e.get_con_type_exception()+"\t"+"直接忽略这种非法边！");
					}					
				}
//				else {
//					throw new FileNotFormat_re_Exception(filePath,s,"文件错误a！","边的类型不匹配！","重新读入新的文件！");	
//				}
			}	
			return graph;
		}catch (IOException e) {
			log1.info(filePath+"\t"+"空"+"\t"+"文件错误！"+"\t"+"输入的文件路径错误"+"\t"+"重新读入新的文件！");
			LogFactory.close();
			System.out.println(e.getMessage());
			System.out.println("请重新输入文件名称：");
			Scanner in = new Scanner(System.in);

			String path = in.nextLine();
			String name1 = GraphPoetFactory.class.getName();
			Logger log11 = LogFactory.Get_Logger(1,2,name1+"e");//记录异常/错误的日志
			log11.info("读取文件： "+path+"！");
			LogFactory.close();

			return createGraph(path);
		}catch (FileNotFormat_re_Exception e) {
			log1.info(filePath+"\t"+e.get_content()+"\t"+e.getMessage()+"\t"+e.get_con_type_exception()+"\t"+e.get_result_handle());
			LogFactory.close();
			System.out.println(e.getMessage());
			System.out.println("错误文件路径："+e.get_file_name());
			System.out.println("错误文件内容："+e.get_content());
			System.out.println("请重新输入文件名称：");
			Scanner in = new Scanner(System.in);		

			String path = in.nextLine();
			String name1 = GraphPoetFactory.class.getName();
			Logger log11 = LogFactory.Get_Logger(1,2,name1+"e");//记录异常/错误的日志
			log11.info("读取文件： "+path+"！");
			LogFactory.close();

			return createGraph(path);
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
	public String pattern(String filePath,String s) throws FileNotFormat_re_Exception {
		String pattern_GraphType = "GraphType\\s*=\\s*\"\\w+\""; //图的类型的模式串
		String pattern_GraphName = "GraphName\\s*=\\s*\"\\w+\"";//图的名称的模式串
		String pattern_VertexType = "VertexType\\s*=\\s*(\\s*\"\\w+\"\\s*,?\\s*)+\\s*";//顶点类型的模式串
		String pattern_Vertex ="Vertex\\s*=\\s*<\\s*\"\\w+\"\\s*,\\s*\"\\w+\"\\s*,?\\s*(<(\\s*\"[\\w.-]+\"\\s*,?)*>)?\\s*>\\s*";  //顶点信息的模式串
		String pattern_EdgeType = "EdgeType\\s*=\\s*(\\s*\"\\w+\"\\s*,?\\s*)+\\s*";//边的类型的模式串
		String pattern_Edge = "Edge\\s*=\\s*<\\s*(\\s*\"[\\w.-]+\"\\s*,?\\s*){6}\\s*>";//边的信息的模式串
		String pattern = "\"([\\w.-]+)\""; // 匹配引号当中的内容

		Pattern r1 =Pattern.compile(pattern_GraphType);
		Pattern r2 =Pattern.compile(pattern_GraphName);
		Pattern r3 =Pattern.compile(pattern_VertexType);
		Pattern r4 =Pattern.compile(pattern_Vertex);
		Pattern r5 =Pattern.compile(pattern_EdgeType);
		Pattern r6 =Pattern.compile(pattern_Edge);		
		Pattern r8 =Pattern.compile(pattern);

		Matcher m1=r1.matcher(s);
		Matcher m2=r2.matcher(s);
		Matcher m3=r3.matcher(s);
		Matcher m4=r4.matcher(s);
		Matcher m5=r5.matcher(s);
		Matcher m6=r6.matcher(s);
		Matcher m8=r8.matcher(s);
		String a=" ";
		if(first_String(s).equals("GraphType")&&!m1.matches()) {
			throw new FileNotFormat_re_Exception(filePath,s,"文件错误！","图的类型的信息不完整！","重新读入新的文件！");
		}else if(first_String(s).equals("GraphName")&&!m2.matches()) {
			throw new FileNotFormat_re_Exception(filePath,s,"文件错误！","图的名字的信息不完整！","重新读入新的文件！");
		}else if(first_String(s).equals("VertexType")&&!m3.matches()) {
			throw new FileNotFormat_re_Exception(filePath,s,"文件错误！","点的类型的信息不完整！","重新读入新的文件！");
		}else if(first_String(s).equals("Vertex")&&!m4.matches()) {
			throw new FileNotFormat_re_Exception(filePath,s,"文件错误！","点的信息不完整（非属性问题）！","重新读入新的文件！");
		}else if(first_String(s).equals("EdgeType")&&!m5.matches()) {
			throw new FileNotFormat_re_Exception(filePath,s,"文件错误！","边的类型的信息不完整！","重新读入新的文件！");
		}else if(first_String(s).equals("Edge")&&!m6.matches()) {
			throw new FileNotFormat_re_Exception(filePath,s,"文件错误！","边的信息不完整！","重新读入新的文件！");
		}else {
			while(m8.find()) {			
				a=a+" "+m8.group(1);
			}
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
	public Vertex label_v(GraphPoet graph,String label) {
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
