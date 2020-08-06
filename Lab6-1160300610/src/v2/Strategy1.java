package v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 此策略用于选择：优先选择没有猴子的梯子，
 * 若所有梯子上都有猴子，则优先选择没有与我对向而行的猴子的梯子；
 * 若满足该条件的梯子有很多， 则随机选择
 * 
 * @author 1160300610_李思睿
 *
 */
public class Strategy1 implements LadderStrategy {

  // Abstraction function:
  //静态变量no映射为一个假梯子（不存在的梯子相当于null）
  
  static Ladder no = new Ladder(1, Integer.MAX_VALUE);

  @Override
  public Ladder Strategy(Monkey monkey, MLGraph graph) {
    List<Ladder> success1 = new ArrayList<>();
    List<Ladder> success2 = new ArrayList<>();
    int flag = 1;
    Set<Ladder> s = graph.ladders();
    synchronized (s) {
      for (Ladder l : s) {
        synchronized (l) {
          Monkey[] listpedal = l.pedal;
          for (int i = 0; i < listpedal.length; i++) {
            if (listpedal[i] != Ladder.no) {
              flag = 2;
              if (!listpedal[i].getDirection().equals(monkey.getDirection())) {
                flag = 0;
                break;
              }
            }
          }
          if (flag == 1) {
            success1.add(l);
          } else if (flag == 2) {
            success2.add(l);
          }
          flag = 1;
        }
      }
      if (!success1.isEmpty()) {
        int index = (int) (Math.random() * success1.size());
        if (success1.get(index).pedal[0] == Ladder.no) {
          success1.get(index).pedal[0] = monkey;
          return success1.get(index);
        }
      } else if (!success2.isEmpty()) {
        int index = (int) (Math.random() * success2.size());
        if (success2.get(index).pedal[0] == Ladder.no) {
          success2.get(index).pedal[0] = monkey;
          return success2.get(index);
        }
      }
    }
    return no;

  }

}
