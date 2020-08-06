package v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 此策略用于选择：优先选择没有猴子的梯子，
 * 若所有梯子上都有猴子，则在岸边等待，直到某个梯子空闲出来；
 * @author 1160300610_李思睿
 *
 */
public class Strategy3 implements LadderStrategy{

  // Abstraction function:
  //静态变量no映射为一个假梯子（不存在的梯子相当于null）
  
  static Ladder no = new Ladder(1, Integer.MAX_VALUE);
  
  @Override
  public Ladder Strategy(Monkey monkey, MLGraph graph) {
    List<Ladder> success = new ArrayList<>();
    int flag =0;
    Set<Ladder> s =graph.ladders();
    synchronized (s) {
      for(Ladder l : s) {
        synchronized (l) {
          Monkey[] listpedal = l.pedal;
          for(int i=0;i<listpedal.length;i++) {
            if(listpedal[i]!=Ladder.no) {
              flag =1;
              break;
            }
          }
          if(flag ==1 ) {
            flag =0;
            continue;
          }else {
            success.add(l);
          }          
        }      
      }
      if(!success.isEmpty()) {
        int index = (int)(Math.random()*success.size());
        if(success.get(index).pedal[0] == Ladder.no) {
          success.get(index).pedal[0] = monkey;
          return success.get(index);
        }else {
          return no;
        }     
      }else {
        return no;
      } 
    }  
  }

}
