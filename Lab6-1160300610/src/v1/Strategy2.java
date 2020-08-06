package v1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 此策略用于选择：优先选择整体推进速度最快的梯子（没有与我对向而行的猴子、其上的猴子数量最少）；
 * 
 * @author 1160300610_李思睿
 *
 */
public class Strategy2 implements LadderStrategy {

  // Abstraction function:
  //静态变量no映射为一个假梯子（不存在的梯子相当于null）
  
  static Ladder no = new Ladder(1, Integer.MAX_VALUE);

  @Override
  public Ladder Strategy(Monkey monkey, MLGraph graph) {
    Map<Ladder, Integer> map = new HashMap<>();
    synchronized (graph.ladders()) {
      for (Ladder l : graph.ladders()) {
        int num = 0;
        synchronized (l) {
          map.put(l, num);
          Monkey[] listpedal = l.pedal;
          for (int i = 0; i < listpedal.length; i++) {
            if (listpedal[i] != Ladder.no) {
              if (!listpedal[i].getDirection().equals(monkey.getDirection())) {
                map.remove(l);
                break;
              }
            }
            if (listpedal[i] != Ladder.no) {
              num++;
              map.put(l, num);
            }
          }
        }
      }
      // map按Value值进行排序的函数
      Comparator<Map.Entry<Ladder, Integer>> valueComparator =
          new Comparator<Map.Entry<Ladder, Integer>>() {
            @Override
            public int compare(Entry<Ladder, Integer> o1, Entry<Ladder, Integer> o2) {
              return o1.getValue() - o2.getValue();
            }
          };
      // map转换成list进行排序
      List<Map.Entry<Ladder, Integer>> list =
          new ArrayList<Map.Entry<Ladder, Integer>>(map.entrySet());
      // 排序
      Collections.sort(list, valueComparator);
      for (Map.Entry<Ladder, Integer> entry : list) {
        if (entry.getKey().pedal[0] == Ladder.no) {
          entry.getKey().pedal[0] = monkey;
          return entry.getKey();
        }
      }

      return no;
    }

  }

}
