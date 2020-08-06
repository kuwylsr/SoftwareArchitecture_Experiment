package vertex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movie extends Vertex {

  // Abstraction function:

  // Representation invariant（表示 不变性）:
  // 上映年份：四位正整数，范围为[1900, 2018]
  // 拍摄过家：字符串
  // imdbIMDb 评分：0-10 范围内的数值，最多 2 位小数

  // Safety from rep exposure（表示暴露的安全性）:


  private int yearRelease = -1;
  private String countryPhotographing = null;
  private float imdbIMDb = -1;

  /*
   * 顶点Movie的构造函数
   * 
   * @param 顶点的label
   * 
   * @return 无
   */
  public Movie(String label) {
    super(label);

  }

  protected void checkRep() {
//    if (yearRelease != -1 && countryPhotographing != null && imdbIMDb != -1) {
//      assert this.imdbIMDb >= 0 && this.imdbIMDb <= 10;
//      assert this.yearRelease >= 1900 && this.yearRelease <= 2018;
//    }

  }

  @Override
  public void fillVertexInfo(String[] args) {
    this.yearRelease = Integer.valueOf(args[0]);
    this.countryPhotographing = args[1];
    this.imdbIMDb = Float.valueOf(args[2]);
    //checkRep();
  }

  @Override
  public String[] get_VertexInfo() {
    //checkRep();
    String[] a = new String[3];
    a[0] = String.valueOf(this.yearRelease);
    a[1] = this.countryPhotographing;
    a[2] = String.valueOf(this.imdbIMDb);
    //checkRep();
    return a;
  }

  @Override
  public String toString() {
    //checkRep();
    String a = String.format(" <%s, Movie, <%d, %s, %f>>", getLabel(), this.yearRelease,
        this.countryPhotographing, this.imdbIMDb);
    //checkRep();
    return a + "\n";
  }


}
