package vertex;

public class Director extends Vertex {

  // Abstraction function:

  // Representation invariant（表示 不变性）:
  // 年龄：正整数
  // 性别：字符串，M/F

  // Safety from rep exposure（表示暴露的安全性）:


  private int age;
  private String gender;

  /*
   * 顶点Director的构造函数
   * 
   * @param 顶点的label
   * 
   * @return 无
   */
  public Director(String label) {
    super(label);
  }

  protected void checkRep() {
//    if (age != -1 && gender != null) {
//      assert this.age >= 0;
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
    String a = String.format(" <%s, Director, <%s, %d>>", getLabel(), this.gender, this.age);
    //checkRep();
    return a + "\n";
  }



}
