package factory;

import vertex.Director;

public class DirectorVertexFactory extends VertexFactory {

  @Override
  public Director createVertex(String label, String[] args) {
    Director v = new Director(label);
    v.fillVertexInfo(args);
    return v;
  }
}
