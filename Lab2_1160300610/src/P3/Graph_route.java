package P3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import P1.graph.Graph;




public class  Graph_route implements Graph<Vertex>,RoutePlannerBuilder{

	private final List<Vertex> vertices = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();
    private final Map<String,List<Vertex>> bus_itinerary= new HashMap<>();
    private final List<TripSegment> Trip=new ArrayList<>();
    
	
	public static void main(String[] args) {
		Graph_route g=new Graph_route();		
		RoutePlanner G=g.build("data.txt", 60);
		
		for(Vertex v:g.vertices) {
			System.out.println(v.name);
		}
		System.out.println("-----------------------------------");
		for(Edge e:g.edges) {
			System.out.println(e.source.name+"->"+e.target.name+"/"+e.weight);
		}
		System.out.println("-----------------------------------");
		for(Map.Entry<String,List<Vertex>> entry:g.bus_itinerary.entrySet()) {
			System.out.println(entry.getKey());
			for(Vertex v:entry.getValue()) {
				System.out.println(v.name);
			}
		}
		
	}

	@Override
	public boolean add(Vertex vertex) {
//		bus_vertices.add(vertex);
		for(Vertex x:vertices) {
        	if(x.equals(x, vertex)) {
        		return false;
        	}else {
        		continue;
        	}
        }
        vertices.add(vertex);
        return true;
	}

	@Override
	public int set(Vertex source, Vertex target, int weight) {
		Edge e=new Edge(source,target,weight);
		add(source);
		add(target);
		edges.add(e);
		return 0;        	
	}

	@Override
	public boolean remove(Vertex vertex) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public Set<Vertex> vertices() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Map<Vertex, Integer> sources(Vertex target) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public Map<Vertex, Integer> targets(Vertex source) {
		// TODO 自动生成的方法存根
		return null;
	}


	@Override
	public RoutePlanner build(String filename, int maxWaitLimit) {
		// TODO 自动生成的方法存根
		File file=new File("src/P3/"+filename);
		BufferedReader reader =null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String s= null;
			int station_num[]=new int[1000];
			station_num[0]=0;
			int num=0;
			int title=0;
			String it_name[]=new String[1000];
			while((s= reader.readLine())!=null) {
				String[] a1=s.split(",");
				if(a1.length==2) {	
					it_name[num]=a1[0];
					station_num[num+1]=station_num[num]+Integer.valueOf(a1[1]);	 
					 num++;
				}else {
					Vertex v=new Vertex(a1[0],Double.valueOf(a1[1]),Double.valueOf(a1[2]),Integer.valueOf(a1[3]),title);
					add(v);
					title++;
				}			
			}
			for(int i=0;i<num;i++) {
				List<Vertex> bus_vertices = new ArrayList<>();
				for(int j=station_num[i];j<station_num[i+1]-1;j++) {
					bus_vertices.add(vertices.get(j));
					set(vertices.get(j), vertices.get(j+1), vertices.get(j+1).time-vertices.get(j).time);
				}
				bus_vertices.add(vertices.get(station_num[i]+2));
				bus_itinerary.put(it_name[i], bus_vertices);
			}
			reader.close();
			return new Planner();
		}catch (Exception e) {
			// TODO: handle exception
		}
		

		return new Planner();
	}
	
	public void add_tripSegment() {
		
		TripSegment t_wait=new WaitSegment();
		for(Edge e:edges) {
			TripSegment t_bus=new BusSegment(e.source,e.target,e.source.time,e.target.time);
			
		}
	}

}
