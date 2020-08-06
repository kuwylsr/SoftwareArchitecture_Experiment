package factory;

import edge.FriendTie;
import java.util.List;
import vertex.Vertex;

public class FriendConnectionFactory extends EdgeFactory {

  @Override
  public FriendTie createEdge(String label, List<Vertex> vertices, List<Double> weights) {

    return new FriendTie(label, weights.get(0), vertices.get(0), vertices.get(1), "Yes", vertices);

  }
}
