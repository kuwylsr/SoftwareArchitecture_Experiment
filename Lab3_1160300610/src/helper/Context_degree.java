package helper;

public class Context_degree {
	
	/*
	 * 计算某种度
	 * 
	 * @param 度的类型参数
	 * @return 返回计算出来的某种度
	 */
	public double calculate_degree(Degree_vertex centralityType) {
		return centralityType.V_degree();
	}
}
