package factory;

import edge.WordNeighborhood;
import java.util.List;
import vertex.Vertex;

public class WordEdgeFartory extends EdgeFactory {

  @Override
  public WordNeighborhood createEdge(String label, List<Vertex> vertices, List<Double> weights) {
    return new WordNeighborhood(label, weights.get(0), vertices.get(0), vertices.get(1), "Yes",
        vertices);

  }
}
