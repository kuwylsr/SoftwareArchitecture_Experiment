package P2;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import P1.graph.ConcreteEdgesGraph;


public class FriendshipGraph extends  ConcreteEdgesGraph<Person>{

	// Abstraction function:
    //  如果表示属于vertices，则映射为一系列在vertices中的L类型的点
    //  如果表示属于edges，则映射为一系列在edges中的Edge类型的边
    
    // Representation invariant（表示 不变性）:
    //  该类继承ConcreteEdgesGraph<Person>类，所有表示和ConcreteEdgesGraph<Person>类表示的RI一致
    
    // Safety from rep exposure（表示暴露的安全性）:
    //   所有的区域块都是私有的。
    //   尽管vertices以及edges分别是可变数据类型Set和List，
    //但类中重写的方法并没有以他们作为参数，并没有多个变量指向一个相同的单元。
    //并且将其有关键词final定义，使得其引用不可变.
    //总之不存在表示暴露。

	
    // TODO checkRep
	//由于该类继承了ConcreteEdgesGraph<Person>类，所以用ConcreteEdgesGraph<Person>的
	//checkRep即可
	
	public int addVertex(Person name) {
			checkRep();
			add(name);
			checkRep();
			return vertices().size();
	}

	public int addEdge(Person name1, Person name2) {
		checkRep();
		set(name1, name2, 1);
		checkRep();
		return targets(name1).size();
	}
	
	public int getDistance(Person name1, Person name2) {
		checkRep();
		int distance[]=new int[vertices().size()];
		int i=0;
		Iterator<Person> it=vertices().iterator();
		while(it.hasNext()) {   //将每个人都设置为未被访问过
			Person p=it.next();
			p.set_title(i);
			p.set_visited(false);
			distance[p.get_title()]=0;
			i++;
		}
		Queue<Person> queue=new LinkedList<>();
		queue.offer(name1);
		name1.set_visited(true);
		while(!queue.isEmpty()) {
			Person temp=queue.poll();
			
			if(name1.get_title()==name2.get_title()) {
				checkRep();
				return 0;
			}
			Map<Person, Integer> map =targets(temp);
//			Iterator<Edge<Person>> its=edges.iterator();
			for(Map.Entry<Person, Integer> entry:map.entrySet()) {
//				Edge<Person> p =its.next();
				Person p1=entry.getKey();
				if(!p1.get_visited()) {
					queue.offer(p1);
					distance[p1.get_title()]=distance[temp.get_title()]+1;
//					Person_visited[p1.get_title()]=temp;
					p1.set_visited(true);
					if(p1.get_name().equals(name2.get_name())) {
						checkRep();
						return distance[p1.get_title()];
					}
				}
			}
		}
		checkRep();
		return -1;
	}
	
	public void handle(Person x1,Person x2,Person V[],int v[]) {
		int temp=x2.get_title();
		for(int i=0;;i++) {
			v[i]=temp;
			if(temp==x1.get_title()) {
				break;
			}
			temp=V[temp].get_title();
		}
	}
	
	class Edge<Person> {
    	private Person source;
    	private Person target;
    	
    	
    	// Abstraction function:
        //   如果表示属于source或target，则映射为L类型的点
    	//   如果表死属于weight，则映射为整型的数
    	
        // Representation invariant:
    	//  泛型L应为immutable类型
    	//   weight需要满足大于0
    	
        // Safety from rep exposure:
        //   所有的区域都是私有的，并且所有成员变量的类型都是immutable的
    	
    	public Edge(Person source,Person target,int weight) {
    		this.source=source;
    		this.target=target;
    	}
    	
    	public Person get_source() {
    		return this.source;
    	}
    	
    	public Person get_target() {
    		return this.target;
    	}
    }
	
	public static void main(String[] args) {
		int title[]=new int[100];
		Person P[]=new Person[100];
		FriendshipGraph graph =new FriendshipGraph();
		Person a =new Person("A");
		Person b =new Person("B");
		Person c =new Person("C");
		Person d =new Person("D");
		Person e =new Person("E");
		Person f =new Person("F");
		Person g =new Person("G");
		Person h =new Person("H");
		Person i =new Person("I");
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addVertex(c);
		graph.addVertex(d);
		graph.addVertex(e);
		graph.addVertex(f);
		graph.addVertex(g);
		graph.addVertex(h);
		graph.addVertex(i);
		
		graph.addEdge(a, b);
        graph.addEdge(b,a );
        graph.addEdge(a, e);
        graph.addEdge(e, a);
        graph.addEdge(b, e);
        graph.addEdge(e, b);
        graph.addEdge(e, f);
        graph.addEdge(f, e);
        graph.addEdge(d, e);
        graph.addEdge(e, d);
        graph.addEdge(d, g);
        graph.addEdge(g, d);
        graph.addEdge(d, h);
        graph.addEdge(h, d);
        graph.addEdge(f, i);
        graph.addEdge(i, f);
//		System.out.println(graph.getDistance(a, i,P));
//		System.out.println(graph.getDistance(b, d,P));
//		for(Person vvv:graph.vertices()) {
//			System.out.println(vvv.get_name()+":"+vvv.get_title());
//		}
//		graph.handle(a, i, P, title);
//		for(int z=0;z<title.length;z++) {
//			System.out.println(title[z]);
//			if(title[z]==a.get_title())
//				break;
//		}
		System.out.println(graph.getDistance(e, e));
		System.out.println(graph.getDistance(g, c));

	}

}
