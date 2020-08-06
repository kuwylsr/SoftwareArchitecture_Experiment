package v2;

public class Ladder {

  // Abstraction function:
  //将n映射为梯子上踏板的数量
  //将title映射为梯子的编号
  //pedal[]数组映射为梯子上每个踏板上的猴子的集合
  //静态变量no映射为一个空猴子

  // Representation invariant（表示 不变性）:
  //梯子的数量n要求大于0且为整数
  //梯子编号的取值范围是[0,n)

  // Safety from rep exposure（表示暴露的安全性）:
  // 由于梯子上的踏板状态是可以改变的，也是猴子可以观察的，所以将其设置成了public
  // 所有的区域块都是私有的。
  // 总之不存在表示暴露

  private final int n;
  private final int title; // 梯子的编号[0,n)
  public Monkey[] pedal;
  static Monkey no = new Monkey(Integer.MAX_VALUE, null, 0, 0);

  /**
   * 
   * @param n 梯子上踏板的数量
   * @param title 梯子的编号
   */
  public Ladder(int n, int title) {
    this.n = n;
    this.title = title;
    pedal = new Monkey[n];
    for (int i = 0; i < n; i++) { //初始化踏板上的猴子
      pedal[i] = no;
    }
  }

  /**
   * 获取梯子的编号
   * @return 返回梯子的编号
   */
  public int getTitle() {
    return this.title;
  }
}
