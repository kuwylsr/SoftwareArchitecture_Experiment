package factory;

import edge.CommentTie;
import edge.Edge;
import edge.ForwardTie;
import edge.FriendTie;
import edge.NetworkConnection;
import edge.WordNeighborhood;
import graph.Graph;
import graph.NetworkTopology;
import graph.SocialNetwork;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vertex.Computer;
import vertex.Person;
import vertex.Router;
import vertex.Server;
import vertex.Vertex;
import vertex.WirelessRouter;
import vertex.Word;

public class SocialNetworkFactory extends GraphFactory {


  private long begin = 0; // 开始时间
  private long end = 0; // 结束时间

  @Override
  public SocialNetwork createGraphUseBuffer(String filePath) {

    String name = SocialNetworkFactory.class.getName();
    Logger log1 = LogFactory.getLogger(1, 1, name + "e");// 记录异常/错误的日志

    SocialNetwork graph = new SocialNetwork();
    Map<String, Integer> vertexMap = new HashMap<>();
    Map<String, Integer> edgeMap = new HashMap<>();
    int numH = 0;
    // 使用 Try-with-Resources 在try模块运行完成后，自动关闭文件流
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String s = null;
      begin = System.currentTimeMillis();
      while ((s = reader.readLine()) != null) {
        numH++;
        if (numH % 1000 == 0) {
          System.out.println(numH);
        }
        String first = first_String(s.trim());
        String patternYH = pattern(filePath, s.trim());
        if (first.equals("GraphType")) {
          if (graph.getClass().getName().equals("graph." + patternYH)) { // 判断图的类型是否匹配
            graph.set_GraphType(patternYH);
          } else {
            throw new FileNotFormatReException(filePath, s.trim(), "文件错误！", "图的类型不匹配！",
                "重新读入新的文件！");
          }


        } else if (first.equals("GraphName")) {
          graph.set_GraphName(patternYH);


        } else if (first.equals("VertexType")) {
          if (patternYH.equals("Person")) { // 判断顶点类型是否匹配
            graph.set_VertexType(patternYH);
          } else {
            throw new FileNotFormatReException(filePath, s.trim(), "文件错误！", "顶点的类型不匹配！",
                "重新读入新的文件！");
          }


        } else if (first.equals("Vertex")) {
          String[] v = patternYH.split(" ");
//          try { // 判断点是否已经存在图中
//            for (Vertex x : graph.vertices()) {
//              if (x.getLabel().equals(v[0])) {
//                throw new FileNotFormatException(filePath, line, "文件错误！", "图中顶点Label重复！",
//                    "系统自动处理！");
//              }
//            }
//            vertexMap.put(v[0], 1);
//          } catch (FileNotFormatException e) {
//            int num = vertexMap.get(v[0]);// 多个节点的Label重复
//            vertexMap.put(v[0], num + 1);
//            Vertex newV = new Word(v[0] + num);
//            graph.addVertex(newV);
//            System.out.println(e.getMessage());
//            System.out.println("错误文件路径：" + e.get_file_name());
//            System.out.println("错误文件内容：" + e.get_content());
//            log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                + e.get_con_type_exception() + "\t" + "将顶点：" + v[0] + "变为顶点：" + newV.getLabel());
//            continue;
//          }
          if (v[1].equals("Person")) {
//            if (v.length != 4) { // 判断点的信息是否完整
//              throw new FileNotFormatReException(filePath, line, "文件错误！", "点的信息不完整（属性问题）！",
//                  "重新读入新的文件！");
//            }
            Vertex vertex = new Person(v[0]);
            vertex.fillVertexInfo(argus(v));
            graph.addVertex(vertex);
          }


        } else if (first.equals("EdgeType")) {
//          if (patternYH.equals("CommentTie ForwardTie FriendTie")) { // 判断边的类型是否匹配
            graph.set_EdgeType(patternYH);
//          } else {
//            throw new FileNotFormatReException(filePath, line, "文件错误！", "边的类型不匹配！",
//                "重新读入新的文件！");
//          }


        } else if (first.equals("Edge")) {
          String[] v = patternYH.split(" ");
          Vertex x1 = label_v(graph, v[3]);
          Vertex x2 = label_v(graph, v[4]);
//          for (int j = 3; j < v.length - 1; j++) {
//            if (label_v(graph, v[j]) == null) { // 判断边中的顶点是否被定义
//              throw new FileNotFormatReException(filePath, line, "文件错误！", "边中的顶点未定义！",
//                  "重新读入新的文件！");
//            }
//          }
//          try { // 判断该边是不是Loop
//            if (x1.equals(x2)) {
//              throw new FileNotFormatException(filePath, line, "文件错误！", "该图中不存在Loop！",
//                  "系统自动处理！");
//            }
//          } catch (FileNotFormatException e) {
//            System.out.println(e.getMessage());
//            System.out.println("错误文件路径：" + e.get_file_name());
//            System.out.println("错误文件内容：" + e.get_content());
//            log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                + e.get_con_type_exception() + "\t" + "直接忽略这种非法边！");
//            continue;
//          }
//          try {
//            for (Edge x : graph.edges()) {
//              if (x.get_label().equals(v[0])) { // 判断边是否在图中已经存在
//                throw new FileNotFormatException(filePath, line, "文件错误！", "图中边的Label重复！",
//                    "系统自动处理！");
//              }
//            }
//            edgeMap.put(v[0], 1);
//          } catch (FileNotFormatException e) {
//            int num = edgeMap.get(v[0]);// 多个边的Label重复
//            edgeMap.put(v[0], num + 1);
//            List<Vertex> vertices = new ArrayList<>();
//            for (int i = 3; i < v.length - 1; i++) {
//              vertices.add(label_v(graph, v[i]));
//            }
//            Edge newE = new WordNeighborhood(v[0] + num, Double.valueOf(v[2]), x1,
//                x2, v[5], vertices);
//            graph.addEdge(newE);
//            System.out.println(e.getMessage());
//            System.out.println("错误文件路径：" + e.get_file_name());
//            System.out.println("错误文件内容：" + e.get_content());
//            log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                + e.get_con_type_exception() + "\t" + "将边：" + v[0] + "变为边：" + newE.get_label());
//            continue;
//          }
//          if (!v[5].equals("Yes")) { // 判断边的方向格式是否正确
//            throw new FileNotFormatReException(filePath, line, "文件错误！", "边的方向叙述错误！",
//                "重新读入新的文件！");
//          }
//          if (Double.valueOf(v[2]) <= 0 || Double.valueOf(v[2]) > 1) { // 判断边的权重是否符合要求
//            throw new FileNotFormatReException(filePath, line, "文件错误！", "该边的权重不符合要求！",
//                "重新读入新的文件！");
//          }
          if (v[1].equals("FriendTie")) {
            List<Vertex> vertices = new ArrayList<>();
            vertices.add(x1);
            vertices.add(x2);

            Edge e = new FriendTie(v[0], Double.valueOf(v[2]), x1,
                x2, v[5], vertices);
            graph.addEdge(e);
          } else if (v[1].equals("ForwardTie")) {
            if ((graph instanceof SocialNetwork)) {
              List<Vertex> vertices = new ArrayList<>();
              vertices.add(x1);
              vertices.add(x2);
              Edge e = new ForwardTie(v[0], Double.valueOf(v[2]), x1,
                  x2, v[5], vertices);
              graph.addEdge(e);
            }
          } else if (v[1].equals("CommentTie")) {
            List<Vertex> vertices = new ArrayList<>();
            vertices.add(x1);
            vertices.add(x2);
            Edge e = new CommentTie(v[0], Double.valueOf(v[2]), x1,
                x2, v[5], vertices);
            graph.addEdge(e);
          }
        } else if (first.equals("HyperEdge")) {
//          try {
//            throw new FileNotFormatException(filePath, line, "文件错误！", "该图不能存在超边！", "系统自动处理！");
//          } catch (FileNotFormatException e) {
//            System.out.println(e.getMessage());
//            System.out.println("错误文件路径：" + e.get_file_name());
//            System.out.println("错误文件内容：" + e.get_content());
//            log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                + e.get_con_type_exception() + "\t" + "直接忽略这种非法边！");
//          }
        }
      }
      end = System.currentTimeMillis();
      System.out.println("InputStreamReader执行耗时:" + (end - begin) + " 毫秒");
      return graph;
    } catch (IOException e) {
      log1.info(filePath + "\t" + "空" + "\t" + "文件错误！" + "\t" + "输入的文件路径错误" + "\t" + "重新读入新的文件！");
      LogFactory.close();
      System.out.println(e.getMessage());
      System.out.println("请重新输入文件名称：");
      Scanner in = new Scanner(System.in);

      String path = in.nextLine();
      String name1 = SocialNetworkFactory.class.getName();
      Logger log11 = LogFactory.getLogger(1, 2, name1 + "e");// 记录异常/错误的日志
      log11.info("读取文件： " + path + "！");
      LogFactory.close();

      return createGraphUseBuffer(path);
    } catch (FileNotFormatReException e) {
      log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
          + e.get_con_type_exception() + "\t" + e.get_result_handle());
      LogFactory.close();
      System.out.println(e.getMessage());
      System.out.println("错误文件路径：" + e.get_file_name());
      System.out.println("错误文件内容：" + e.get_content());
      System.out.println("请重新输入文件名称：");
      Scanner in = new Scanner(System.in);

