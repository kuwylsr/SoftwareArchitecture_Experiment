package P2;

 public class Person {
	private String name;
	private boolean visited;
	private int title;
	
	// Abstraction function:
    //   如果表示属于name，将其映射为一系列字符
	//   如果表示属于visited，将其映射为一个true或者false
	//	  如果表示属于title，将其映射为一个整数的标号
	
    // Representation invariant:
	//  title应大于等于0
	
    // Safety from rep exposure:
    //   所有的区域都是私有的，并且所有成员变量的类型都是immutable的
	
	private void checkRep() {
		assert this.title >=0;
	}
	public Person(String name){
		this.name=name;
		visited=false;
		title=0;
	}
	/*
	 * get the Person name
	 * 
	 * @return a String name
	 */
	public String get_name() {
		checkRep();
		return this.name;
	}
	
	/*
	 * get whether the person is visited or not
	 * 
	 * @return true or false
	 */
	public boolean get_visited() {
		checkRep();
		return this.visited;
	}
	/*
	 * set whether the person is visited or not
	 * 
	 * @return true or false after setting
	 */
	public boolean set_visited(boolean visited) {
		checkRep();
		return this.visited=visited;
	}
	/*
	 * get the title of the person
	 * 
	 * @return the title
	 */
	public int get_title() {
		checkRep();
		return this.title;
	}
	/*
	 * set the title of the person
	 * 
	 * @return the title after setting
	 */
	public int set_title(int title) {
		checkRep();
		return this.title=title;
	}
}
