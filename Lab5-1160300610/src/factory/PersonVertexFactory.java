package factory;

import vertex.Person;

public class PersonVertexFactory extends VertexFactory {

  @Override
  public Person createVertex(String label, String[] args) {
    Person v = new Person(label);
    v.fillVertexInfo(args);
    return v;
  }
}
