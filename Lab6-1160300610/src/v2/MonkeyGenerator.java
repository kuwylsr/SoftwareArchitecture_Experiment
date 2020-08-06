package v2;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

public class MonkeyGenerator {

  // Abstraction function:
  //将time映射为猴子过河的总时间

  // Representation invariant（表示 不变性）:
  //time大于等于0并且为整数

  // Safety from rep exposure（表示暴露的安全性）:
  // 所有的区域块都是私有的。
  // 总之不存在表示暴露

  static MLGraph graph = new MLGraph("src/v2/data.txt");
  static int time = 0;

  static int n = graph.getLadderNum(); // 梯子的总数量
  static int h = graph.getpedalNum(); // 每个梯子上的踏板数量
  static int t = graph.gettimeSpan(); // 产生猴子的时间间隔
  static int N = graph.getmonkeyNum(); // 产生猴子的总数量
  static int k = graph.getmonkeySpan(); // 每秒产生的猴子数
  static int MV = graph.getmostV(); // 猴子最大速度
  static double ttrate=0; // 系统的吞吐率
  static double gprate=0; // 系统的公平性

  // 用close将文件控制流关闭后，只有再生成一个同文件路径名的log才能再次开启
  static String name = MonkeyGenerator.class.getName();
  static Logger log1 = LogFactory.getLogger(1, name);// 记录异常/错误的日志

  public static void main(String[] args) {

    MonkeyCrossRiverGui ge = new MonkeyCrossRiverGui();

    try {
      // 将梯子加入到图graph中
      for (int i = 0; i < n; i++) {
        Ladder l = new Ladder(h, i);
        graph.addLadder(l);
      }
      // 猴子生成器，生成之后立即启动线程
      Map<Integer, String> map = new HashMap<>();
      map.put(0, "L->R");
      map.put(1, "R->L");
      int remainder = N % k;
      for (int i = 0; i <= ((N / k) - 1) * t; i++) {
        if (i % t != 0 && i != 0) {
          Thread.sleep(1000);
          time++;
        } else {
          for (int j = 0; j < k; j++) {
            Random rd1 = new Random();
            int index1 = rd1.nextInt(2); // 方向随机指数
            int index2 = rd1.nextInt(MV - 1) + 1; // 速度随机指数
            // 猴子的编号 按照出生时间*10 加上 同一批次的顺序号[0,k)。
            Monkey m = new Monkey((i / t) * 10 + j, map.get(index1), index2, time);
            graph.addmonkey(m);
            (new Thread(m)).start();
          }
          Thread.sleep(1000);
          time++;
        }
      }
      if (remainder != 0) { // 若N除以k 不是整数的，最后一批猴子
        for (int i = 0; i < remainder; i++) {
          Random rd1 = new Random();
          int index1 = rd1.nextInt(2); // 方向随机指数
          int index2 = rd1.nextInt(MV - 1) + 1; // 速度随机指数
          Monkey m = new Monkey((N / k) * 10 + i, map.get(index1), index2, time);
          (new Thread(m)).start();
        }
        Thread.sleep(1000);
        time++;        
      }
      while(true) { //当猴子产生器 产生完猴子之后，时间会继续流逝，直到所有猴子都过到了对岸，此时的线程数量为3
        Thread.sleep(1000);
        if(Thread.activeCount()>3) {          
          time++;
        }else {
          if(ttrate!=0&&gprate!=0) {
            log1.info("吞吐率为："+String.valueOf(ttrate));
            log1.info("公平性为："+String.valueOf(gprate));
            Thread.yield();
            break;
          }else {
            continue;
          }
        }
        
      }
    } catch (InterruptedException e) {

    }
  }
}
