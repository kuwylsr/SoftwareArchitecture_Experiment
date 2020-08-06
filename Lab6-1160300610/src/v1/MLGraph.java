package v1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;



public class MLGraph {

  // Abstraction function:
  // 将ladderNum映射为梯子的数量
  // 将pedalNum映射为梯子上踏板的数量
  // 将timeSpan映射为生成猴子的时间间隔
  // 将monkeyNum映射为生成猴子的总数量
  // 将monkeySpan映射为每批猴子的数量
  // 将mostV映射为猴子的最大行进速度

  // Representation invariant（表示 不变性）:
  // ladderNum的取值范围是[1,5]
  // pedalNum大于0
  // timeSpan的取值范围是[1,5]
  // monkeyNum的取值范围是[2,100]
  // monkeySpan的取值范围是[1,3]
  // mostV大于1

  // Safety from rep exposure（表示暴露的安全性）:
  // 所有的区域块都是私有的。
  // 总之不存在表示暴露
  private final Set<Monkey> monkeys = new HashSet<>(); // 图中存储猴子的集合
  private final Set<Ladder> ladders = new HashSet<>(); // 图中存储梯子的集合

  private int ladderNum; // 梯子的数量
  private int pedalNum; // 梯子上踏板的数量
  private int timeSpan; // 生成猴子的时间间隔
  private int monkeyNum; // 生成猴子的总数量
  private int monkeySpan; // 每批猴子的数量
  private int mostV; // 猴子的最大行进速度

  /**
   * 读取文件并建造猴子过河图
   * 
   * @param filePath 图配置的文件路径
   */
  public MLGraph(String filePath) {
    File file = new File(filePath);
    try {
      Scanner sc = new Scanner(file);
      while (sc.hasNext()) {
        String s[] = sc.nextLine().split(" ");
        if (s[0].equals("ladderNum")) {
          this.ladderNum = Integer.valueOf(s[1]);
        } else if (s[0].equals("pedalNum")) {
          this.pedalNum = Integer.valueOf(s[1]);
        } else if (s[0].equals("timeSpan")) {
          this.timeSpan = Integer.valueOf(s[1]);
        } else if (s[0].equals("monkeyNum")) {
          this.monkeyNum = Integer.valueOf(s[1]);
        } else if (s[0].equals("monkeySpan")) {
          this.monkeySpan = Integer.valueOf(s[1]);
        } else if (s[0].equals("mostV")) {
          this.mostV = Integer.valueOf(s[1]);
        }
      }

    } catch (FileNotFoundException e) {

    }
  }

  /**
   * 为图中增加梯子
   * 
   * @param l 所要添加的梯子
   * @return 是否成功添加梯子
   */
  public boolean addLadder(Ladder l) {
    if (this.ladders.contains(l)) {
      return false;
    } else {
      this.ladders.add(l);
      return true;
    }
  }

  /**
   * 为图中增加猴子
   * 
   * @param m 所要添加的猴子
   * @return 是否成功添加猴子
   */
  public boolean addmonkey(Monkey m) {
    if (this.monkeys.contains(m)) {
      return false;
    } else {
      this.monkeys.add(m);
      return true;
    }
  }

  /**
   * 返回图中猴子的集合
   * @return 返回图中猴子的集合
   */
  public Set<Monkey> monkeys() {
    Set<Monkey> monkeys = Collections.synchronizedSet(new HashSet<>()); //将线程不安全的集合，变为线程安全
    monkeys.addAll(this.monkeys);
    return monkeys;
  }

  /**
   * 返回图中梯子的集合
   * @return 返回图中梯子的集合
   */
  public Set<Ladder> ladders() {
    Set<Ladder> ladders = Collections.synchronizedSet(new HashSet<>());//将线程不安全的集合，变为线程安全
    ladders.addAll(this.ladders);
    return ladders;
  }

  /**
   * 返回梯子的数量
   * @return 返回梯子的数量
   */
  public int getLadderNum() {
    return this.ladderNum;
  }

  /**
   * 返回梯子上踏板的数量
   * @return 返回梯子上踏板的数量
   */
  public int getpedalNum() {
    return this.pedalNum;
  }

  /**
   * 返回时间间隔
   * @return 返回时间间隔
   */
  public int gettimeSpan() {
    return this.timeSpan;
  }

  /**
   * 返回产生猴子的总数量
   * @return 返回产生猴子的总数量
   */
  public int getmonkeyNum() {
    return this.monkeyNum;
  }

  /**
   * 返回每批猴子的数量
   * @return 返回每批猴子的数量
   */
  public int getmonkeySpan() {
    return this.monkeySpan;
  }

  /**
   * 返回猴子的最大速度
   * @return 返回猴子的最大速度
   */
  public int getmostV() {
    return this.mostV;
  }

}
