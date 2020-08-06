package v2;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;



public class Monkey implements Runnable {

  // Abstraction function:
  // 将monkeyID映射为猴子的编号
  // 将direction映射为猴子前进的方向
  // 将speed映射为猴子前进的速度
  // 将bornTime映射为猴子的出生时间
  // 将reachTime映射为猴子到达对岸的时间
  // 将State映射为猴子的状态
  // 将publicTime映射为每个猴子的公共时间

  // Representation invariant（表示 不变性）:
  // monkeyID大于0
  // direction只能等于"L->R"或者"R->L"
  // State只能等于"UnReach"或者"Reach"

  // Safety from rep exposure（表示暴露的安全性）:
  // 所有的区域块都是私有的。
  // 总之不存在表示暴露

  private final int monkeyID; // 猴子的编号
  private final String direction; // 猴子前进的方向
  private final int speed; // 猴子前进的速度
  protected final int bornTime; // 猴子的出生时间
  protected int reachTime; // 猴子到达对岸的时间
  protected String State = "UnReach"; // 猴子的状态
  protected int publicTime = MonkeyGenerator.time; // 每个猴子的公共时间

  /**
   * 猴子的构造函数
   * 
   * @param monkeyID 猴子的编号
   * @param dircetion 猴子的行进方向
   * @param speed 猴子的速度
   * @param time 猴子的出生时间
   */
  public Monkey(int monkeyID, String dircetion, int speed, int time) {
    this.monkeyID = monkeyID;
    this.direction = dircetion;
    this.speed = speed;
    this.bornTime = time;
  }

