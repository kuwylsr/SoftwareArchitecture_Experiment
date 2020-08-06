package vertex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movie extends Vertex{

	// Abstraction function:
    
    // Representation invariant（表示 不变性）:
	// 上映年份：四位正整数，范围为[1900, 2018]
	// 拍摄过家：字符串
	// IMDb 评分：0-10 范围内的数值，最多 2 位小数
    
    // Safety from rep exposure（表示暴露的安全性）:
	

	private int year_Release=-1;
	private String country_Photographing=null;
	private float IMDb=-1;
	
	/*
	 * 顶点Movie的构造函数
	 * 
	 * @param 顶点的label
	 * @return 无
	 */
	public Movie(String label) {
		super(label);
		
	}
	
	protected void checkRep(){
		if(year_Release!=-1&&country_Photographing!=null&&IMDb!=-1) {
			assert this.IMDb>=0&&this.IMDb<=10;
			assert this.year_Release>=1900&&this.year_Release<=2018;
		}
		
    }
	
	@Override
	public void fillVertexInfo(String[] args) {
		this.year_Release=Integer.valueOf(args[0]);
		this.country_Photographing=args[1];
		this.IMDb=Float.valueOf(args[2]);
		checkRep();
	}
	
	@Override
	public String[] get_VertexInfo() {
		checkRep();
		String a[]=new String[3];
		a[0]=String.valueOf(this.year_Release);
		a[1]=this.country_Photographing;
		a[2]=String.valueOf(this.IMDb);
		checkRep();
		return a;
	}
	
	@Override
	public String toString() {
		checkRep();
		String a=String.format(" <%s, Movie, <%d, %s, %f>>",getLabel(),this.year_Release,this.country_Photographing,this.IMDb);
		checkRep();
		return a+"\n";
	}
	

}
