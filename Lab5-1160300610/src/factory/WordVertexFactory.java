package factory;

import vertex.Word;

public class WordVertexFactory extends VertexFactory {

  @Override
  public Word createVertex(String label, String[] args) {
    return new Word(label);
  }

}