  @Override
  public void run() {
    Map<String, String> map1 = new HashMap<>(); // 为写日志和打印信息提供方便
    map1.put("L->R", "左");
    map1.put("R->L", "右");
    Map<String, String> map2 = new HashMap<>(); // 为写日志和打印信息提供方便
    map2.put("L->R", "左岸抵达右岸");
    map2.put("R->L", "右岸抵达左岸");
    // 猴子选择策略
    Random rd1 = new Random();
    int index = rd1.nextInt(1);
    Ladder l = null;
    try {
      if (index == 1) { // 策略一
        synchronized (Monkey.class) { // 锁住猴子的类，让同一时间只能有一只猴子选择策略
          l = new ContextLadderStrategy().ChoiceStrategy(this, MonkeyGenerator.graph,
              new Strategy1());
        }
        while (l == Strategy1.no) { // 若目前为止没有满足策略的梯子，继续用次策略寻找，直到找到为止
//          MonkeyGenerator.log1
//              .info("第" + this.publicTime + "秒；" + this.monkeyID + "正在" + map1.get(this.direction)
//                  + "岸等待；" + "已经出生" + (this.publicTime - this.bornTime) + "秒");
          System.out.println(
              "第" + this.publicTime + "秒；" + this.monkeyID + "正在" + map1.get(this.direction)
                  + "岸等待；" + "已经出生" + (this.publicTime - this.bornTime) + "秒");
          Thread.sleep(1000);
          this.publicTime++;
          synchronized (Monkey.class) { // 锁住猴子的类，让同一时间只能有一只猴子选择策略
            l = new ContextLadderStrategy().ChoiceStrategy(this, MonkeyGenerator.graph,
                new Strategy1());
          }
        }
        // this.publicTime++; //上到第0个踏板不需要时间
//        MonkeyGenerator.log1.info(
//            "第" + this.publicTime + "秒；" + this.monkeyID + "正在第" + l.getTitle() + "架梯子的第0个踏板上，自"
//                + this.direction + "前进；" + "已经出生" + (this.publicTime - this.bornTime) + "秒");
        System.out.println(
            "第" + this.publicTime + "秒；" + this.monkeyID + "正在第" + l.getTitle() + "架梯子的第0个踏板上，自"
                + this.direction + "前进；" + "已经出生" + (this.publicTime - this.bornTime) + "秒");
        Thread.sleep(1000);
      } else if (index == 0) { //策略二
        synchronized (Monkey.class) { // 锁住猴子的类，让同一时间只能有一只猴子选择策略
          l = new ContextLadderStrategy().ChoiceStrategy(this, MonkeyGenerator.graph,
              new Strategy2());
        }
        while (l == Strategy2.no) { // 若目前为止没有满足策略的梯子，继续用次策略寻找，直到找到为止
//          MonkeyGenerator.log1
//              .info("第" + this.publicTime + "秒；" + this.monkeyID + "正在" + map1.get(this.direction)
//                  + "岸等待；" + "已经出生" + (this.publicTime - this.bornTime) + "秒");
          System.out.println(
              "第" + this.publicTime + "秒；" + this.monkeyID + "正在" + map1.get(this.direction)
                  + "岸等待；" + "已经出生" + (this.publicTime - this.bornTime) + "秒");
          Thread.sleep(1000);
          this.publicTime++;
          synchronized (Monkey.class) { // 锁住猴子的类，让同一时间只能有一只猴子选择策略
            l = new ContextLadderStrategy().ChoiceStrategy(this, MonkeyGenerator.graph,
                new Strategy2());
          }
        }
//        MonkeyGenerator.log1.info(
//            "第" + this.publicTime + "秒；" + this.monkeyID + "正在第" + l.getTitle() + "架梯子的第0个踏板上，自"
//                + this.direction + "前进；" + "已经出生" + (this.publicTime - this.bornTime) + "秒");
        System.out.println(
            "第" + this.publicTime + "秒；" + this.monkeyID + "正在第" + l.getTitle() + "架梯子的第0个踏板上，自"
                + this.direction + "前进；" + "已经出生" + (this.publicTime - this.bornTime) + "秒");
        Thread.sleep(1000);
      } else if (index == 2) { //策略三
        synchronized (Monkey.class) { // 锁住猴子的类，让同一时间只能有一只猴子选择策略
          l = new ContextLadderStrategy().ChoiceStrategy(this, MonkeyGenerator.graph,
              new Strategy3());
        }
        while (l == Strategy3.no) { // 若目前为止没有满足策略的梯子，继续用次策略寻找，直到找到为止
//          MonkeyGenerator.log1
//              .info("第" + this.publicTime + "秒；" + this.monkeyID + "正在" + map1.get(this.direction)
//                  + "岸等待；" + "已经出生" + (this.publicTime - this.bornTime) + "秒");
          System.out.println(
              "第" + this.publicTime + "秒；" + this.monkeyID + "正在" + map1.get(this.direction)
                  + "岸等待；" + "已经出生" + (this.publicTime - this.bornTime) + "秒");
          Thread.sleep(1000);
          this.publicTime++;
          synchronized (Monkey.class) { // 锁住猴子的类，让同一时间只能有一只猴子选择策略
            l = new ContextLadderStrategy().ChoiceStrategy(this, MonkeyGenerator.graph,
                new Strategy3());
          }
        }
//        MonkeyGenerator.log1.info(
//            "第" + this.publicTime + "秒；" + this.monkeyID + "正在第" + l.getTitle() + "架梯子的第0个踏板上，自"
//                + this.direction + "前进；" + "已经出生" + (this.publicTime - this.bornTime) + "秒");
        System.out.println(
            "第" + this.publicTime + "秒；" + this.monkeyID + "正在第" + l.getTitle() + "架梯子的第0个踏板上，自"
                + this.direction + "前进；" + "已经出生" + (this.publicTime - this.bornTime) + "秒");
        Thread.sleep(1000);
      }

      // 猴子在梯子上行走
      int endtemp = 0; // 每过1秒后猴子实际到达的位置，初始为0
      int temp = endtemp; // 记录猴子能跳到的位置，初始化能跳到自己现处位置
      while (true) {
        // 从猴子所在踏板的下一个开始判断，到1秒后的理论位置或者梯子的末尾
        for (int i = endtemp + 1; i <= endtemp + this.speed && i <= MonkeyGenerator.h - 1; i++) {
          if (l.pedal[i] == Ladder.no) {
            temp = i; // 记录1秒内猴子所能爬的最远踏板
          } else {
            break;
          }
        }
        if (temp == endtemp) { // 若猴子能跳到的位置还是原先的位置
          this.publicTime++;
//          MonkeyGenerator.log1.info("第" + this.publicTime + "秒；" + this.monkeyID + "正在第"
//              + l.getTitle() + "架梯子的第" + temp + "个踏板上，自" + this.direction + "前进；" + "已经出生"
//              + (this.publicTime - this.bornTime) + "秒");
          System.out.println("第" + this.publicTime + "秒；" + this.monkeyID + "正在第" + l.getTitle()
              + "架梯子的第" + temp + "个踏板上，自" + this.direction + "前进；" + "已经出生"
              + (this.publicTime - this.bornTime) + "秒");
          Thread.sleep(1000);
        } else { // 将猴子移送到1秒内所能到达的最远位置
          synchronized (l) { // 在移动时，锁住梯子
            l.pedal[temp] = this;
            l.pedal[endtemp] = Ladder.no;
            endtemp = temp;
          }
          this.publicTime++;
//          MonkeyGenerator.log1.info("第" + this.publicTime + "秒；" + this.monkeyID + "正在第"
//              + l.getTitle() + "架梯子的第" + temp + "个踏板上，自" + this.direction + "前进；" + "已经出生"
//              + (this.publicTime - this.bornTime) + "秒");
          System.out.println("第" + this.publicTime + "秒；" + this.monkeyID + "正在第" + l.getTitle()
              + "架梯子的第" + temp + "个踏板上，自" + this.direction + "前进；" + "已经出生"
              + (this.publicTime - this.bornTime) + "秒");
          Thread.sleep(1000);
          // 若猴子处在了梯子的最后一块踏板
          if (l.pedal[MonkeyGenerator.h - 1].equals(this)) { // 若猴子到达了梯子的最后一块踏板
            l.pedal[MonkeyGenerator.h - 1] = Ladder.no;
            this.publicTime++;
            this.reachTime = this.publicTime;
            this.State = "Reach";
//            MonkeyGenerator.log1.info("第" + this.publicTime + "秒；" + this.monkeyID + "以从"
//                + map2.get(this.direction) + "，共耗时" + (this.publicTime - this.bornTime) + "秒");
            System.out.println("第" + this.publicTime + "秒；" + this.monkeyID + "以从"
                + map2.get(this.direction) + "，共耗时" + (this.publicTime - this.bornTime) + "秒");
            break;
          }
        }
        temp = endtemp;
      }
      Thread.yield(); // 线程中断
    } catch (InterruptedException e) {
      // TODO 自动生成的 catch 块
      e.printStackTrace();
    }

  }

  /**
   * 返回猴子的ID编号
   * 
   * @return 返回猴子的ID编号
   */
  public int getID() {
    return this.monkeyID;
  }

  /**
   * 返回猴子的行进方向
   * 
   * @return 返回猴子的行进方向
   */
  public String getDirection() {
    return this.direction;
  }

  /**
   * 获取猴子的出生时间
   * 
   * @return 返回猴子的出生时间
   */
  public int getTime() {
    return this.bornTime;
  }

  @Override
  public boolean equals(Object b) {
    if (b instanceof Monkey) {
      Monkey other = (Monkey) b;
      if (this.monkeyID == other.monkeyID) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }

  }

  @Override
  public int hashCode() {
    return String.valueOf(monkeyID).hashCode();
  }
}
