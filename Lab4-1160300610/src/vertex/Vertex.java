package vertex;


public abstract class Vertex {
	
	
	// Abstraction function:
    
    // Representation invariant（表示 不变性）:
	// 标签：字符串类型
    
    // Safety from rep exposure（表示暴露的安全性）:
	
	private String label;
	private boolean visited;
	private int title;
	
	
	/*
	 * 顶点Vertex的构造函数
	 * 
	 * @param 顶点的label
	 * @return 无
	 */
	public Vertex(String label) {
		this.label=label;
		this.visited=false;
		this.title=0;
	}
	
	/*
	 *给顶点增加属性信息
	 * 
	 * @param 属性信息的字符串数组args
	 * @return 无
	 */
	public abstract void fillVertexInfo(String[] args);
	
	/*
	 * 获取顶点所增加的属性信息
	 * 
	 * @param 无
	 * @return 返回顶点所增加的属性信息
	 */
	public abstract String[] get_VertexInfo();
	
	protected abstract void checkRep();
	
	/*
	 *获取顶点的标签
	 * 
	 * @param 无 
	 * @return 返回顶点的标签
	 */
	public String getLabel() {
		return this.label;
	}
	
	/*
	 *获取顶点是否被访问
	 * 
	 * @param 无 
	 * @return 返回顶点是否被访问
	 */
	public boolean get_visited() {
		return this.visited;
	}
	
	/*
	 *设置顶点是否被访问
	 * 
	 * @param 访问visited 
	 * @return 返回是否成功设置顶点是否被访问
	 */
	public boolean set_visited(boolean visited) {
		return this.visited=visited;
	}
	
	/*
	 *获取顶点的编号
	 * 
	 * @param 无 
	 * @return 返回顶点的编号
	 */
	public int get_title() {
		return this.title;
	}
	
	/*
	 *设置顶点的编号
	 * 
	 * @param 编号title 
	 * @return 返回是否成功设置顶点的编号
	 */
	public boolean set_title(int title) {
		this.title=title;
		return true;
	}
	
	/*
	 *设置标签顶点的
	 * 
	 * @param 顶点的标签label 
	 * @return 返回顶点的编号
	 */
	public boolean set_Label(String Label) {
		this.label=Label;
		return true;
	}
	
	@Override
	public boolean equals(Object b) {
		if(b instanceof Vertex) {
			Vertex other=(Vertex) b;
			if(this.label.equals(other.label)) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}	
	}
	@Override
	public int hashCode() {
		return this.label.hashCode()*37;
	}
    
	@Override
	public abstract String toString();
	
}
