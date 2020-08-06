package helper;

public class ContextDegree {

  /*
   * 计算某种度
   * 
   * @param 度的类型参数
   * 
   * @return 返回计算出来的某种度
   */
  public double calculate_degree(DegreeVertex centralityType) {
    return centralityType.vertexDegree();
  }
}
