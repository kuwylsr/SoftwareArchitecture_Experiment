package vertex;

public class Word extends Vertex{

	
	/*
	 * 顶点Word的构造函数
	 * 
	 * @param 顶点的label
	 * @return 无
	 */
	public Word(String label) {
		super(label);
	}

	@Override
	public void fillVertexInfo(String[] args) {
		// TODO 自动生成的方法存根
		
	}
	@Override
	public String toString() {
		String a=String.format(" <%s, Word>",getLabel());
		return a+"\n";
	}

	@Override
	public String[] get_VertexInfo() {
		// TODO 自动生成的方法存根
		return null;
	}

}
