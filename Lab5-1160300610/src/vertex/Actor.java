package vertex;

import java.util.regex.Pattern;

public class Actor extends Vertex {

  // Abstraction function:

  // Representation invariant（表示 不变性）:
  // 年龄：正整数
  // 性别：字符串，M/F

  // Safety from rep exposure（表示暴露的安全性）:
  // 所有的区域块都是私有的。
  // 总之不存在表示暴露。


  private int age = -1;
  private String gender = null;

  /*
   * 顶点Actor的构造函数
   * 
   * @param 顶点的label
   * 
   * @return 无
   */
  public Actor(String label) {
    super(label);

  }

  @Override
  protected void checkRep() {
//    if (age != -1 && gender != null) {
//      Pattern pattern = Pattern.compile("[0-9]+(.0)?0*");
//      assert pattern.matcher(String.valueOf(age)).matches();
//      assert this.gender.equals("M") || this.gender.equals("F");
//    } else {
//      assert true;
//    }
  }

  @Override
  public void fillVertexInfo(String[] args) {
    this.gender = args[0];
    this.age = Integer.valueOf(args[1]);
    //checkRep();
  }

  @Override
  public String[] get_VertexInfo() {
    //checkRep();
    String[] a = new String[2];
    a[0] = this.gender;
    a[1] = String.valueOf(this.age);
    //checkRep();
    return a;
  }


  @Override
  public String toString() {
    //checkRep();
    String a = String.format(" <%s, Actor, <%s, %d>>", getLabel(), this.gender, this.age);
    return a + "\n";
  }

}
