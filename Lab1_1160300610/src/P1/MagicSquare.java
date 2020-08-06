package P1;

import java.io.BufferedReader;

import java.io.File;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class MagicSquare {
		@SuppressWarnings("resource")
		public static Boolean isLegalMagicSquare(String fileName)throws  MyException{ 
			File file = new File("src/P1/txt/"+fileName);
			BufferedReader reader = null;
			long suml=0,sumh=0,sum=0,sumz=0,sumd=0;    // suml 矩阵每一行的和；sumh矩阵每一列的和；sumz矩阵正对角线的和；sumd矩阵逆对角线的和
			int len=0;
			int k=0,i=0,j=0;
			Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]*");
			String b[][]=new String[10000][10000];
			try {
				reader = new BufferedReader(new FileReader(file));
				String s = null;
				while ((s = reader.readLine()) != null) {
					String a[] = s.split("\t");
					len=a.length;
					for(j=0;j<len;j++) {
						Matcher isNum =pattern.matcher(a[j]);
						if(!isNum.matches()) {
							System.out.println("分隔符使用错误！");
							return false;
						}
						b[k][j]=a[j];
					}
					k++;
				}
				for(i=0,len=0;b[0][i]!=null;i++)
					len++;
				for(j=0;j<len;j++) {
					sum+=Integer.valueOf(b[0][j]);
				}
				if(k!=len||k==0||len==0) {    //矩阵行列不相等
					throw new MyException("不是矩阵！");
				}
				for(i=0;i<len;i++) {
					for(j=0;j<len;j++) {
						if(Integer.valueOf(b[i][j])<0) {
							throw new MyException("矩阵存在负数！");
						}
						sumh+=Integer.valueOf(b[i][j]);
						suml+=Integer.valueOf(b[j][i]);
					}
					sumz+=Integer.valueOf(b[i][i]);
					sumd+=Integer.valueOf(b[i][len-i-1]);
					if(sumh!=sum) {    //矩阵 行和不相等
						throw new MyException("矩阵行和不相等！");
					}
					if(suml!=sum) {    //矩阵 列和不相等
						throw new MyException("矩阵列和不相等！");
					}
					sumh=0;
					suml=0;
				}
				if(sumz!=sum||sumd!=sum) { //正负对角线和 不相等
					throw new MyException("矩阵对角线和不相等！");
				}
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}catch(NumberFormatException e) {
				System.out.println("存在非整数！");
				return false;
			}
			return true;
		}

		public static boolean generateMagicSquare(int n) {
			File file = new File("src/P1/txt/"+"6.txt");
			try {
				FileWriter out = new FileWriter(file);
				int magic[][] = new int[n][n];
				int row = 0, col = n / 2, i, j, square = n * n;
				for (i = 1; i <= square; i++) {
					magic[row][col] = i;
					if (i % n == 0)
						row++;
					else {
						if (row == 0)
							row = n - 1;
						else
							row--;
						if (col == (n - 1))
							col = 0;
						else
							col++;
					}
				}
				for (i = 0; i < n; i++) {
					for (j = 0; j < n; j++) {
						out.write(String.format("%s\t", String.valueOf(magic[i][j])));
					}
					if(i!=n-1)
					out.write("\n");
				}
				out.close();
			}catch(IOException e) {
				e.printStackTrace();
			}catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("输入参数为偶数！输入不合法！");
				return false;
			}catch(NegativeArraySizeException e) {
				System.out.println("输入参数n为负数！输入不合法！");
				return false;
			}
			return true;
		}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		for(int i=1;i<=6;i++) {			
			try {
				System.out.println("第"+i+"个文件");
				System.out.println(isLegalMagicSquare(i+".txt"));
			} catch (MyException e) {
				e.printStackTrace();
			}
		}
		generateMagicSquare(17);
	}
}
