package vertex;

public class Person extends Vertex {

  // Abstraction function:

  // Representation invariant（表示 不变性）:
  // 性别为：M/F
  // 年龄为：正整数

  // Safety from rep exposure（表示暴露的安全性）:

  private int age = -1;
  private String gender = null;
  private double weight = -1;

  PersonState state;

  enum PersonState {
    deactive, active, locked
  }


  /*
   * 顶点Person的构造函数
   * 
   * @param 顶点的label
   * 
   * @return 无
   */
  public Person(String label) {
    super(label);
  }

  protected void checkRep() {
//    if (age != -1 && gender != null && weight != -1) {
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
    this.weight = 0;
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
    String a = String.format(" <%s, Person, <%s, %d>>（%f）", getLabel(), this.gender, this.age,
        this.weight);
    //checkRep();
    return a + "\n";
  }

  public void set_weight(double weight) {
    this.weight = weight;
  }

  /**
   * 将顶点的状态设置为lock
   * 
   * @param 无
   * 
   * @return 无
   */
  public void lock() {
    if (this.state == PersonState.deactive) {
      this.state = PersonState.locked;
    } else if (this.state == PersonState.active) {
      this.state = PersonState.locked;
    }
  }

  /**
   * 将顶点的状态设置为unlock
   * 
   * @param 无
   * 
   * @return 无
   */
  public void unlock() {
    if (this.state == PersonState.locked) {
      this.state = PersonState.active;
    }
  }

  /**
   * 将顶点的状态设置为deactive
   * 
   * @param 无
   * 
   * @return 无
   */
  public void deactive() {
    if (this.state == PersonState.active) {
      this.state = PersonState.deactive;
    }
  }

  /**
   * 将顶点的状态设置为active
   * 
   * @param 无
   * 
   * @return 无
   */
  public void active() {
    if (this.state == PersonState.deactive) {
      this.state = PersonState.active;
    }
  }

  /*
   * 创建一个备忘录（记录的是当前状态）
   * 
   * @param 无
   * 
   * @return 返回创建的备忘录对象
   */
  public Memento save() {
    return new Memento(this.state);
  }

  /*
   * 将顶点恢复之前的状态
   * 
   * @param 备忘录 m
   * 
   * @return 无
   */
  public void restore(Memento m) {
    this.state = m.getState();
  }

  /*
   * 获取当前状态
   * 
   * @param 备忘录 m
   * 
   * @return 返回当前状态
   */
  public PersonState getState() {
    return this.state;
  }

  class Memento {
    PersonState menState;

    /*
     * 备忘录的构造函数
     * 
     * @param 存储的状态s
     * 
     * @return 无
     */
    public Memento(PersonState s) {
      this.menState = s;
    }

    /*
     * 获取备忘录的状态
     * 
     * @param 无
     * 
     * @return 返回备忘录的状态
     */
    public PersonState getState() {
      return this.menState;
    }

    /*
     * 设置备忘录的状态
     * 
     * @param 状态s
     * 
     * @return 无
     */
    public void setState(PersonState s) {
      menState = s;
    }
  }

}
