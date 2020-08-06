package helper;

import edge.Edge;
import edge.HyperEdge;
import edge.SameMovieHyperEdge;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
import graph.Graph;
import graph.GraphPoet;
import graph.MovieGraph;
import graph.NetworkTopology;
import graph.SocialNetwork;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Paint;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import org.apache.commons.collections15.Transformer;
import vertex.Actor;
import vertex.Computer;
import vertex.Director;
import vertex.Movie;
import vertex.Person;
import vertex.Router;
import vertex.Server;
import vertex.Vertex;
import vertex.WirelessRouter;
import vertex.Word;

public class GraphVisualizationHelper {



  public static void visualize(Graph<Vertex, Edge> g1,
      edu.uci.ics.jung.graph.Graph<Vertex, Edge> g2) {


    // 初始化 GUI图结构
    for (Vertex v : g1.vertices()) {
      g2.addVertex(v);
    }

    for (Edge e : g1.edges()) {
      List<Vertex> vertices = new ArrayList<>();
      if (g1 instanceof MovieGraph || g1 instanceof NetworkTopology) {
        Set<Vertex> s = e.targetVertices();
        Iterator<Vertex> it = s.iterator();
        while (it.hasNext()) {
          vertices.add(it.next());
        }
      } else if (g1 instanceof GraphPoet || g1 instanceof SocialNetwork) {
        Set<Vertex> s1 = e.sourceVertices();
        Set<Vertex> s2 = e.targetVertices();
        Iterator<Vertex> it1 = s1.iterator();
        Iterator<Vertex> it2 = s2.iterator();
        while (it1.hasNext() && it2.hasNext()) {
          vertices.add(it1.next());
          vertices.add(it2.next());
        }
      }
      if (vertices.size() == 2) {
        if (g1 instanceof MovieGraph || g1 instanceof NetworkTopology) {
          g2.addEdge(e, vertices.get(0), vertices.get(1));
        } else if (g1 instanceof GraphPoet || g1 instanceof SocialNetwork) {
          g2.addEdge(e, vertices.get(0), vertices.get(1), EdgeType.DIRECTED);
        }

      } else if (vertices.size() == 1) {
        g2.addEdge(e, vertices.get(0), vertices.get(0));
      } else {
        for (int i = 0; i < vertices.size() - 1; i++) {
          List<Vertex> list = new ArrayList<>();
          list.add(vertices.get(i));
          list.add(vertices.get(i + 1));
          HyperEdge e1 = new SameMovieHyperEdge(e.get_label(), e.get_weight(), list);
          g2.addEdge(e1, list.get(0), list.get(1));
        }
      }

    }

    // 设置布局
    Layout<Vertex, Edge> layout = new CircleLayout<Vertex, Edge>(g2);
    // 设置可视化的的观察器，节点、边的信息如何显示、节点和边的颜色、形态都可以在vv中更改
    VisualizationViewer<Vertex, Edge> vv = new VisualizationViewer<Vertex, Edge>(layout);

    // 默认显示节点的label
    // vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<Vertex>());

    // 或者利用一个Transformer，将节点的类型也显示出来
    // 使用匿名内部类
    vv.getRenderContext().setVertexLabelTransformer(new Transformer<Vertex, String>() {
      @Override
      public String transform(Vertex v) {
        if (v instanceof Word) {
          return "Word " + v.getLabel();
        } else if (v instanceof Person) {
          return "Person " + v.getLabel();
        } else if (v instanceof WirelessRouter) {
          return "WirelessRouter " + v.getLabel();
        } else if (v instanceof Computer) {
          return "Computer " + v.getLabel();
        } else if (v instanceof Router) {
          return "Router " + v.getLabel();
        } else if (v instanceof Server) {
          return "Server " + v.getLabel();
        } else if (v instanceof Movie) {
          return "Movie " + v.getLabel();
        } else if (v instanceof Actor) {
          return "Actor " + v.getLabel();
        } else if (v instanceof Director) {
          return "Director " + v.getLabel();
        } else {
          return null;
        }

      }
    });

    // 设置节点的颜色，利用一个Vertex - Paint 的Transformer
    // 使用匿名内部类
    Transformer<Vertex, Paint> vertexPaint = new Transformer<Vertex, Paint>() {
      @Override
      public Paint transform(Vertex v) {
        if (v instanceof Word || v instanceof Person) {
          return Color.RED;
        } else if (v instanceof Computer) {
          return Color.BLUE;
        } else if (v instanceof WirelessRouter) {
          return Color.cyan;
        } else if (v instanceof Router) {
          return Color.GREEN;
        } else if (v instanceof Server) {
          return Color.ORANGE;
        } else if (v instanceof Movie) {
          return Color.BLUE;
        } else if (v instanceof Actor) {
          return Color.RED;
        } else if (v instanceof Director) {
          return Color.GREEN;
        } else {
          return null;
        }

      }

    };

    // 设置顶点颜色
    vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);

    // 设置边的文本

    // vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<Edge>());
    // 默认显示边的label

    // 利用Transformer，显示边的label 以及 权值
    // 使用匿名内部类
    vv.getRenderContext().setEdgeLabelTransformer(new Transformer<Edge, String>() {
      public String transform(Edge e) {
        return e.toString();
      }
    });

    // 在这里edgeStrokeTransformer显示的是虚线
    float[] dash = {10.0f};
    final Stroke edgeStroke =
        new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
    // 使用匿名内部类
    Transformer<Edge, Stroke> edgeStrokeTransformer = new Transformer<Edge, Stroke>() {
      public Stroke transform(Edge s) {
        if (s instanceof SameMovieHyperEdge) {
          return edgeStroke;
        }
        return null;
      }
    };
    // 设置边的线型
    vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);

    // 设置模式，默认模式可以拖动节点
    DefaultModalGraphMouse<Vertex, Edge> gm = new DefaultModalGraphMouse<Vertex, Edge>();
    gm.setMode(Mode.PICKING);
    vv.setGraphMouse(gm);

    // 最后是套话，pack 、 visible
    JFrame myframe = new JFrame();
    myframe.getContentPane().add(vv);
    myframe.setExtendedState(JFrame.MAXIMIZED_BOTH);
    myframe.pack();
    myframe.setVisible(true);

  }
}