      String path = in.nextLine();
      String name1 = SocialNetworkFactory.class.getName();
      Logger log11 = LogFactory.getLogger(1, 2, name1 + "e");// 记录异常/错误的日志
      log11.info("读取文件： " + path + "！");
      LogFactory.close();

      return createGraphUseBuffer(path);
    } finally {
      LogFactory.close();
    }
  }

  @Override
  public SocialNetwork createGraphUseReader(String filePath) {
    String name = SocialNetworkFactory.class.getName();
    Logger log1 = LogFactory.getLogger(1, 1, name + "e");// 记录异常/错误的日志

    SocialNetwork graph = new SocialNetwork();
    Map<String, Integer> vertexMap = new HashMap<>();
    Map<String, Integer> edgeMap = new HashMap<>();
    int numH = 0;
    // 使用 Try-with-Resources 在try模块运行完成后，自动关闭文件流
    try (Reader r = new InputStreamReader(new FileInputStream(filePath))) {
      begin = System.currentTimeMillis();
      // 一次读一个字符
      int tempchar;
      String s = " ";
      while (true) {
        int ans = (tempchar = r.read());
        // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
        // 但如果这两个字符分开显示时，会换两次行。
        // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
        if (((char) tempchar) != '\r' && ans != -1) {
          s = s + ((char) tempchar);
        }
        if (((char) tempchar) == '\n' || ans == -1) {
          numH++;
          if (numH % 1000 == 0) {
            System.out.println(numH);
          }
          String first = first_String(s.trim());
          String patternYH = pattern(filePath, s.trim());
          if (first.equals("GraphType")) {
            if (graph.getClass().getName().equals("graph." + patternYH)) { // 判断图的类型是否匹配
              graph.set_GraphType(patternYH);
            } else {
              throw new FileNotFormatReException(filePath, s.trim(), "文件错误！", "图的类型不匹配！",
                  "重新读入新的文件！");
            }


          } else if (first.equals("GraphName")) {
            graph.set_GraphName(patternYH);


          } else if (first.equals("VertexType")) {
            if (patternYH.equals("Person")) { // 判断顶点类型是否匹配
              graph.set_VertexType(patternYH);
            } else {
              throw new FileNotFormatReException(filePath, s.trim(), "文件错误！", "顶点的类型不匹配！",
                  "重新读入新的文件！");
            }


          } else if (first.equals("Vertex")) {
            String[] v = patternYH.split(" ");
//            try { // 判断点是否已经存在图中
//              for (Vertex x : graph.vertices()) {
//                if (x.getLabel().equals(v[0])) {
//                  throw new FileNotFormatException(filePath, line, "文件错误！", "图中顶点Label重复！",
//                      "系统自动处理！");
//                }
//              }
//              vertexMap.put(v[0], 1);
//            } catch (FileNotFormatException e) {
//              int num = vertexMap.get(v[0]);// 多个节点的Label重复
//              vertexMap.put(v[0], num + 1);
//              Vertex newV = new Word(v[0] + num);
//              graph.addVertex(newV);
//              System.out.println(e.getMessage());
//              System.out.println("错误文件路径：" + e.get_file_name());
//              System.out.println("错误文件内容：" + e.get_content());
//              log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                  + e.get_con_type_exception() + "\t" + "将顶点：" + v[0] + "变为顶点：" + newV.getLabel());
//              continue;
//            }
            if (v[1].equals("Person")) {
//              if (v.length != 4) { // 判断点的信息是否完整
//                throw new FileNotFormatReException(filePath, line, "文件错误！", "点的信息不完整（属性问题）！",
//                    "重新读入新的文件！");
//              }
              Vertex vertex = new Person(v[0]);
              vertex.fillVertexInfo(argus(v));
              graph.addVertex(vertex);
            }


          } else if (first.equals("EdgeType")) {
//            if (patternYH.equals("CommentTie ForwardTie FriendTie")) { // 判断边的类型是否匹配
              graph.set_EdgeType(patternYH);
//            } else {
//              throw new FileNotFormatReException(filePath, line, "文件错误！", "边的类型不匹配！",
//                  "重新读入新的文件！");
//            }


          } else if (first.equals("Edge")) {
            String[] v = patternYH.split(" ");
            Vertex x1 = label_v(graph, v[3]);
            Vertex x2 = label_v(graph, v[4]);
//            for (int j = 3; j < v.length - 1; j++) {
//              if (label_v(graph, v[j]) == null) { // 判断边中的顶点是否被定义
//                throw new FileNotFormatReException(filePath, line, "文件错误！", "边中的顶点未定义！",
//                    "重新读入新的文件！");
//              }
//            }
//            try { // 判断该边是不是Loop
//              if (x1.equals(x2)) {
//                throw new FileNotFormatException(filePath, line, "文件错误！", "该图中不存在Loop！",
//                    "系统自动处理！");
//              }
//            } catch (FileNotFormatException e) {
//              System.out.println(e.getMessage());
//              System.out.println("错误文件路径：" + e.get_file_name());
//              System.out.println("错误文件内容：" + e.get_content());
//              log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                  + e.get_con_type_exception() + "\t" + "直接忽略这种非法边！");
//              continue;
//            }
//            try {
//              for (Edge x : graph.edges()) {
//                if (x.get_label().equals(v[0])) { // 判断边是否在图中已经存在
//                  throw new FileNotFormatException(filePath, line, "文件错误！", "图中边的Label重复！",
//                      "系统自动处理！");
//                }
//              }
//              edgeMap.put(v[0], 1);
//            } catch (FileNotFormatException e) {
//              int num = edgeMap.get(v[0]);// 多个边的Label重复
//              edgeMap.put(v[0], num + 1);
//              List<Vertex> vertices = new ArrayList<>();
//              for (int i = 3; i < v.length - 1; i++) {
//                vertices.add(label_v(graph, v[i]));
//              }
//              Edge newE = new WordNeighborhood(v[0] + num, Double.valueOf(v[2]), x1,
//                  x2, v[5], vertices);
//              graph.addEdge(newE);
//              System.out.println(e.getMessage());
//              System.out.println("错误文件路径：" + e.get_file_name());
//              System.out.println("错误文件内容：" + e.get_content());
//              log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                  + e.get_con_type_exception() + "\t" + "将边：" + v[0] + "变为边：" + newE.get_label());
//              continue;
//            }
//            if (!v[5].equals("Yes")) { // 判断边的方向格式是否正确
//              throw new FileNotFormatReException(filePath, line, "文件错误！", "边的方向叙述错误！",
//                  "重新读入新的文件！");
//            }
//            if (Double.valueOf(v[2]) <= 0 || Double.valueOf(v[2]) > 1) { // 判断边的权重是否符合要求
//              throw new FileNotFormatReException(filePath, line, "文件错误！", "该边的权重不符合要求！",
//                  "重新读入新的文件！");
//            }
            if (v[1].equals("FriendTie")) {
              List<Vertex> vertices = new ArrayList<>();
              vertices.add(x1);
              vertices.add(x2);

              Edge e = new FriendTie(v[0], Double.valueOf(v[2]), x1,
                  x2, v[5], vertices);
              graph.addEdge(e);
            } else if (v[1].equals("ForwardTie")) {
              if ((graph instanceof SocialNetwork)) {
                List<Vertex> vertices = new ArrayList<>();
                vertices.add(x1);
                vertices.add(x2);
                Edge e = new ForwardTie(v[0], Double.valueOf(v[2]), x1,
                    x2, v[5], vertices);
                graph.addEdge(e);
              }
            } else if (v[1].equals("CommentTie")) {
              List<Vertex> vertices = new ArrayList<>();
              vertices.add(x1);
              vertices.add(x2);
              Edge e = new CommentTie(v[0], Double.valueOf(v[2]), x1,
                  x2, v[5], vertices);
              graph.addEdge(e);
            }
          } else if (first.equals("HyperEdge")) {
//            try {
//              throw new FileNotFormatException(filePath, line, "文件错误！", "该图不能存在超边！", "系统自动处理！");
//            } catch (FileNotFormatException e) {
//              System.out.println(e.getMessage());
//              System.out.println("错误文件路径：" + e.get_file_name());
//              System.out.println("错误文件内容：" + e.get_content());
//              log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                  + e.get_con_type_exception() + "\t" + "直接忽略这种非法边！");
//            }
          }
          s = " ";
        }
        if (ans == -1) {
          break;
        }
      }
      end = System.currentTimeMillis();
      System.out.println("InputStreamReader执行耗时:" + (end - begin) + " 毫秒");
      return graph;
    } catch (IOException e) {
      log1.info(filePath + "\t" + "空" + "\t" + "文件错误！" + "\t" + "输入的文件路径错误" + "\t" + "重新读入新的文件！");
      LogFactory.close();
      System.out.println(e.getMessage());
      System.out.println("请重新输入文件名称：");
      Scanner in = new Scanner(System.in);

      String path = in.nextLine();
      String name1 = SocialNetworkFactory.class.getName();
      Logger log11 = LogFactory.getLogger(1, 2, name1 + "e");// 记录异常/错误的日志
      log11.info("读取文件： " + path + "！");
      LogFactory.close();

      return createGraphUseBuffer(path);
    } catch (FileNotFormatReException e) {
      log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
          + e.get_con_type_exception() + "\t" + e.get_result_handle());
      LogFactory.close();
      System.out.println(e.getMessage());
      System.out.println("错误文件路径：" + e.get_file_name());
      System.out.println("错误文件内容：" + e.get_content());
      System.out.println("请重新输入文件名称：");
      Scanner in = new Scanner(System.in);

      String path = in.nextLine();
      String name1 = SocialNetworkFactory.class.getName();
      Logger log11 = LogFactory.getLogger(1, 2, name1 + "e");// 记录异常/错误的日志
      log11.info("读取文件： " + path + "！");
      LogFactory.close();

      return createGraphUseBuffer(path);
    } finally {
      LogFactory.close();
    }
  }

  @Override
  public SocialNetwork createGraphUseStream(String filePath) {
    String name = SocialNetworkFactory.class.getName();
    Logger log1 = LogFactory.getLogger(1, 1, name + "e");// 记录异常/错误的日志

    SocialNetwork graph = new SocialNetwork();
    Map<String, Integer> vertexMap = new HashMap<>();
    Map<String, Integer> edgeMap = new HashMap<>();
    int numH = 0;
    // 使用 Try-with-Resources 在try模块运行完成后，自动关闭文件流
    try (InputStream stm = new FileInputStream(filePath)) {
      begin = System.currentTimeMillis();
      // 一次读一个字符
      int tempchar;
      String s = " ";
      while (true) {
        int ans = (tempchar = stm.read());
        // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
        // 但如果这两个字符分开显示时，会换两次行。
        // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
        if (((char) tempchar) != '\r' && ans != -1) {
          s = s + ((char) tempchar);
        }
        if (((char) tempchar) == '\n' || ans == -1) {
          numH++;
          if (numH % 1000 == 0) {
            System.out.println(numH);
          }
          String first = first_String(s.trim());
          String patternYH = pattern(filePath, s.trim());
          if (first.equals("GraphType")) {
            if (graph.getClass().getName().equals("graph." + patternYH)) { // 判断图的类型是否匹配
              graph.set_GraphType(patternYH);
            } else {
              throw new FileNotFormatReException(filePath, s.trim(), "文件错误！", "图的类型不匹配！",
                  "重新读入新的文件！");
            }


          } else if (first.equals("GraphName")) {
            graph.set_GraphName(patternYH);


          } else if (first.equals("VertexType")) {
            if (patternYH.equals("Person")) { // 判断顶点类型是否匹配
              graph.set_VertexType(patternYH);
            } else {
              throw new FileNotFormatReException(filePath, s.trim(), "文件错误！", "顶点的类型不匹配！",
                  "重新读入新的文件！");
            }


          } else if (first.equals("Vertex")) {
            String[] v = patternYH.split(" ");
//            try { // 判断点是否已经存在图中
//              for (Vertex x : graph.vertices()) {
//                if (x.getLabel().equals(v[0])) {
//                  throw new FileNotFormatException(filePath, line, "文件错误！", "图中顶点Label重复！",
//                      "系统自动处理！");
//                }
//              }
//              vertexMap.put(v[0], 1);
//            } catch (FileNotFormatException e) {
//              int num = vertexMap.get(v[0]);// 多个节点的Label重复
//              vertexMap.put(v[0], num + 1);
//              Vertex newV = new Word(v[0] + num);
//              graph.addVertex(newV);
//              System.out.println(e.getMessage());
//              System.out.println("错误文件路径：" + e.get_file_name());
//              System.out.println("错误文件内容：" + e.get_content());
//              log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                  + e.get_con_type_exception() + "\t" + "将顶点：" + v[0] + "变为顶点：" + newV.getLabel());
//              continue;
//            }
            if (v[1].equals("Person")) {
//              if (v.length != 4) { // 判断点的信息是否完整
//                throw new FileNotFormatReException(filePath, line, "文件错误！", "点的信息不完整（属性问题）！",
//                    "重新读入新的文件！");
//              }
              Vertex vertex = new Person(v[0]);
              vertex.fillVertexInfo(argus(v));
              graph.addVertex(vertex);
            }


          } else if (first.equals("EdgeType")) {
//            if (patternYH.equals("CommentTie ForwardTie FriendTie")) { // 判断边的类型是否匹配
              graph.set_EdgeType(patternYH);
//            } else {
//              throw new FileNotFormatReException(filePath, line, "文件错误！", "边的类型不匹配！",
//                  "重新读入新的文件！");
//            }


          } else if (first.equals("Edge")) {
            String[] v = patternYH.split(" ");
            Vertex x1 = label_v(graph, v[3]);
            Vertex x2 = label_v(graph, v[4]);
//            for (int j = 3; j < v.length - 1; j++) {
//              if (label_v(graph, v[j]) == null) { // 判断边中的顶点是否被定义
//                throw new FileNotFormatReException(filePath, line, "文件错误！", "边中的顶点未定义！",
//                    "重新读入新的文件！");
//              }
//            }
//            try { // 判断该边是不是Loop
//              if (x1.equals(x2)) {
//                throw new FileNotFormatException(filePath, line, "文件错误！", "该图中不存在Loop！",
//                    "系统自动处理！");
//              }
//            } catch (FileNotFormatException e) {
//              System.out.println(e.getMessage());
//              System.out.println("错误文件路径：" + e.get_file_name());
//              System.out.println("错误文件内容：" + e.get_content());
//              log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                  + e.get_con_type_exception() + "\t" + "直接忽略这种非法边！");
//              continue;
//            }
//            try {
//              for (Edge x : graph.edges()) {
//                if (x.get_label().equals(v[0])) { // 判断边是否在图中已经存在
//                  throw new FileNotFormatException(filePath, line, "文件错误！", "图中边的Label重复！",
//                      "系统自动处理！");
//                }
//              }
//              edgeMap.put(v[0], 1);
//            } catch (FileNotFormatException e) {
//              int num = edgeMap.get(v[0]);// 多个边的Label重复
//              edgeMap.put(v[0], num + 1);
//              List<Vertex> vertices = new ArrayList<>();
//              for (int i = 3; i < v.length - 1; i++) {
//                vertices.add(label_v(graph, v[i]));
//              }
//              Edge newE = new WordNeighborhood(v[0] + num, Double.valueOf(v[2]), x1,
//                  x2, v[5], vertices);
//              graph.addEdge(newE);
//              System.out.println(e.getMessage());
//              System.out.println("错误文件路径：" + e.get_file_name());
//              System.out.println("错误文件内容：" + e.get_content());
//              log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                  + e.get_con_type_exception() + "\t" + "将边：" + v[0] + "变为边：" + newE.get_label());
//              continue;
//            }
//            if (!v[5].equals("Yes")) { // 判断边的方向格式是否正确
//              throw new FileNotFormatReException(filePath, line, "文件错误！", "边的方向叙述错误！",
//                  "重新读入新的文件！");
//            }
//            if (Double.valueOf(v[2]) <= 0 || Double.valueOf(v[2]) > 1) { // 判断边的权重是否符合要求
//              throw new FileNotFormatReException(filePath, line, "文件错误！", "该边的权重不符合要求！",
//                  "重新读入新的文件！");
//            }
            if (v[1].equals("FriendTie")) {
              List<Vertex> vertices = new ArrayList<>();
              vertices.add(x1);
              vertices.add(x2);

              Edge e = new FriendTie(v[0], Double.valueOf(v[2]), x1,
                  x2, v[5], vertices);
              graph.addEdge(e);
            } else if (v[1].equals("ForwardTie")) {
              if ((graph instanceof SocialNetwork)) {
                List<Vertex> vertices = new ArrayList<>();
                vertices.add(x1);
                vertices.add(x2);
                Edge e = new ForwardTie(v[0], Double.valueOf(v[2]), x1,
                    x2, v[5], vertices);
                graph.addEdge(e);
              }
            } else if (v[1].equals("CommentTie")) {
              List<Vertex> vertices = new ArrayList<>();
              vertices.add(x1);
              vertices.add(x2);
              Edge e = new CommentTie(v[0], Double.valueOf(v[2]), x1,
                  x2, v[5], vertices);
              graph.addEdge(e);
            }
          } else if (first.equals("HyperEdge")) {
//            try {
//              throw new FileNotFormatException(filePath, line, "文件错误！", "该图不能存在超边！", "系统自动处理！");
//            } catch (FileNotFormatException e) {
//              System.out.println(e.getMessage());
//              System.out.println("错误文件路径：" + e.get_file_name());
//              System.out.println("错误文件内容：" + e.get_content());
//              log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                  + e.get_con_type_exception() + "\t" + "直接忽略这种非法边！");
//            }
          }
          s = " ";
        }
        if (ans == -1) {
          break;
        }
      }
      end = System.currentTimeMillis();
      System.out.println("InputStreamReader执行耗时:" + (end - begin) + " 毫秒");
      return graph;
    } catch (IOException e) {
      log1.info(filePath + "\t" + "空" + "\t" + "文件错误！" + "\t" + "输入的文件路径错误" + "\t" + "重新读入新的文件！");
      LogFactory.close();
      System.out.println(e.getMessage());
      System.out.println("请重新输入文件名称：");
      Scanner in = new Scanner(System.in);

      String path = in.nextLine();
      String name1 = SocialNetworkFactory.class.getName();
      Logger log11 = LogFactory.getLogger(1, 2, name1 + "e");// 记录异常/错误的日志
      log11.info("读取文件： " + path + "！");
      LogFactory.close();

      return createGraphUseBuffer(path);
    } catch (FileNotFormatReException e) {
      log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
          + e.get_con_type_exception() + "\t" + e.get_result_handle());
      LogFactory.close();
      System.out.println(e.getMessage());
      System.out.println("错误文件路径：" + e.get_file_name());
      System.out.println("错误文件内容：" + e.get_content());
      System.out.println("请重新输入文件名称：");
      Scanner in = new Scanner(System.in);

      String path = in.nextLine();
      String name1 = SocialNetworkFactory.class.getName();
      Logger log11 = LogFactory.getLogger(1, 2, name1 + "e");// 记录异常/错误的日志
      log11.info("读取文件： " + path + "！");
      LogFactory.close();

      return createGraphUseBuffer(path);
    } finally {
      LogFactory.close();
    }
  }

  @Override
  public SocialNetwork createGraphUseNio(String filePath) {
    // 用close将文件控制流关闭后，只有再生成一个同文件路径名的log才能再次开启
    String name = SocialNetworkFactory.class.getName();
    Logger log1 = LogFactory.getLogger(1, 1, name + "e");// 记录异常/错误的日志

    SocialNetwork graph = new SocialNetwork();
    Map<String, Integer> vertexMap = new HashMap<>();
    Map<String, Integer> edgeMap = new HashMap<>();
    int numH = 0; // 记录读取文件的行数

    // 使用 Try-with-Resources 在try模块运行完成后，自动关闭文件流
    try {
      List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
      begin = System.currentTimeMillis();
      for (String line : lines) {
        numH++;
        if (numH % 1000 == 0) {
          System.out.println(numH);
        }
        String first = first_String(line);
        String patternYH = pattern(filePath, line);
        if (first.equals("GraphType")) {
          if (graph.getClass().getName().equals("graph." + patternYH)) { // 判断图的类型是否匹配
            graph.set_GraphType(patternYH);
          } else {
            throw new FileNotFormatReException(filePath, line, "文件错误！", "图的类型不匹配！",
                "重新读入新的文件！");
          }


        } else if (first.equals("GraphName")) {
          graph.set_GraphName(patternYH);


        } else if (first.equals("VertexType")) {
          if (patternYH.equals("Person")) { // 判断顶点类型是否匹配
            graph.set_VertexType(patternYH);
          } else {
            throw new FileNotFormatReException(filePath, line, "文件错误！", "顶点的类型不匹配！",
                "重新读入新的文件！");
          }


        } else if (first.equals("Vertex")) {
          String[] v = patternYH.split(" ");
//          try { // 判断点是否已经存在图中
//            for (Vertex x : graph.vertices()) {
//              if (x.getLabel().equals(v[0])) {
//                throw new FileNotFormatException(filePath, line, "文件错误！", "图中顶点Label重复！",
//                    "系统自动处理！");
//              }
//            }
//            vertexMap.put(v[0], 1);
//          } catch (FileNotFormatException e) {
//            int num = vertexMap.get(v[0]);// 多个节点的Label重复
//            vertexMap.put(v[0], num + 1);
//            Vertex newV = new Word(v[0] + num);
//            graph.addVertex(newV);
//            System.out.println(e.getMessage());
//            System.out.println("错误文件路径：" + e.get_file_name());
//            System.out.println("错误文件内容：" + e.get_content());
//            log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                + e.get_con_type_exception() + "\t" + "将顶点：" + v[0] + "变为顶点：" + newV.getLabel());
//            continue;
//          }
          if (v[1].equals("Person")) {
//            if (v.length != 4) { // 判断点的信息是否完整
//              throw new FileNotFormatReException(filePath, line, "文件错误！", "点的信息不完整（属性问题）！",
//                  "重新读入新的文件！");
//            }
            Vertex vertex = new Person(v[0]);
            vertex.fillVertexInfo(argus(v));
            graph.addVertex(vertex);
          }


        } else if (first.equals("EdgeType")) {
//          if (patternYH.equals("CommentTie ForwardTie FriendTie")) { // 判断边的类型是否匹配
            graph.set_EdgeType(patternYH);
//          } else {
//            throw new FileNotFormatReException(filePath, line, "文件错误！", "边的类型不匹配！",
//                "重新读入新的文件！");
//          }


        } else if (first.equals("Edge")) {
          String[] v = patternYH.split(" ");
          Vertex x1 = label_v(graph, v[3]);
          Vertex x2 = label_v(graph, v[4]);
//          for (int j = 3; j < v.length - 1; j++) {
//            if (label_v(graph, v[j]) == null) { // 判断边中的顶点是否被定义
//              throw new FileNotFormatReException(filePath, line, "文件错误！", "边中的顶点未定义！",
//                  "重新读入新的文件！");
//            }
//          }
//          try { // 判断该边是不是Loop
//            if (x1.equals(x2)) {
//              throw new FileNotFormatException(filePath, line, "文件错误！", "该图中不存在Loop！",
//                  "系统自动处理！");
//            }
//          } catch (FileNotFormatException e) {
//            System.out.println(e.getMessage());
//            System.out.println("错误文件路径：" + e.get_file_name());
//            System.out.println("错误文件内容：" + e.get_content());
//            log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                + e.get_con_type_exception() + "\t" + "直接忽略这种非法边！");
//            continue;
//          }
//          try {
//            for (Edge x : graph.edges()) {
//              if (x.get_label().equals(v[0])) { // 判断边是否在图中已经存在
//                throw new FileNotFormatException(filePath, line, "文件错误！", "图中边的Label重复！",
//                    "系统自动处理！");
//              }
//            }
//            edgeMap.put(v[0], 1);
//          } catch (FileNotFormatException e) {
//            int num = edgeMap.get(v[0]);// 多个边的Label重复
//            edgeMap.put(v[0], num + 1);
//            List<Vertex> vertices = new ArrayList<>();
//            for (int i = 3; i < v.length - 1; i++) {
//              vertices.add(label_v(graph, v[i]));
//            }
//            Edge newE = new WordNeighborhood(v[0] + num, Double.valueOf(v[2]), x1,
//                x2, v[5], vertices);
//            graph.addEdge(newE);
//            System.out.println(e.getMessage());
//            System.out.println("错误文件路径：" + e.get_file_name());
//            System.out.println("错误文件内容：" + e.get_content());
//            log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                + e.get_con_type_exception() + "\t" + "将边：" + v[0] + "变为边：" + newE.get_label());
//            continue;
//          }
//          if (!v[5].equals("Yes")) { // 判断边的方向格式是否正确
//            throw new FileNotFormatReException(filePath, line, "文件错误！", "边的方向叙述错误！",
//                "重新读入新的文件！");
//          }
//          if (Double.valueOf(v[2]) <= 0 || Double.valueOf(v[2]) > 1) { // 判断边的权重是否符合要求
//            throw new FileNotFormatReException(filePath, line, "文件错误！", "该边的权重不符合要求！",
//                "重新读入新的文件！");
//          }
          if (v[1].equals("FriendTie")) {
            List<Vertex> vertices = new ArrayList<>();
            vertices.add(x1);
            vertices.add(x2);

            Edge e = new FriendTie(v[0], Double.valueOf(v[2]), x1,
                x2, v[5], vertices);
            graph.addEdge(e);
          } else if (v[1].equals("ForwardTie")) {
            if ((graph instanceof SocialNetwork)) {
              List<Vertex> vertices = new ArrayList<>();
              vertices.add(x1);
              vertices.add(x2);
              Edge e = new ForwardTie(v[0], Double.valueOf(v[2]), x1,
                  x2, v[5], vertices);
              graph.addEdge(e);
            }
          } else if (v[1].equals("CommentTie")) {
            List<Vertex> vertices = new ArrayList<>();
            vertices.add(x1);
            vertices.add(x2);
            Edge e = new CommentTie(v[0], Double.valueOf(v[2]), x1,
                x2, v[5], vertices);
            graph.addEdge(e);
          }
        } else if (first.equals("HyperEdge")) {
//          try {
//            throw new FileNotFormatException(filePath, line, "文件错误！", "该图不能存在超边！", "系统自动处理！");
//          } catch (FileNotFormatException e) {
//            System.out.println(e.getMessage());
//            System.out.println("错误文件路径：" + e.get_file_name());
//            System.out.println("错误文件内容：" + e.get_content());
//            log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
//                + e.get_con_type_exception() + "\t" + "直接忽略这种非法边！");
//          }
        }
      }
      end = System.currentTimeMillis();
      System.out.println("BufferedReader执行耗时:" + (end - begin) + " 毫秒");
      return graph;
    } catch (IOException e) {
      log1.info(filePath + "\t" + "空" + "\t" + "文件错误！" + "\t" + "输入的文件路径错误" + "\t" + "重新读入新的文件！");
      LogFactory.close();
      System.out.println(e.getMessage());
      System.out.println("请重新输入文件名称：");
      Scanner in = new Scanner(System.in);

      String path = in.nextLine();
      String name1 = GraphPoetFactory.class.getName();
      Logger log11 = LogFactory.getLogger(1, 2, name1 + "e");// 记录异常/错误的日志
      log11.info("读取文件： " + path + "！");
      in.close();
      LogFactory.close();

      return createGraphUseBuffer(path);
    } catch (FileNotFormatReException e) {
      log1.info(filePath + "\t" + e.get_content() + "\t" + e.getMessage() + "\t"
          + e.get_con_type_exception() + "\t" + e.get_result_handle());
      LogFactory.close();
      System.out.println(e.getMessage());
      System.out.println("错误文件路径：" + e.get_file_name());
      System.out.println("错误文件内容：" + e.get_content());
      System.out.println("请重新输入文件名称：");
      Scanner in = new Scanner(System.in);

      String path = in.nextLine();
      String name1 = GraphPoetFactory.class.getName();
      Logger log11 = LogFactory.getLogger(1, 2, name1 + "e");// 记录异常/错误的日志
      log11.info("读取文件： " + path + "！");
      in.close();
      LogFactory.close();

      return createGraphUseBuffer(path);
    } finally {
      LogFactory.close();
    }
  }

  /**
   * 获取运行时间
   * 
   * @return 运行时间
   */
  public long getTime() {
    return (this.end - this.begin);
  }

  /**
   * 将语句中英文双引号中的内容提取出来
   * 
   * @param 待提取的字符串
   * 
   * @return 双引号中的内容
   */
  public String pattern(String filePath, String s) throws FileNotFormatReException {

    String patternGraphType = "GraphType\\s*=\\s*\"\\w+\""; // 图的类型的模式串
    String patternGraphName = "GraphName\\s*=\\s*\"\\w+\"";// 图的名称的模式串
    String patternVertexType = "VertexType\\s*=\\s*(\\s*\"\\w+\"\\s*,?\\s*)+\\s*";// 顶点类型的模式串
    String patternVertex = "Vertex\\s*=\\s*<\\s*\"\\w+\"\\s*,\\s*\"\\w+"
        + "\"\\s*,?\\s*(<(\\s*\"[\\w.-]+\"\\s*,?)*>)?\\s*>\\s*"; // 顶点信息的模式串
    String patternEdgeType = "EdgeType\\s*=\\s*(\\s*\"\\w+\"\\s*,?\\s*)+\\s*";// 边的类型的模式串
    String patternEdge = "Edge\\s*=\\s*<\\s*(\\s*\"[\\w.-]+\"\\s*,?\\s*){6}\\s*>\\s*";// 边的信息的模式串
    String pattern = "\"([\\w.-]+)\""; // 匹配引号当中的内容

    Pattern r1 = Pattern.compile(patternGraphType);
    Pattern r2 = Pattern.compile(patternGraphName);
    Pattern r3 = Pattern.compile(patternVertexType);
    Pattern r4 = Pattern.compile(patternVertex);
    Pattern r5 = Pattern.compile(patternEdgeType);
    Pattern r6 = Pattern.compile(patternEdge);
    Pattern r8 = Pattern.compile(pattern);

    Matcher m1 = r1.matcher(s);
    Matcher m2 = r2.matcher(s);
    Matcher m3 = r3.matcher(s);
    Matcher m4 = r4.matcher(s);
    Matcher m5 = r5.matcher(s);
    Matcher m6 = r6.matcher(s);
    Matcher m8 = r8.matcher(s);
    String a = " ";
    if (first_String(s).equals("GraphType") && !m1.matches()) {
      throw new FileNotFormatReException(filePath, s, "文件内容出错！", "图的类型的信息不完整！", "重新读入新的文件！");
    } else if (first_String(s).equals("GraphName") && !m2.matches()) {
      throw new FileNotFormatReException(filePath, s, "文件内容出错！", "图的名字的信息不完整！", "重新读入新的文件！");
    } else if (first_String(s).equals("VertexType") && !m3.matches()) {
      throw new FileNotFormatReException(filePath, s, "文件内容出错！", "点的类型的信息不完整！", "重新读入新的文件！");
    } else if (first_String(s).equals("Vertex") && !m4.matches()) {
      throw new FileNotFormatReException(filePath, s, "文件内容出错！", "点的信息不完整（非属性问题）！", "重新读入新的文件！");
    } else if (first_String(s).equals("EdgeType") && !m5.matches()) {
      throw new FileNotFormatReException(filePath, s, "文件内容出错！", "边的类型的信息不完整！", "重新读入新的文件！");
    } else if (first_String(s).equals("Edge") && !m6.matches()) {
      throw new FileNotFormatReException(filePath, s, "文件内容出错！", "边的信息不完整！", "重新读入新的文件！");
    } else {
      while (m8.find()) {
        a = a + " " + m8.group(1);
      }
    }
    return a.trim();
  }

  /*
   * 获取字符串的第一个按空格分割的第一个字符串
   * 
   * @param 待处理的字符串
   * 
   * @return 返回按空格分割的第一个字符串
   */
  public String first_String(String s) {
    String[] a = s.split(" ");
    return a[0];
  }

  public String[] argus(String[] v) {
    String[] args = new String[v.length - 2];
    for (int i = 0; i < v.length - 2; i++) {
      args[i] = v[i + 2];
    }
    return args;
  }

  /**
   * 返回顶点为label的顶点
   * 
   * @param 顶点的标签label
   * 
   * @return 返回顶点为label的顶点
   */
  public Vertex label_v(SocialNetwork graph, String label) {
    for (Vertex v : graph.vertices()) {
      if (v.getLabel().equals(label)) {
        return v;
      } else {
        continue;
      }
    }
    return null;
  }



}
