package P3;




import java.util.LinkedList;
import java.util.Queue;

/*
*您应该将社交网络建模为一个无向图，其中每个人都是一个无向图。
连接到零或更多的人，但您的底层图形实现应该是有向图；
注：本问题拟刻画的社交网络是无向图，但你的类设计要能够支持
未来扩展到有向图。正因为此，如果要在两个 Person 对象 A 和 B 之间增加一条
社交关系，那么需要同时调用addVertex(A, B)和addVertex(B,A)两条语句
*您的解决方案应该与上面的客户机代码一起工作。的getdistance方法
应该以两个人（人）作为论据，并返回两人之间的最短的距离（一个 int 型），
或如果两个人之间没有链接则返回-1.
*您的图形实现应该具有合理的可伸缩性。我们将测试您的图形有几百个或千个顶点和边。
*为您的字段和方法使用适当的访问修饰符（公共、私有等）。如果字段/方法可以是私有的，则应该是私有的。
*除了主要方法和常量外，不要使用静态字段或方法。
*遵循java编码规范，尤其是命名和评论。提示：使用Ctrl + Shift + F自动格式化代码！
*
*额外的暗示 /假设：
*你的getdistance执行，你可能需要复习广度优先搜索。
*你可以使用标准的java库，包括类中挑出来的，但没有第三方库
*你可以假设每个人都有一个唯一的名字
*您可能需要处理不正确的输入（打印到标准输出/错误、悄无声息地失败、崩溃、抛出特殊异常等）。
*您应该编写更多的示例来测试您的图表，这与我们的主要方法类似。要打印的东西到标准输出，使用System.out.println。例如:系统的输入（“不要恐慌”）
*你也应该写JUnit测试代码来测试方法addvertex()，addedge()，和类friendshipgraph中getdistance()。所有测试用例
应包括在FriendshipGraphTest.java的目录\test\ P3。测试用例应该充足。
*
*/



public class FriendshipGraph {
	
	static final int Maxnum = 1000;
	int currentnum=0;
	int Peoplenum;//顶点数量
    int Edgenum;   //边的数量
	private Person list[]=new Person[Maxnum];    //顶点表
	private int edge[][]=new int[Maxnum][Maxnum];  //邻接矩阵
	
	public FriendshipGraph() {
		Peoplenum=0;
		Edgenum=0;
		for(int i=0;i<Maxnum;i++) {
			for(int j=0;j<Maxnum;j++) {
				edge[i][j]=0;
			}
		}
	}

	 public int addVertex(Person name) {
		 for(int i=0;i<Peoplenum;i++) {
			 if(name.name.equals(list[i].name)) {
			 System.out.println("该Person已经存在!");
				 System.exit(0);
			 }
		 }
		 list[currentnum]=name;
		 list[currentnum].title=currentnum;
		 currentnum++;
		 Peoplenum++;
		 return Peoplenum;
	}

	public int addEdge(Person name1, Person name2) {
		edge[name1.title][name2.title]=1;
		Edgenum++;
		return Edgenum;
	}
	public boolean examine(FriendshipGraph graph) {
		for(int i=0;i<graph.Peoplenum;i++) {
			
			for(int j=0;j<graph.Peoplenum;j++) {
				if(graph.edge[i][j]!=graph.edge[j][i]) {
					System.out.println("图不合法！");
					return false;
				}
			}
		}
		return true;
	}
	public int getDistance(Person name1, Person name2) {
		int temp;
		int distance[]=new int[Peoplenum];
//		int path[]=new int[Peoplenum];
		for(int i=0;i<Peoplenum;i++) {   //将每个人都设置为未被访问过
			list[i].visited=false;
			distance[i]=0;
		}
		Queue<Integer> queue=new LinkedList<>();
		queue.offer(name1.title);
		name1.visited=true;
		while(!queue.isEmpty()) {
			temp=queue.poll();

			if(name1.title==name2.title)
				return 0;

			for(int i=0;i<Peoplenum;i++) {
				if(edge[temp][i]==1&&!list[i].visited) {  //将所有与temp有连接的人入队
					queue.offer(i);
					distance[i]=distance[temp]+1;
//					path[i]=temp;     输出路径
					list[i].visited=true;
					if(list[i].title==name2.title) {  //如果到达目标人物，结束程序，返回距离
						return distance[i];
					}
				}
			}
		}
		return -1;
	}
	

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
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
		System.out.println(graph.getDistance(a, i));
		System.out.println(graph.getDistance(b, d)); 
		System.out.println(graph.getDistance(e, e));
		System.out.println(graph.getDistance(g, c));
		
	}

}
