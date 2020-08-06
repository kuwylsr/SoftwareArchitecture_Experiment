package application;

import edge.Edge;
import edge.NetworkConnection;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import factory.GraphPoetFactory;
import factory.LogFactory;
import factory.NetworkTopologyFactory;
import graph.NetworkTopology;
import helper.CalculateBetweennessCentrality;
import helper.CalculateClosenessCentrality;
import helper.CalculateDegreeCentrality;
import helper.ContextDegree;
import helper.ContextIO;
import helper.GraphMetrics;
import helper.GraphVisualizationHelper;
import helper.JFreeChartOfIO;
import helper.ParseCommandHelper;
import helper.UseBufferR;
import helper.UseBufferW;
import helper.UseNioR;
import helper.UseNioW;
import helper.UseReaderR;
import helper.UseStreamR;
import helper.UseStreamW;
import helper.UseWriterW;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import vertex.Computer;
import vertex.Router;
import vertex.Server;
import vertex.Vertex;

public class NetworkTopologyApp {

  /**
   * 菜单.
   * 
   * @return 选择序号。
   */
  public int menu() {
    Scanner in = new Scanner(System.in);
    System.out.println("-------------------菜单-------------------");
    System.out.println("Management for GraphPoetApp");
    System.out.println("1.Read_file");
    System.out.println("2.print the imformation of the graph");
    System.out.println("3.version of GUI");
    System.out.println("4.operations of Vertex");
    System.out.println("5.operations of Edge");
    System.out.println("6.calculating of degrees");
    System.out.println("7.calculating of graph_degrees");
    System.out.println("8.calculating the distance of two Vertex");
    System.out.println("9.execute the commands");
    System.out.println("10.inquire the log");
    System.out.println("11.Write_file");
    System.out.println("12.Compare the IO time");
    System.out.println("13.exit the program!");
    System.out.println("------------------------------------------");
    int itemSelected = in.nextInt();
    return itemSelected;
  }

  /**
   * 修改顶点的label.
   * 
   * @param graph startLabel endLabel.
   * 
   * @return 是否成功修改.
   */
  public boolean setVertexLabel(NetworkTopology graph, String startLabel, String endLabel) {

    for (Vertex v : graph.vertices()) {
      if (v.getLabel().equals(startLabel)) {
        v.set_Label(endLabel);
        return true;
      }
    }
    return false;
  }

  /**
   * 修改顶点的属性-endIP
   * 
   * @param 图graph，原顶点s_label，现顶点属性-endIP
   * 
   * @return 是否成功修改
   */
  public boolean setVertexIP(NetworkTopology graph, String startLabel, String endIP) {
    for (Vertex v : graph.vertices()) {
      if (v.getLabel().equals(startLabel)) {
        String[] args = v.get_VertexInfo();
        args[0] = endIP;
        v.fillVertexInfo(args);
        return true;
      }
    }
    return false;
  }

  /**
   * 修改边的label
   * 
   * @param 图graph，原边s_label，现边e_label
   * 
   * @return 是否成功修改
   */
  public boolean setEdgeLabel(NetworkTopology graph, String startLabel, String endLabel) {
    
    for (Edge e : graph.edges()) {
      if (e.get_label().equals(startLabel)) {
        e.set_label(endLabel);
        return true;
      }
    }
    return false;
  }

  /**
   * 修改边的权重
   * 
   * @param 图graph，原边s_label，现边权重e_weight）
   * 
   * @return 是否成功修改
   */
  public boolean setEdgeWeight(NetworkTopology graph, String startLabel, double endWeight) {
    double wa = 0;
    for (Edge e : graph.edges()) {
      if (e.get_label().equals(startLabel)) {
        wa = e.get_weight();
        e.set_weight(endWeight);
        return true;
      }
    }
    for (Edge e : graph.edges()) {
      if (!e.get_label().equals(startLabel)) {
        double wc = e.get_weight();
        e.set_weight((wc * (1 - endWeight)) / (1 - wa));
      }
    }
    return false;
  }

