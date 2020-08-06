package vertex;

public class Director extends Vertex{

	// Abstraction function:
    
    // Representation invariant（表示 不变性）:
	// 年龄：正整数
	// 性别：字符串，M/F
    
    // Safety from rep exposure（表示暴露的安全性）:
	

	private int age;
	private String Gender;
	
	/*
	 * 顶点Director的构造函数
	 * 
	 * @param 顶点的label
	 * @return 无
	 */
	public Director(String label) {
		super(label);
	}
	
	protected void checkRep(){
		if(age!=-1&&Gender!=null) {
			assert this.age>=0;
			assert this.Gender.equals("M") || this.Gender.equals("F");
		}else {
			assert true;
		}	
    }
	
	@Override
	public void fillVertexInfo(String[] args) {
		this.Gender=args[1];
		this.age=Integer.valueOf(args[0]);
		checkRep();
	}
	
	@Override
	public String[] get_VertexInfo() {
		checkRep();
		String a[]=new String[2];
		a[1]=this.Gender;
		a[0]=String.valueOf(this.age);
		checkRep();
		return a;
	}
	
	@Override
	public String toString() {
		checkRep();
		String a=String.format(" <%s, Director, <%d, %s>>",getLabel(),this.age,this.Gender);
		checkRep();
		return a+"\n";
	}
	
	

}
