package v2;

public class ContextLadderStrategy {

/**
 *  选择策略接口的包装函数
 * @param monkey 选择策略的猴子
 * @param graph 猴子过河的图
 * @param choice 所选策略的编号
 * @return 返回策略所选择的梯子
 */
  public Ladder ChoiceStrategy(Monkey monkey,MLGraph graph,LadderStrategy choice) {
    return choice.Strategy(monkey,graph);
  }
}
