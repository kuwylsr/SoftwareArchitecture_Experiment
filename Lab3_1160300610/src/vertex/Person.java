package vertex;

public class Person extends Vertex{
	
	// Abstraction function:
    
    // Representation invariant（表示 不变性）:
	// 性别为：M/F
	// 年龄为：正整数
    
    // Safety from rep exposure（表示暴露的安全性）:
	
	private int age;
	private String Gender;
	private double weight;
	
	private Person_state state;
	
	enum Person_state{
		deactive,active,locked
	}
	
	
	/*
	 * 顶点Person的构造函数
	 * 
	 * @param 顶点的label
	 * @return 无
	 */
	public Person(String label) {
		super(label);
	}
	
	protected void checkRep(){
		assert this.age>=0;
		assert this.Gender.equals("M") || this.Gender.equals("F");
    }

	
	@Override
	public void fillVertexInfo(String[] args) {
		this.Gender=args[0];
		this.age=Integer.valueOf(args[1]);
		this.weight=0;
		checkRep();
	}
	
	@Override
	public String[] get_VertexInfo() {
		checkRep();
		String a[]=new String[2];
		a[0]=this.Gender;
		a[1]=String.valueOf(this.age);
		checkRep();
		return a;
	}
	
	@Override
	public String toString() {
		checkRep();
		String a=String.format(" <%s, Person, <%d, %s>>（%f）",getLabel(),this.age,this.Gender,this.weight);
		checkRep();
		return a+"\n";
	}
	public void set_age(int age) {
		this.age=age;
	}
	public void set_Gender(String Gender) {
		this.Gender=Gender;
	}
	public double get_weight() {
		return this.weight;
	}
	public void set_weight(double weight) {
		this.weight=weight;
	}
	
	/*
	 *将顶点的状态设置为lock
	 * 
	 * @param 无 
	 * @return 无
	 */
	public void lock() {
		if(this.state==Person_state.deactive) {
			this.state=Person_state.locked;
		}else if(this.state==Person_state.active) {
			this.state=Person_state.locked;
		}
	}
	
	/*
	 *将顶点的状态设置为unlock
	 * 
	 * @param 无 
	 * @return 无
	 */
	public void unlock() {
		if(this.state==Person_state.locked) {
			this.state=Person_state.active;
		}
	}
	
	/*
	 *将顶点的状态设置为deactive
	 * 
	 * @param 无 
	 * @return 无
	 */
	public void deactive() {
		if(this.state==Person_state.active) {
			this.state=Person_state.deactive;
		}
	}
	
	/*
	 *将顶点的状态设置为active
	 * 
	 * @param 无 
	 * @return 无
	 */
	public void active() {
		if(this.state==Person_state.deactive) {
			this.state=Person_state.active;
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
