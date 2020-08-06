package v1;

public interface LadderStrategy {

  /**
   *  策略选择函数
   * @param monkey 所要选择策略的猴子
   * @param graph 伙子过河的图
   * @return 返回策略所选择的梯子
   */
  public abstract Ladder Strategy(Monkey monkey,MLGraph graph);
}
