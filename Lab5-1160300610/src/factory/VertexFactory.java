package factory;

import vertex.Vertex;

public abstract class VertexFactory {

  public abstract Vertex createVertex(String label, String[] args);

  public String[] argus(String[] v) {
    String[] args = new String[v.length - 2];
    for (int i = 0; i < v.length - 2; i++) {
      args[i] = v[i + 2];
    }
    return args;
  }

}
