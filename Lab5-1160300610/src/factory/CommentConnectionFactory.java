package factory;

import edge.CommentTie;
import java.util.List;
import vertex.Vertex;

public class CommentConnectionFactory extends EdgeFactory {

  @Override
  public CommentTie createEdge(String label, List<Vertex> vertices, List<Double> weights) {
    return new CommentTie(label, weights.get(0), vertices.get(0), vertices.get(1), "Yes", vertices);

  }
}
