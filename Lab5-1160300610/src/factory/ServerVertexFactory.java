package factory;

import vertex.Server;

public class ServerVertexFactory extends VertexFactory {

  @Override
  public Server createVertex(String label, String[] args) {
    Server v = new Server(label);
    v.fillVertexInfo(args);
    return v;
  }
}
