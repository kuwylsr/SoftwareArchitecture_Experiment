package vertex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vertex.Router.Memento;
import vertex.Router.PersonState;

public class Server extends Vertex {


  // Abstraction function:

  // Representation invariant（表示 不变性）:
  // IP地址：字符串，用“.”分割为四部分，每部分的取值范围为[0,255]

  // Safety from rep exposure（表示暴露的安全性）:

  private String ip;

  enum PersonState {
    close, open
  }

  PersonState state;

  /*
   * 顶点Server的构造函数
   * 
   * @param 顶点的label
   * 
   * @return 无
   */
  public Server(String label) {
    super(label);
  }

  protected void checkRep() {
//    if (ip != null) {
//      String pattern =
//          "^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$";
//      Pattern r = Pattern.compile(pattern);
//      Matcher m = r.matcher(this.ip);
//      assert m.matches();
//    } else {
//      assert true;
//    }

  }

  @Override
  public void fillVertexInfo(String[] args) {
    this.ip = args[0];
    //checkRep();
  }

  @Override
  public String[] get_VertexInfo() {
    //checkRep();
    String[] a = new String[1];
    a[0] = this.ip;
    //checkRep();
    return a;
  }

  @Override
  public String toString() {
    //checkRep();
    String a = String.format(" <%s, Server, <%s>>", getLabel(), this.ip);
    //checkRep();
    return a + "\n";
  }

  public void setIP(String ip) {
    this.ip = ip;
  }

  /**
   * 将顶点的状态设置为open
   * 
   * @param 无
   * 
   * @return 无
   */
  public void open() {
    if (this.state == PersonState.close) {
      this.state = PersonState.open;
    }
  }

  /**
   * 将顶点的状态设置为close
   * 
   * @param 无
   * 
   * @return 无
   */
  public void close() {
    if (this.state == PersonState.open) {
      this.state = PersonState.close;
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
    PersonState memState;

    /*
     * 备忘录的构造函数
     * 
     * @param 存储的状态s
     * 
     * @return 无
     */
    public Memento(PersonState s) {
      this.memState = s;
    }

    /*
     * 获取备忘录的状态
     * 
     * @param 无
     * 
     * @return 返回备忘录的状态
     */
    public PersonState getState() {
      return this.memState;
    }

    /*
     * 设置备忘录的状态
     * 
     * @param 状态s
     * 
     * @return 无
     */
    public void setState(PersonState s) {
      memState = s;
    }
  }

}
