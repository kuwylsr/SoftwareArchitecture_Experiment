package vertex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Router extends Vertex{

	// Abstraction function:
    
    // Representation invariant（表示 不变性）:
	// IP地址：字符串，用“.”分割为四部分，每部分的取值范围为[0,255]
    
    // Safety from rep exposure（表示暴露的安全性）:

	private String IP;
	
	enum Person_state{
		close,open
	}
	Person_state state;
	
	/*
	 * 顶点Router的构造函数
	 * 
	 * @param 顶点的label
	 * @return 无
	 */
	public Router(String label) {
		super(label);
	}
	
	protected void checkRep(){
		if(IP!=null) {
			String pattern= "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
			Pattern r =Pattern.compile(pattern);
			Matcher m=r.matcher(this.IP);
			assert m.matches();
		}else {
			assert true;
		}
		
    }
	
	@Override
	public void fillVertexInfo(String[] args) {
		this.IP=args[0];
		checkRep();
	}
	
	@Override
	public String[] get_VertexInfo() {
		checkRep();
		String a[]=new String[1];
		a[0]=this.IP;
		checkRep();
		return a;
	}
	
	@Override
	public String toString() {
		checkRep();
		String a=String.format(" <%s, Router, <%s>>",getLabel(),this.IP);
		checkRep();
		return a+"\n";
	}
	
	public void set_IP(String IP) {
		this.IP=IP;
	}
	
	/*
	 *将顶点的状态设置为open
	 * 
	 * @param 无 
	 * @return 无
	 */
	public void open() {
		if(this.state==Person_state.close) {
			this.state=Person_state.open;
		}
	}
	
	/*
	 *将顶点的状态设置为close
	 * 
	 * @param 无 
	 * @return 无
	 */
	public void close() {
		if(this.state==Person_state.open) {
			this.state=Person_state.close;
		}
	}
	
	/*
	 *创建一个备忘录（记录的是当前状态）
	 * 
	 * @param 无 
	 * @return 返回创建的备忘录对象
	 */
	public Memento save() {
		return new Memento(this.state);
	}
	
	/*
	 *将顶点恢复之前的状态
	 * 
	 * @param 备忘录 m 
	 * @return 无
	 */
	public void restore(Memento m) {
		this.state=m.getState();
	}
	
	/*
	 *获取当前状态
	 * 
	 * @param 备忘录 m 
	 * @return 返回当前状态
	 */
	public Person_state getState() {
		return this.state;
	}
	
	class Memento{
		Person_state m_state;
		
		/*
		 *备忘录的构造函数
		 * 
		 * @param 存储的状态s 
		 * @return 无
		 */
		public Memento(Person_state s) {
			this.m_state=s;
		}
		
		/*
		 *获取备忘录的状态
		 * 
		 * @param 无 
		 * @return 返回备忘录的状态
		 */
		public Person_state getState() {
			return this.m_state;
		}
		
		/*
		 *设置备忘录的状态
		 * 
		 * @param 状态s 
		 * @return 无
		 */
		public void setState(Person_state s) {
			m_state=s;
		}
	}

}