  /**
   * 修改边方向
   * 
   * @param 图graph，边的标签s_label
   * 
   * @return 是否成功修改
   */
  public boolean setEdgePoint(NetworkTopology graph, String startLabel) {
    for (Edge e : graph.edges()) {
      if (e.get_label().equals(startLabel)) {
        e.set_point();
        return true;
      }
    }
    return false;
  }

  /*
   * 获取字符串的第一个按空格分割的第一个字符串
   * 
   * @param 待处理的字符串
   * 
   * @return 返回按空格分割的第一个字符串
   */
  public static String first_String(String s) {
    String[] a = s.split(" ");
    return a[0];
  }

  /*
   * 获取字符串的第二个按空格分割的第一个字符串
   * 
   * @param 待处理的字符串
   * 
   * @return 返回按空格分割的第二个字符串
   */
  public static String secondandthird_String(String s) {
    String[] a = s.split(" ");
    return a[1] + a[2];
  }

  /**
   * 获取字符串的第二个按空格分割的第一个字符串
   * 
   * @param 待处理的字符串
   * 
   * @return 返回按空格分割的第二个字符串
   */
  public static String firstandsecond_String(String s) {
    String[] a = s.split(" ");
    return a[0] + a[1];
  }

  public static void main(String[] args) throws IOException {

    // String name = GraphPoetFactory.class.getName();
    // Logger log2 = LogFactory.Get_Logger(2,2,name+"o");//记录操作的日志

    long bufferWTime = 0;
    long streamWTime = 0;
    long writeTime = 0;
    long nioWTime = 0;
    long bufferRTime = 0;
    long streamRTime = 0;
    long readTime = 0;
    long nioRTime = 0;
    NetworkTopologyApp app = new NetworkTopologyApp();
    NetworkTopology graph = new NetworkTopology();
    int ch;
    while (true) {
      String name1 = NetworkTopologyFactory.class.getName();
      Logger log1 = LogFactory.getLogger(1, 2, name1 + "e");// 记录异常/错误的日志
      ch = app.menu();
      switch (ch) {
        case 1:
          Scanner in = new Scanner(System.in);
          System.out.println("请输入文件路径：");
          String path = in.nextLine();
          log1.info("读取文件： " + path + "！");
          LogFactory.close();
          // log2.info("读取文件："+path+"！");
          System.out.println("-----读取文件的方式-----" + "\n" + "使用Stream：Stream" + "\n"
              + "使用Reader：Reader" + "\n" + "使用Buffer：Buffer" + "\n" + "使用Nio：Nio" + "\n");
          System.out.println("请输入读取文件的方式：");
          String choice7 = in.nextLine();
          if (choice7.equals("Stream")) {
            UseStreamR stream = new UseStreamR(path);
            graph = new ContextIO().ReadFromFileToNetworkTopology(stream);
            streamRTime = stream.getTime();
          } else if (choice7.equals("Reader")) {
            UseReaderR reader = new UseReaderR(path);
            graph = new ContextIO().ReadFromFileToNetworkTopology(reader);
            readTime = reader.getTime();
            System.err.println(readTime);
          } else if (choice7.equals("Buffer")) {
            UseBufferR buffer = new UseBufferR(path);
            graph = new ContextIO().ReadFromFileToNetworkTopology(buffer);
            bufferRTime = buffer.getTime();
          } else if (choice7.equals("Nio")) {
            UseNioR nio = new UseNioR(path);
            graph = new ContextIO().ReadFromFileToNetworkTopology(nio);
            nioRTime = nio.getTime();
          }      
          break;
        case 2:
          log1.info("打印图的信息！");
          LogFactory.close();
          // log2.info("打印图的信息！");
          System.out.println(graph.get_GraphName());
          System.out.println(graph.get_GraphType());
          System.out.println(graph.get_VertexType());
          System.out.println(graph.get_EdgeType());
          System.out.println(graph.toString());
          break;
        case 3:
          log1.info("可视化图结构！");
          LogFactory.close();
          // log2.info("可视化图结构！");
          UndirectedSparseGraph<Vertex, Edge> g = new UndirectedSparseGraph<>();
          GraphVisualizationHelper.visualize(graph, g);
          break;
        case 4:
          Scanner in1 = new Scanner(System.in);
          System.out.println("-----请输入你要对点进行的操作-----" + "\n" + "增加节点--add_vertex" + "\n"
              + "删除节点--delate_vertex" + "\n" + "修改节点--change_vertex" + "\n");
          String choice = in1.nextLine();
          if (choice.equals("add_vertex")) {
            System.out.println("请输入所要加入的节点信息：");
            System.out.println("类型：");
            String type = in1.nextLine();
            System.out.println("标签：");
            String label = in1.nextLine();
            System.out.println("输入属性：endIP 地址（字符串，用“.”分割为四部分，每部分的取值范围为[0,255]）");
            String[] argus = in1.nextLine().split(" ");
            Vertex v = null;
            if (type.equals("Computer")) {
              v = new Computer(label);
              v.fillVertexInfo(argus);
            } else if (type.equals("Server")) {
              v = new Server(label);
              v.fillVertexInfo(argus);
            } else if (type.equals("Router")) {
              v = new Router(label);
              v.fillVertexInfo(argus);
            }
            if (graph.addVertex(v)) {
              log1.info("添加了节点： " + v.getLabel());
              LogFactory.close();
              // log2.info("添加了节点："+v.getLabel());
              System.out.println("加入点成功！");
            } else {
              System.out.println("加入失败，该点已经存在！");
            }
          } else if (choice.equals("delate_vertex")) {
            System.out.println("请输入所要删除的节点信息：");
            System.out.println("类型：");
            String type = in1.nextLine();
            System.out.println("标签：");
            String label = in1.nextLine();
            int flag = 0;
            for (Vertex v : graph.vertices()) {
              if (v.getLabel().equals(label)) {
                if (graph.removeVertex(v)) {
                  log1.info("删除了节点： " + v.getLabel());
                  LogFactory.close();
                  // log2.info("删除了节点："+v.getLabel());
                  System.out.println("删除该点成功！");
                  flag = 1;
                  break;
                } else {
                  System.out.println("删除失败，该点不存在！");
                  break;
                }
              }
            }
            if (flag == 0) {
              System.out.println("删除失败，该点不存在！");
            }
          } else if (choice.equals("change_vertex")) {
            System.out.println("请输入所要修改的节点信息：");
            System.out.println("标签：");
            String label = in1.nextLine();
            System.out.println("输入要修改该节点的哪项信息：标签--label，endIP");
            String imformation = in1.nextLine();
            System.out.println("输入修改信息的内容：");
            String reLabel = in1.nextLine();
            if (imformation.equals("label")) {
              app.setVertexLabel(graph, label, reLabel);
              // log2.info("修改了点："+label+"的标签。"+"由："+label+"变为："+reLabel);
              log1.info("修改了点： " + label + "的标签。" + "由：" + label + "变为：" + reLabel);
              LogFactory.close();
            } else if (imformation.equals("endIP")) {
              app.setVertexIP(graph, label, reLabel);
              // log2.info("修改了点："+label+"的IP。"+"由："+label+"变为："+reLabel);
              log1.info("修改了点： " + label + "的IP。" + "由：" + label + "变为：" + reLabel);
              LogFactory.close();
            }
          }
          break;
        case 5:
          Scanner in2 = new Scanner(System.in);
          System.out.println("-----请输入你要对边进行的操作-----" + "\n" + "增加边：add_edge" + "\n"
              + "删除边：delate_edge" + "\n" + "修改边：change_edge" + "\n");
          String choice2 = in2.nextLine();
          if (choice2.equals("add_edge")) {
            System.out.println("请输入所要加入的边信息：");
            System.out.println("第一个点（有向图默认为起点）的标签：");
            String startLabel = in2.nextLine();
            System.out.println("第二个点（有向图默认为终点）的标签：");
            String endLabel = in2.nextLine();
            Vertex source = null;
            Vertex target = null;
            List<Vertex> list = new ArrayList<>();
            for (Vertex v : graph.vertices()) {
              if (v.getLabel().equals(startLabel)) {
                source = v;
              }
              if (v.getLabel().equals(endLabel)) {
                target = v;
              }
            }
            if (source.equals(target)) {
              list.add(source);
            } else {
              list.add(target);
              list.add(source);
            }
            Edge e = null;
            System.out.println("标签：");
            String label = in2.nextLine();
            System.out.println("权重：");
            double weight = Double.valueOf(in2.nextLine());
            System.out.println("请输入边的类型：");
            String type = in2.nextLine();
            if (type.equals("NetworkConnection")) {
              e = new NetworkConnection(label, weight, source, target, "NO", list);
            }
            if (graph.addEdge(e)) {
              log1.info("添加了边： " + e.get_label());
              LogFactory.close();
              // log2.info("添加了边："+e.get_label());
              System.out.println("加入边成功！");
            } else {
              System.out.println("加入失败，起点或者终点不存在！");
            }
          } else if (choice2.equals("delate_edge")) {
            System.out.println("请输入所要删除的边信息：");
            System.out.println("标签：");
            String label = in2.nextLine();
            int flag = 0;
            for (Edge e : graph.edges()) {
              if (e.get_label().equals(label)) {
                if (graph.removeEdge(e)) {
                  log1.info("删除了边： " + e.get_label());
                  LogFactory.close();
                  // log2.info("删除了边："+e.get_label());
                  System.out.println("删除该边成功！");
                  flag = 1;
                  break;
                } else {
                  System.out.println("删除失败，该边不存在！");
                  break;
                }
              }
            }
            if (flag == 0) {
              System.out.println("删除失败，该边不存在！");
            }
          } else if (choice2.equals("change_edge")) {
            System.out.println("请输入所要修改的边信息：");
            System.out.println("标签：");
            String label = in2.nextLine();
            System.out.println("-----请输入你要改变的信息-----" + "\n" + "标签：label" + "\n" + "权重：weight"
                + "\n" + "方向：point" + "\n");
            String reImformation = in2.nextLine();
            if (reImformation.equals("label")) {
              System.out.println("输入修改信息的内容：");
              String reLabel = in2.nextLine();
              log1.info("修改了边： " + label + "的标签。" + "由：" + label + "变为：" + reLabel);
              LogFactory.close();
              // log2.info("修改了边："+label+"的标签。"+"由："+label+"变为："+reLabel);
              app.setEdgeLabel(graph, label, reLabel);
            } else if (reImformation.equals("weight")) {
              System.out.println("输入修改信息的内容：");
              double reWeight = in2.nextDouble();
              log1.info("修改了边： " + label + "的权重。" + "由：" + label + "变为：" + reWeight);
              LogFactory.close();
              // log2.info("修改了边："+label+"的权重。"+"由："+label+"变为："+reWeight);
              app.setEdgeWeight(graph, label, reWeight);
            } else if (reImformation.equals("point")) {
              // log2.info("修改了边："+label+"的方向。");
              log1.info("修改了边： " + label + "的方向。");
              LogFactory.close();
              app.setEdgePoint(graph, label);
            }
          }
          break;
        case 6:
          ContextDegree context = new ContextDegree();
          System.out.println("请输入顶点的标签：");
          Scanner in3 = new Scanner(System.in);
          String label = in3.nextLine();
          for (Vertex v : graph.vertices()) {
            if (v.getLabel().equals(label)) {
              log1.info("查看了顶点： " + v.getLabel() + "的度。");
              LogFactory.close();
              // log2.info("查看了顶点："+v.getLabel()+"的度。");
              System.out.println(
                  "该顶点的点度中心性：" + context.calculate_degree(new CalculateDegreeCentrality(graph, v)));
              System.out.println("该顶点的接近中心性："
                  + context.calculate_degree(new CalculateClosenessCentrality(graph, v)));
              System.out.println("该顶点的中介中心性："
                  + context.calculate_degree(new CalculateBetweennessCentrality(graph, v)));
              System.out.println("该顶点的偏心度：" + GraphMetrics.eccentricity(graph, v));
              System.out.println("该顶点的入度：" + GraphMetrics.inDegreeCentrality(graph, v));
              System.out.println("该顶点的出度：" + GraphMetrics.outDegreeCentrality(graph, v));
              break;
            }
          }
          break;
        case 7:
          log1.info("查看了图： " + graph.get_GraphName() + "的度。");
          LogFactory.close();
          // log2.info("查看了图："+graph.get_GraphName()+"的度。");
          System.out.println("该图的中心度：" + GraphMetrics.degreeCentrality(graph));
          System.out.println("该图的半径：" + GraphMetrics.radius(graph));
          System.out.println("该图的直径：" + GraphMetrics.diameter(graph));
          break;
        case 8:
          Scanner in8 = new Scanner(System.in);
          System.out.println("请输入起点标签：");
          String startLabel = in8.nextLine();
          System.out.println("请输入终点标签：");
          String endLabel = in8.nextLine();
          Vertex s = null;
          Vertex e = null;
          for (Vertex v : graph.vertices()) {
            if (v.getLabel().equals(startLabel)) {
              s = v;
            }
            if (v.getLabel().equals(endLabel)) {
              e = v;
            }
          }
          // log2.info("查看了点："+startLabel+"到点："+endLabel+"的最短路径。");
          log1.info("查看了点： " + startLabel + "到点：" + endLabel + "的最短路径。");
          LogFactory.close();
          System.out.println(
              startLabel + "到" + endLabel + "的最短路径为：" + GraphMetrics.distance(graph, s, e));
          break;
        case 9:
          System.out.println("-----请输入基于语法的图操作指令-----" + "\n" + "加入顶点：vertex --add label type"
              + "\n" + "删除顶点：vertex --delete regex" + "\n"
              + "加入边：edge --add label type [weighted=Y|N] [weight] [directed=Y|N] v1, v2" + "\n"
              + "删除边：edge --delete regex" + "\n"
              + "添加超边：hyperedge --add label type vertex1, ...,vertexn" + "\n");
          Scanner in9 = new Scanner(System.in);
          System.out.println("请输入指令：");
          String startLabel1 = in9.nextLine();
          ParseCommandHelper.parseAndExecuteCommand(graph, startLabel1);
          LogFactory.close();
          break;
        case 10:
          Scanner in4 = new Scanner(System.in);
          System.out.println("-----请输入你的过滤日志的条件-----" + "\n" + "时间段：time_between" + "\n"
              + "异常/错误类型：type_wrong" + "\n" + "异常/错误发生的类：class_wrong" + "\n"
              + "异常/错误发生的方法：method_wrong" + "\n" + "对图的操作：operator" + "\n");
          System.out.println("请输入你的过滤条件：");
          String choice1 = in4.nextLine();
          if (choice1.equals("time_between")) {
            System.out.println("请输入起始时间：(格式：xxxx-xx-xx xx:xx:xx)");
            String beginTime = in4.nextLine();
            System.out.println("请输入终止时间：(格式：xxxx-xx-xx xx:xx:xx)");
            String endTime = in4.nextLine();
            try (BufferedReader reader =
                new BufferedReader(new FileReader("src/graph/factory.GraphPoetFactorye_e.txt"))) {
              String ss = null;
              while ((ss = reader.readLine()) != null) {
                if (first_String(ss).equals("操作的时间：")) {
                  if (Long.valueOf(beginTime.replaceAll("[-\\s:]", "")) < Long
                      .valueOf(secondandthird_String(ss).replaceAll("[-\\s:]", ""))
                      && Long.valueOf(endTime.replaceAll("[-\\s:]", "")) > Long
                          .valueOf(secondandthird_String(ss).replaceAll("[-\\s:]", ""))) {
                    System.out.println(ss);
                    System.out.println(reader.readLine());
                    System.out.println(reader.readLine());
                  }
                }
              }
            } catch (IOException e1) {
              e1.printStackTrace();
            }
          } else if (choice1.equals("type_wrong")) {
            System.out.println("-----请输入错误/异常的类型-----" + "\n" + "文件异常：file_exception" + "\n"
                + "命令异常：command_exception" + "\n");
            System.out.println("请输入你的错误/异常的类型：");
            String error = in4.nextLine();

            try (BufferedReader reader =
                new BufferedReader(new FileReader("src/graph/factory.GraphPoetFactorye_e.txt"))) {
              String ss = null;
              String[] exFile = new String[9];
              String[] exCommand = new String[8];
              while ((ss = reader.readLine()) != null) {
                if (first_String(ss).equals("发生文件错误的时间：")) {
                  exFile[0] = ss;
                  for (int i = 1; i < 9; i++) {
                    exFile[i] = reader.readLine();
                  }
                  if (exFile[5].equals("发生错误的类型： 文件错误！") && error.equals("file_exception")) {
                    for (int i = 0; i < 9; i++) {
                      System.out.println(exFile[i]);
                    }
                  }
                } else if (first_String(ss).equals("发生图操作指令错误的的时间：")) {
                  exCommand[0] = ss;
                  for (int i = 1; i < 8; i++) {
                    exCommand[i] = reader.readLine();
                  }
                  if (exFile[4].equals("发生错误的类型： 图的语法解析操作错误！")
                      && error.equals("command_exception")) {
                    for (int i = 0; i < 8; i++) {
                      System.out.println(exCommand[i]);
                    }
                  }
                }
              }
            } catch (IOException e1) {
              e1.printStackTrace();
            }

          } else if (choice1.equals("class_wrong")) {

            System.out.println("-----请输入错误的类名-----" + "\n" + "命令异常：helper.ParseCommandHelper" + "\n"
                + "文件异常：factory.GraphPoetFactory" + "\n");
            System.out.println("请输入错误的类名：");
            String error = in4.nextLine();

            try (BufferedReader reader =
                new BufferedReader(new FileReader("src/graph/factory.GraphPoetFactorye_e.txt"))) {
              String ss = null;
              String[] exFile = new String[9];
              String[] exCommand = new String[8];
              while ((ss = reader.readLine()) != null) {
                if (first_String(ss).equals("发生文件错误的时间：")) {
                  exFile[0] = ss;
                  for (int i = 1; i < 9; i++) {
                    exFile[i] = reader.readLine();
                  }
                  if (exFile[3].equals("发生错误的类名： " + error)) {
                    for (int i = 0; i < 9; i++) {
                      System.out.println(exFile[i]);
                    }
                  }
                } else if (first_String(ss).equals("发生图操作指令错误的的时间：")) {
                  exCommand[0] = ss;
                  for (int i = 1; i < 8; i++) {
                    exCommand[i] = reader.readLine();
                  }
                  if (exFile[2].equals("发生错误的类名： " + error)) {
                    for (int i = 0; i < 8; i++) {
                      System.out.println(exCommand[i]);
                    }
                  }
                }
              }
            } catch (IOException e1) {
              e1.printStackTrace();
            }

          } else if (choice1.equals("method_wrong")) {
            System.out.println("-----请输入错误的方法名-----" + "\n" + "命令异常：createGraph" + "\n"
                + "文件异常：parseAndExecuteCommand" + "\n");
            System.out.println("请输入错误的方法名：");
            String error = in4.nextLine();

            try (BufferedReader reader =
                new BufferedReader(new FileReader("src/graph/factory.GraphPoetFactorye_e.txt"))) {
              String ss = null;
              String[] exFile = new String[9];
              String[] exCommand = new String[8];
              while ((ss = reader.readLine()) != null) {
                if (first_String(ss).equals("发生文件错误的时间：")) {
                  exFile[0] = ss;
                  for (int i = 1; i < 9; i++) {
                    exFile[i] = reader.readLine();
                  }
                  if (exFile[4].equals("发生错误的方法名： " + error)) {
                    for (int i = 0; i < 9; i++) {
                      System.out.println(exFile[i]);
                    }
                  }
                } else if (first_String(ss).equals("发生图操作指令错误的的时间：")) {
                  exCommand[0] = ss;
                  for (int i = 1; i < 8; i++) {
                    exCommand[i] = reader.readLine();
                  }
                  if (exFile[3].equals("发生错误的方法名： " + error)) {
                    for (int i = 0; i < 8; i++) {
                      System.out.println(exCommand[i]);
                    }
                  }
                }
              }
            } catch (IOException e1) {
              e1.printStackTrace();
            }

          } else if (choice1.equals("operator")) {
            System.out.println("-----请输入操作的名称-----" + "\n" + "读取文件：" + "\n" + "打印图的信息！" + "\n"
                + "可视化图结构！" + "\n" + "添加了节点：" + "\n" + "删除了节点：" + "\n" + "修改了点：" + "\n" + "添加了边："
                + "\n" + "删除了边：" + "\n" + "修改了边：" + "\n" + "查看了顶点：" + "\n" + "查看了图：" + "\n"
                + "使用图语句添加了节点：" + "\n" + "使用图语句添加了边：" + "\n" + "使用图语句添加了超边：" + "\n" + "使用图语句删除了顶点："
                + "\n" + "使用图语句删除了边：" + "\n");
            System.out.println("请输入操作名：");
            String error = in4.nextLine();

            try (BufferedReader reader =
                new BufferedReader(new FileReader("src/graph/factory.GraphPoetFactorye_e.txt"))) {
              String ss = null;
              String[] operator = new String[2];
              while ((ss = reader.readLine()) != null) {
                if (first_String(ss).equals("操作的时间：")) {
                  operator[0] = ss;
                  for (int i = 1; i < 3; i++) {
                    operator[i] = reader.readLine();
                  }
                  System.err.println(operator[1]);
                  System.err.println(firstandsecond_String(operator[1]));
                  if (firstandsecond_String(operator[1]).equals("操作的内容：" + error)) {
                    for (int i = 0; i < 3; i++) {
                      System.out.println(operator[i]);
                    }
                  }
                }
              }
            } catch (IOException e1) {
              e1.printStackTrace();
            }
          }
          break;
        case 11:
          Scanner in5 = new Scanner(System.in);
          System.out.println("-----文件写入的方式-----" + "\n" + "使用Stream：Stream" + "\n"
              + "使用Writer：Writer" + "\n" + "使用Buffer：Buffer" + "\n" + "使用Nio：Nio" + "\n");
          System.out.println("请输入像文件写入的方式：");
          String choice3 = in5.nextLine();
          if (choice3.equals("Stream")) {
            UseStreamW stream=new UseStreamW(graph, "src/graph/W_data_Topology.txt");
            new ContextIO().WriteInToFile(stream);
            streamWTime = stream.getTime();
          } else if (choice3.equals("Writer")) {
            UseWriterW write=new UseWriterW(graph, "src/graph/W_data_Topology.txt");
            new ContextIO().WriteInToFile(write);
            writeTime = write.getTime();
          } else if (choice3.equals("Buffer")) {
            UseBufferW buffer=new UseBufferW(graph, "src/graph/W_data_Topology.txt");
            new ContextIO().WriteInToFile(buffer);
            bufferWTime = buffer.getTime();
          } else if (choice3.equals("Nio")) {
            UseNioW nio=new UseNioW(graph, "src/graph/W_data_Topology.txt");
            new ContextIO().WriteInToFile(nio);
            nioWTime = nio.getTime();
          }
          break;
        case 12:
          System.out.println(readTime);
          JFreeChart J = JFreeChartOfIO.getJFreeChart("file1.txt",bufferRTime, readTime, streamRTime, nioRTime,
              bufferWTime, writeTime, streamWTime, nioWTime);
          JFreeChartOfIO.updateFont(J);
          FileOutputStream out = null;
          try {
            out = new FileOutputStream("F:\\eclipse-workspace\\Lab5-1160300610\\src\\graph\\3.jpg");
            ChartUtilities.writeChartAsJPEG(out, 0.5f, J, 800, 600, null);
          } finally {
            try {
              out.close();
            } catch (Exception ex) {
              ex.printStackTrace();
            }
          }
          break;
        case 13:
          log1.info("结束了程序！");
          LogFactory.close();
          // log2.info("结束了程序！");
          System.out.println("Bye~~");
          System.exit(0);
        default:
          System.out.println("服务序号输入错误，请重新输入！");
          LogFactory.close();
      }

    }
  }
}
